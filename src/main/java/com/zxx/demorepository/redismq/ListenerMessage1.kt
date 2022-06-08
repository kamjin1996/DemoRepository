package com.zxx.demorepository.redismq

import com.zxx.demorepository.redismq.config.*
import org.springframework.data.redis.connection.stream.MapRecord
import org.springframework.beans.factory.annotation.*
import org.springframework.stereotype.*

/**
 * redis stream监听消息
 */
@Component
class ListenerMessage1 : BaseStreamListener {

    override fun onMessage(message: MapRecord<String, String, String?>) {
        // 接收到消息
        println("ListenerMessage1---")
        println("message id " + message.id)
        println("stream " + message.stream)
        println("body " + message.value)
        println("---ListenerMessage1")
    }
}

/**
 * redis stream监听消息
 * 在消费完成后确认已消费
 */
@Component
class ListenerMessage2 : BaseStreamListener {

    override fun onMessage(message: MapRecord<String, String, String?>) {
        val stream = message.stream ?: return

        // 接收到消息
        println("ListenerMessage2---")
        println("message id " + message.id)
        println("stream " + stream)
        println("body " + message.value)
        println("---ListenerMessage2")

        // 消费完成后确认消费（ACK）
        //redisStreamUtil.ack(stream, MqConst.group1, message.id)
    }
}

/**
 * redis stream监听消息
 * 在确认已消费后再尝试消费
 */
@Component
class ListenerMessage3 : BaseStreamListener {

    @Autowired
    lateinit var redisStreamUtil: RedisStreamUtil

    override fun onMessage(message: MapRecord<String, String, String?>) {
        val stream = message.stream ?: return

        // 接收到消息
        println("ListenerMessage3---")
        println("message id " + message.id)
        println("stream " + stream)
        println("body " + message.value)
        println("---ListenerMessage3")

        // 消费完成后确认消费（ACK）
        redisStreamUtil.ack(stream, MqConst.group1, message.id)
    }
}