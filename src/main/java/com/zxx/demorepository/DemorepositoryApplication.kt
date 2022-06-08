package com.zxx.demorepository

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.EnableAsync
import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.*

@SpringBootApplication(scanBasePackages = ["com.zxx.demorepository"])
@EnableScheduling
@EnableAsync
@MapperScan("com.zxx.demorepository.test.mapper")
class DemorepositoryApplication

fun main(args: Array<String>) {
    runApplication<DemorepositoryApplication>(*args)
}
