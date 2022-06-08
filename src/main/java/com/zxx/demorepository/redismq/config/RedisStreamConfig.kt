package com.zxx.demorepository.redismq.config

import org.springframework.data.redis.connection.stream.*
import org.springframework.data.redis.stream.*

abstract class AbsSubscriptionConfig {

    abstract var redisStreamUtil: RedisStreamUtil

    /**
     * 注册消费者
     * [listener]为消费者，[group]为消费者组，[stream]为key，redisKey，通常一个业务对应一个stream,[consumerName]为消费者名称，在当前组内唯一
     * 同一组内所有消费者都是可以监听到消息的，属于竞争关系
     * 又由于消息只有group中的才可ack，所以一般每个客户端自己将会创建一个group
     */
    fun <R : Record<String, *>> StreamMessageListenerContainer<String, R>.registrySubscription(
        listener: StreamListener<String, R>, stream: String,
        group: String,
        consumerName: String
    ): Subscription {
        //创建消费组
        redisStreamUtil.createGroup(stream, group)
        return this.receive(
            Consumer.from(group, consumerName),
            StreamOffset.create(stream, ReadOffset.lastConsumed()),
            listener
        )
    }

    fun <R : Record<String, *>> StreamListener<String, R>.registrySubscription(
        container: StreamMessageListenerContainer<String, R>,
        stream: String,
        group: String,
        consumerName: String
    ): Subscription {
        return container.registrySubscription(listener = this, stream, group, consumerName)
    }
}
