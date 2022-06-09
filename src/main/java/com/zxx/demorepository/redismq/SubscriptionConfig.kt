package com.zxx.demorepository.redismq

import com.zxx.demorepository.redismq.config.*
import org.slf4j.*
import org.springframework.context.annotation.*
import org.springframework.data.redis.connection.*
import org.springframework.data.redis.connection.stream.*
import org.springframework.data.redis.stream.*
import org.springframework.util.*

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
class SubscriptionConfig : AbsSubscriptionConfig() {

    @Bean
    fun userMsgListenerContainer(factory: RedisConnectionFactory): StreamMessageListenerContainer<String, ObjectRecord<String, String>> {
        return createStreamContainer(factory, null) {
            val keySimple = KeySimple("test")
            this.registryListener(ListenerMessage1(), keySimple)
            this.registryListener(ListenerMessage2(), MqConst.stream1, MqConst.group1, MqConst.consumer1, "test")
            this.registryListener(ListenerMessage2(), MqConst.stream1, MqConst.group1, MqConst.consumer2, "test")
            this.registryListener(ListenerMessage3(), keySimple)
            this.registryListener(ListenerMessage3(), MqConst.stream1, MqConst.group2, MqConst.consumer2, "test")
        }
    }
}