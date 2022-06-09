package com.zxx.demorepository.redismq

import com.zxx.demorepository.redismq.config.*
import org.slf4j.*
import org.springframework.beans.factory.annotation.*
import org.springframework.context.annotation.*
import org.springframework.data.redis.connection.*
import org.springframework.data.redis.connection.stream.*
import org.springframework.data.redis.hash.*
import org.springframework.data.redis.serializer.*
import org.springframework.data.redis.stream.*
import org.springframework.data.redis.stream.Subscription
import org.springframework.util.*
import java.time.*

/**
 * <p>
 *
 * </p>
 *
 * @author kam
 * @since 2022/06/08
 */

object MqConst {

    const val group1 = "group1"

    const val group2 = "group2"

    const val stream1 = "stream1"

    const val consumer1 = "consumer1"

    const val consumer2 = "consumer2"

    const val consumer3 = "consumer3"
}

class ListenerErrorHandler : ErrorHandler {

    val log = LoggerFactory.getLogger(ListenerErrorHandler::class.java)

    override fun handleError(p0: Throwable) {
        log.error("stream传输发生错误，", p0)
    }

}

@Configuration
class SubscriptionConfig(
    @Autowired
    override var redisStreamUtil: RedisStreamUtil
) : AbsSubscriptionConfig() {

    @Bean(initMethod = "start", destroyMethod = "stop")
    fun userMsgListenerContainer(factory: RedisConnectionFactory): StreamMessageListenerContainer<String, ObjectRecord<String, User>> {
        val serializer = RedisSerializer.string()
        val options = StreamMessageListenerContainer.StreamMessageListenerContainerOptions
            .builder()
            .keySerializer(serializer)
            .hashKeySerializer<String, String>(serializer)
            .hashValueSerializer<String, String>(serializer)
            .pollTimeout(Duration.ofSeconds(1))
            .serializer(StringRedisSerializer())
            .objectMapper(ObjectHashMapper())
            .errorHandler(ListenerErrorHandler())
            .targetType(User::class.java)
            .build()

        val container: StreamMessageListenerContainer<String, ObjectRecord<String, User>> =
            StreamMessageListenerContainer.create(factory, options)

//        container.registrySubscription(
//            ListenerMessage1(), MqConst.stream1,
//            MqConst.group2,
//            MqConst.consumer2
//        )
        return container
    }

    @Autowired
    lateinit var userMsgListenerContainer: StreamMessageListenerContainer<String, ObjectRecord<String, User>>

    /**
     * 订阅者1，消费组group1，收到消息后自动确认，与订阅者2为竞争关系，消息仅被其中一个消费
     */
    @Bean
    fun subscription(listenerMessage: ListenerMessage1): Subscription {
        return listenerMessage.registrySubscription(
            userMsgListenerContainer, MqConst.stream1,
            MqConst.group2,
            MqConst.consumer2
        )
    }

    /**
     * 订阅者2，消费组group1，收到消息后自动确认，与订阅者1为竞争关系，消息仅被其中一个消费
     */
    @Bean
    fun subscription2(
        listenerMessage: ListenerMessage2
    ): Subscription {
        return listenerMessage.registrySubscription(
            userMsgListenerContainer,
            MqConst.stream1,
            MqConst.group2,
            MqConst.consumer2
        )
    }

    /**
     * 订阅者3，消费组group1，收到消息后自动确认，与订阅者2为竞争关系，消息仅被其中一个消费
     */
    @Bean
    fun subscription3(
        listenerMessage: ListenerMessage3
    ): Subscription {
        return listenerMessage.registrySubscription(
            userMsgListenerContainer,
            MqConst.stream1,
            MqConst.group2,
            MqConst.consumer3
        )
    }

    /**
     * 订阅者3，消费组group2，收到消息后不自动确认，需要用户选择合适的时机确认，与订阅者1和2非竞争关系，即使消息被订阅者1或2消费，亦可消费
     *
     * 当某个消息被ACK，PEL列表就会减少
     * 如果忘记确认（ACK），则PEL列表会不断增长占用内存
     * 如果服务器发生意外，重启连接后将再次收到PEL中的消息ID列表
     */
    @Bean
    fun subscription4(
        listenerMessage: ListenerMessage2
    ): Subscription {
        return listenerMessage.registrySubscription(
            userMsgListenerContainer,
            MqConst.stream1,
            MqConst.group2,
            MqConst.consumer1
        )
    }
}