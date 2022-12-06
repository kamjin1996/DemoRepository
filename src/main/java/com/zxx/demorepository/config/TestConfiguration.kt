package com.zxx.demorepository.config

import org.springframework.context.annotation.*

/**
 * <p>
 *
 * </p>
 *
 * @author kam
 * @since 2022/06/20
 */
@Configuration
class TestConfiguration {

    @Bean
    @DependsOn("myConfig2")
    fun myConfig1(): MyConfig {
        println("myConfig1")
        return MyConfig()
    }

    @Bean
    fun myConfig2(): MyConfig {
        println("myConfig2")
        return MyConfig()
    }

}

class MyConfig {

}