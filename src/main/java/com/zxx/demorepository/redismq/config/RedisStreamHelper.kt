package com.zxx.demorepository.redismq.config

import cn.hutool.json.*
import com.alibaba.druid.support.json.JSONUtils.*
import org.slf4j.*
import org.springframework.data.redis.connection.*
import org.springframework.data.redis.connection.stream.*
import org.springframework.data.redis.hash.*
import org.springframework.data.redis.serializer.*
import org.springframework.data.redis.stream.*
import org.springframework.data.redis.stream.Subscription
import org.springframework.util.*
import java.time.*
import javax.validation.constraints.*

class KeySimple(val bizKey: String) {

    private val _streamPrefix = "stream-"
    private val _groupPrefix = "group-"
    private val _consumerPrefix = "consumer-"

    var stream: String
    var group: String
    var consumer: String

    init {
        if (bizKey.isBlank()) {
            throw RuntimeException("bizKey is null. not obtain stream key")
        }
        val trim = bizKey.trim()

        this.stream = _streamPrefix + trim
        this.group = _groupPrefix + trim
        this.consumer = _consumerPrefix + trim
    }
}

abstract class AbsSubscriptionConfig {

    fun <R : Record<String, *>, L : StreamListener<String, R>> StreamMessageListenerContainer<String, R>.registrySubscription(
        listener: L, keySimple: KeySimple
    ): Subscription {
        return registrySubscription(listener, keySimple.stream, keySimple.group, keySimple.consumer, keySimple.bizKey)
    }

    fun <R : Record<String, *>, C : StreamMessageListenerContainer<String, R>> StreamListener<String, R>.registrySubscription(
        container: C, keySimple: KeySimple
    ): Subscription {
        return registrySubscription(container, keySimple.stream, keySimple.group, keySimple.consumer, keySimple.bizKey)
    }

    /**
     * 注册消费者
     * [listener]为消费者，[group]为消费者组，[stream]为key，redisKey，通常一个业务对应一个stream,[consumerName]为消费者名称，在当前组内唯一
     * 同一组内所有消费者都是可以监听到消息的，属于竞争关系
     * 又由于消息只有group中的才可ack，所以一般每个客户端自己将会创建一个group
     */
    fun <R : Record<String, *>, L : StreamListener<String, R>> StreamMessageListenerContainer<String, R>.registrySubscription(
        listener: L, stream: String,
        group: String,
        consumerName: String, bizKey: String
    ): Subscription {
        val t = listener

        //注入参数
        if (listener is AbsStreamMessageListener<*>) {
            listener(group = group, consumerName = consumerName, bizKey = bizKey)
        }

        //创建消费组
        RedisStreamUtil.createGroup(stream, group)
        return this.receive(
            Consumer.from(group, consumerName),
            StreamOffset.create(stream, ReadOffset.lastConsumed()),
            t
        )
    }

    /**
     * 注册消费者
     * [listener]为消费者，[group]为消费者组，[stream]为key，redisKey，通常一个业务对应一个stream,[consumerName]为消费者名称，在当前组内唯一
     * 同一组内所有消费者都是可以监听到消息的，属于竞争关系
     * 又由于消息只有group中的才可ack，所以一般每个客户端自己将会创建一个group
     */
    fun <R : Record<String, *>> StreamListener<String, R>.registrySubscription(
        container: StreamMessageListenerContainer<String, R>,
        stream: String,
        group: String,
        consumerName: String, bizKey: String
    ): Subscription {
        return container.registrySubscription(listener = this, stream, group, consumerName, bizKey)
    }

    /**
     * 创建容器
     */
    inline fun <reified T> createStreamContainer(
        factory: RedisConnectionFactory,
        errorHandler: ErrorHandler? = null
    ): StreamMessageListenerContainer<String, ObjectRecord<String, T>> {
        val serializer = RedisSerializer.string()
        val options = StreamMessageListenerContainer.StreamMessageListenerContainerOptions
            .builder()
            .keySerializer(serializer)
            .hashKeySerializer<String, String>(serializer)
            .hashValueSerializer<String, String>(serializer)
            .pollTimeout(Duration.ofSeconds(1))
            .serializer(StringRedisSerializer())
            .objectMapper(ObjectHashMapper())
            .targetType(T::class.java)
            .apply {
                if (errorHandler != null) {
                    this.errorHandler(errorHandler)
                }
            }
            .build()

        return StreamMessageListenerContainer.create(factory, options)
    }
}

abstract class AbsStreamMessageProducer<in T : Any> {

    val log = LoggerFactory.getLogger(AbsStreamMessageProducer::class.java)

    abstract val stream: String

    /**
     * 发送
     */
    fun send(message: T) {
        log.info("STREAM message send. stream:${stream}")
        RedisStreamUtil.add(ObjectRecord.create(stream, message))
    }
}

/**
 * 抽象Stream监听器
 */
abstract class AbsStreamMessageListener<T : Any> : StreamListener<String, ObjectRecord<String, T>> {

    val log = LoggerFactory.getLogger(AbsStreamMessageListener::class.java)

    lateinit var group: String

    lateinit var bizKey: String

    lateinit var consumerName: String

    operator fun invoke(group: String, consumerName: String, bizKey: String) {
        this.group = group
        this.bizKey = bizKey
        this.consumerName = consumerName
    }

    override fun onMessage(message: ObjectRecord<String, T>) {
        log.info("STREAM message read. stream:${message.stream}")
        try {
            onMessage0(message)
            ack(message.stream!!, group, message.id)
        } catch (e: Exception) {
            log.error(
                "STREAM message read error. stream:${message.stream} group:$group consumer:$consumerName recordId:${message.id} body:${
                    JSONUtil.toJsonStr(
                        message.value
                    )
                }",
                e
            )
            throw e
        }
    }

    fun ack(stream: String, group: String, recordId: RecordId) {
        RedisStreamUtil.ack(stream, group, recordId)
    }

    /**
     * 处理消息
     */
    abstract fun onMessage0(message: ObjectRecord<String, T>)
}