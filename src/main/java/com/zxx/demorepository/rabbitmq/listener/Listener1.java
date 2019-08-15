package com.zxx.demorepository.rabbitmq.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @auther: kam
 * @date: 15:29 2018-11-22
 * @description: 接收者1
 */
//@Component
@RabbitListener(queues = "queue.1")
public abstract class Listener1 implements ListenerAble {
}
