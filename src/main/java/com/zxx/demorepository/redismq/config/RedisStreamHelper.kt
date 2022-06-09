package com.zxx.demorepository.redismq.config

import cn.hutool.json.*
import com.alibaba.druid.support.json.JSONUtils.*
import com.fasterxml.jackson.annotation.*
import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.module.kotlin.*
import com.zxx.demorepository.utils.*
import org.slf4j.*
import org.springframework.beans.factory.*
import org.springframework.data.redis.connection.*
import org.springframework.data.redis.connection.stream.*
import org.springframework.data.redis.hash.*
import org.springframework.data.redis.serializer.*
import org.springframework.data.redis.stream.*
import org.springframework.data.redis.stream.Subscription
import org.springframework.util.*
import java.sql.*
import java.time.*
import javax.annotation.*
import javax.validation.constraints.*
import kotlin.reflect.*

class KeySimple(
    val stream: String,
    val group: String = "$stream-group",
    val consumer: String = "$stream-consumer"
) {
    init {
        if (stream.isBlank()) {
            throw RuntimeException("stream key is blank.")
        }
    }
}

private val jsonMapper = jacksonObjectMapper().apply {
    propertyNamingStrategy = PropertyNamingStrategy.LOWER_CAMEL_CASE
    setSerializationInclusion(JsonInclude.Include.NON_NULL)
}

abstract class AbsSubscriptionConfig : DisposableBean {

    override fun destroy() {
        //stop all container
        val containersMap =
            SpringContextHolder.applicationContext.getBeansOfType(StreamMessageListenerContainer::class.java)
        if (containersMap.isNotEmpty()) {
            containersMap.entries.forEach {
                it.value.stop()
            }
        }
    }

    fun <R : Record<String, *>, L : StreamListener<String, R>> StreamMessageListenerContainer<String, R>.registryListener(
        listener: L, keySimple: KeySimple
    ): Subscription {
        return registryListener(listener, keySimple.stream, keySimple.group, keySimple.consumer, keySimple.stream)
    }

    fun <R : Record<String, *>, C : StreamMessageListenerContainer<String, R>> StreamListener<String, R>.registryListener(
        container: C, keySimple: KeySimple
    ): Subscription {
        return this.registryListener(container, keySimple.stream, keySimple.group, keySimple.consumer, keySimple.stream)
    }

    /**
     * 注册消费者
     * [listener]为消费者，[group]为消费者组，[stream]为key，redisKey，通常一个业务对应一个stream,[consumerName]为消费者名称，在当前组内唯一
     * 同一组内所有消费者都是可以监听到消息的，属于竞争关系
     * 又由于消息只有group中的才可ack，所以一般每个客户端自己将会创建一个group
     */
    fun <R : Record<String, *>, L : StreamListener<String, R>> StreamMessageListenerContainer<String, R>.registryListener(
        listener: L, stream: String,
        group: String,
        consumerName: String, bizKey: String
    ): Subscription {

        //注入参数
        if (listener is AbsStreamMessageListener<*>) {
            listener(stream = stream, group = group, consumerName = consumerName, bizKey = bizKey)
        }

        //创建消费组
        RedisStreamUtil.createGroup(stream, group)
        return this.receive(
            Consumer.from(group, consumerName),
            StreamOffset.create(stream, ReadOffset.lastConsumed()),
            listener
        )
    }

    /**
     * 注册消费者
     * [listener]为消费者，[group]为消费者组，[stream]为key，redisKey，通常一个业务对应一个stream,[consumerName]为消费者名称，在当前组内唯一
     * 同一组内所有消费者都是可以监听到消息的，属于竞争关系
     * 又由于消息只有group中的才可ack，所以一般每个客户端自己将会创建一个group
     */
    fun <R : Record<String, *>> StreamListener<String, R>.registryListener(
        container: StreamMessageListenerContainer<String, R>,
        stream: String,
        group: String,
        consumerName: String, bizKey: String
    ): Subscription {
        return container.registryListener(listener = this, stream, group, consumerName, bizKey)
    }

    /**
     * 创建容器
     */
    fun createStreamContainer(
        factory: RedisConnectionFactory,
        errorHandler: ErrorHandler? = null,
        registrySubscription: StreamMessageListenerContainer<String, ObjectRecord<String, String>>.() -> Unit
    ): StreamMessageListenerContainer<String, ObjectRecord<String, String>> {
        val serializer = RedisSerializer.string()
        val options = StreamMessageListenerContainer.StreamMessageListenerContainerOptions
            .builder()
            .keySerializer(serializer)
            .hashKeySerializer<String, String?>(serializer)
            .hashValueSerializer<String, String>(serializer)
            .pollTimeout(Duration.ofSeconds(1))
            .serializer(StringRedisSerializer())
            .targetType(String::class.java)
            .apply {
                if (errorHandler != null) {
                    this.errorHandler(errorHandler)
                }
            }
            .build()
        val container = StreamMessageListenerContainer.create(factory, options)
        registrySubscription(container)

        //必须启动container 才可以监听到消息
        container.start()
        return container
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

        //在此处序列化，发送jsonString字符串
        RedisStreamUtil.add(ObjectRecord.create(stream, jsonMapper.writeValueAsString(message)))
    }
}

/**
 * 抽象Stream监听器
 */
abstract class AbsStreamMessageListener<T : Any>(private var msgType: Class<T>) :
    StreamListener<String, ObjectRecord<String, String>> {

    val log = LoggerFactory.getLogger(AbsStreamMessageListener::class.java)

    lateinit var group: String

    lateinit var bizKey: String

    lateinit var consumerName: String

    lateinit var stream: String

    operator fun invoke(stream: String, group: String, consumerName: String, bizKey: String) {
        this.group = group
        this.bizKey = bizKey
        this.consumerName = consumerName
        this.stream = stream
    }

    override fun onMessage(message: ObjectRecord<String, String>) {
        log.info("STREAM message read. stream:${message.stream}")
        try {
            val messageObj = jsonMapper.readValue(message.value, msgType)
            onMessage0(messageObj as T, message.id.value)
            ack(message.stream!!, group, message.id)
        } catch (e: Exception) {
            log.error(
                "STREAM message read error. stream:${message.stream} group:$group consumer:$consumerName recordId:${message.id} body:${message.value}",
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
    abstract fun onMessage0(message: T, messageId: String)
}