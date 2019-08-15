package com.zxx.demorepository.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;

/**
 * @auther: tuosen
 * @date: 16:09 2019-07-26
 * @description:
 */
public interface RabbitMqAble {
    @RabbitHandler
    void recived(String msg);

    void send();
}
