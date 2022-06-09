package com.zxx.demorepository.redismq

import com.zxx.demorepository.redismq.config.*
import org.springframework.data.redis.connection.stream.*
import org.springframework.stereotype.*

/**
 * redis stream监听消息
 */
@Component
class ListenerMessage1 : AbsStreamMessageListener<User>(User::class.java) {

    override fun onMessage0(message: User, messageId: String) {
        // 接收到消息
        println("ListenerMessage1---")
        println("message id " + messageId)
        println("stream " + stream)
        println("body " + message) //TODO debug string not case user
        println("---ListenerMessage1")
    }

}

/**
 * redis stream监听消息
 * 在消费完成后确认已消费
 */
@Component
class ListenerMessage2 : AbsStreamMessageListener<User>(User::class.java) {

    override fun onMessage0(message: User, messageId: String) {
        // 接收到消息
        println("ListenerMessage1---")
        println("message id " + messageId)
        println("stream " + stream)
        println("body " + message) //TODO debug string not case user
        println("---ListenerMessage1")
    }
}

/**
 * redis stream监听消息
 * 在确认已消费后再尝试消费
 */
@Component
class ListenerMessage3 : AbsStreamMessageListener<User>(User::class.java) {

    override fun onMessage0(message: User, messageId: String) {

        // 接收到消息
        println("ListenerMessage3---")
        println("message id " + messageId)
        println("stream " + stream)
        println("body " + message)
        println("---ListenerMessage3")
    }
}