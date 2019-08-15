package com.zxx.demorepository.rabbitmq.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @auther: tuosen
 * @date: 15:50 2019-07-26
 * @description:
 */
@Component
public interface ListenerAble extends com.zxx.demorepository.rabbitmq.RabbitMqAble {
    @Override
    default void recived(String msg) {
        System.out.println(msg);
    }
}
