package com.zxx.demorepository.utils

import org.springframework.context.*
import org.springframework.core.*
import org.springframework.core.annotation.*
import org.springframework.stereotype.*

@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
class SpringContextHolder : ApplicationContextAware {

    override fun setApplicationContext(applicationContext: ApplicationContext) {
        Companion.applicationContext = applicationContext
    }

    companion object {
        lateinit var applicationContext: ApplicationContext

        val activeProfile: String
            get() = applicationContext.environment.activeProfiles[0]

        fun <T> getBean(name: String): T {
            @Suppress("UNCHECKED_CAST")
            return applicationContext.getBean(name) as T
        }

        inline fun <reified T> getBean(): T {
            return applicationContext.getBean(T::class.java)
        }
    }
}