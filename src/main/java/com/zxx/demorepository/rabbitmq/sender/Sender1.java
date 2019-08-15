package com.zxx.demorepository.rabbitmq.sender;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @auther: kam
 * @date: 15:28 2018-11-22
 * @description: 发送者1
 */
@Component
public abstract class Sender1 extends SenderAble {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    String msg = "i am sender1!~";

    @Override
    public void send() {
        rabbitTemplate.convertAndSend("queue.1", msg);
    }

}
