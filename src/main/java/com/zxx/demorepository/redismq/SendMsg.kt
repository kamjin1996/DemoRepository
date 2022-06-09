package com.zxx.demorepository.redismq

import com.zxx.demorepository.redismq.config.*
import org.springframework.boot.*
import org.springframework.data.redis.connection.stream.*
import org.springframework.stereotype.*
import java.util.*
import kotlin.concurrent.*

@Component
class SendMsg : CommandLineRunner {

    override fun run(vararg args: String?) {
        var count = 0
        Timer().schedule(timerTask {
            count++

            RedisStreamUtil.add(ObjectRecord.create(MqConst.stream1, User("zhangsan$count")))

            RedisStreamUtil.add(ObjectRecord.create(KeySimple("test").stream, User("lisi$count")))
        }, 3000L, 2000L)
    }
}

data class User(val name: String)