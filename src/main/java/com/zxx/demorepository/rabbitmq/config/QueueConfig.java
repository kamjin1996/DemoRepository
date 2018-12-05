package com.zxx.demorepository.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @auther: kam
 * @date: 15:35 2018-11-22
 * @description: 队列配置类
 */
@Configuration
public class QueueConfig {

    //返回一个名字叫queue1的队列
    @Bean
    public Queue queue1() {
        return new Queue("queue.1");
    }

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange("topic.exchange1");
    }


    @Bean
    Binding bindingQueue1() {
        return BindingBuilder.bind(queue1()).to(topicExchange()).with("queue.1");
    }
//_________________________________________________________________________________________
    @Bean
    public Queue queue2(){
        return new Queue("queue.2");
    }

    @Bean
    TopicExchange topicExchange2(){
        return new TopicExchange("topic.exchange2");
    }

    @Bean
    Binding bindingQueue2() {
        return BindingBuilder.bind(queue2()).to(topicExchange2()).with("queue.2");
    }

}
