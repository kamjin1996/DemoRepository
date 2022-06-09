package com.zxx.demorepository.redismq

import cn.hutool.core.bean.*
import com.zxx.demorepository.redismq.config.*
import org.springframework.beans.factory.annotation.*
import org.springframework.boot.*
import org.springframework.data.redis.connection.stream.*
import org.springframework.stereotype.*
import java.beans.EventHandler.*
import java.util.*
import kotlin.concurrent.*

@Component
class SendMsg : CommandLineRunner {

    @Autowired
    lateinit var redisStreamUtil: RedisStreamUtil

    override fun run(vararg args: String?) {
        var count = 0
        Timer().schedule(timerTask {
            count++
            redisStreamUtil.add(ObjectRecord.create(MqConst.stream1, User("zhangsan$count")))
        }, 3000L, 2000L)
    }
}

data class User(val name: String)