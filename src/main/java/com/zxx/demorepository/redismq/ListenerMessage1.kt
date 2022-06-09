package com.zxx.demorepository.redismq

import com.zxx.demorepository.redismq.config.*
import org.springframework.data.redis.connection.stream.*
import org.springframework.stereotype.*

/**
 * redis stream监听消息
 */
@Component
class ListenerMessage1 : AbsStreamMessageListener<User>() {

    override fun onMessage0(message: ObjectRecord<String, User>) {
        // 接收到消息
        println("ListenerMessage1---")
        println("message id " + message.id)
        println("stream " + message.stream)
        println("body " + message.value) //TODO debug string not case user
        println("---ListenerMessage1")
    }
}

/**
 * redis stream监听消息
 * 在消费完成后确认已消费
 */
@Component
class ListenerMessage2 : AbsStreamMessageListener<User>() {

    override fun onMessage0(message: ObjectRecord<String, User>) {
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
class ListenerMessage3 : AbsStreamMessageListener<User>() {

    override fun onMessage0(message: ObjectRecord<String, User>) {
        val stream = message.stream ?: return

        // 接收到消息
        println("ListenerMessage3---")
        println("message id " + message.id)
        println("stream " + stream)
        println("body " + message.value)
        println("---ListenerMessage3")
    }
}