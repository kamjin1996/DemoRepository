package com.zxx.demorepository.redismq

import com.zxx.demorepository.redismq.config.*
import org.springframework.beans.factory.annotation.*
import org.springframework.boot.*
import org.springframework.stereotype.*
import java.util.*
import kotlin.concurrent.*

@Component
class SendMsg : CommandLineRunner {

    @Autowired
    lateinit var userMsgProvider: UserMsgProvider

    @Autowired
    lateinit var userTestMsgProvider: UserTestMsgProvider

    override fun run(vararg args: String?) {
        var count = 0
        Timer().schedule(timerTask {
            count++

            userMsgProvider.send(User("zhangsan$count"))
            userTestMsgProvider.send(User("lisi$count"))
        }, 3000L, 2000L)
    }
}

data class User(val name: String)

@Component
class UserMsgProvider(override val stream: String = MqConst.stream1) :
    AbsStreamMessageProducer<User>()

@Component
class UserTestMsgProvider(override val stream: String = "test") :
    AbsStreamMessageProducer<User>()