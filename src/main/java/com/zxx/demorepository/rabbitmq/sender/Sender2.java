package com.zxx.demorepository.rabbitmq.sender;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @auther: kam
 * @date: 15:28 2018-11-22
 * @description: 发送者2
 */
@Component
public class Sender2 {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(){
        rabbitTemplate.convertAndSend("queue.2","i am sender2!~");
    }
}
