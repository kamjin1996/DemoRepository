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
    lateinit var redisStreamUtil: RedisStreamUtil

    override fun run(vararg args: String?) {
        var count = 0
        Timer().schedule(timerTask {
            redisStreamUtil.add(MqConst.stream1, "name", "张三${count++}")
        }, 3000L, 2000L)
    }
}