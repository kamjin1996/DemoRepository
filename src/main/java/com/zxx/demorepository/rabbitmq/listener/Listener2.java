package com.zxx.demorepository.rabbitmq.listener;

/**
 * @auther: kam
 * @date: 15:29 2018-11-22
 * @description: 接收者2
 */
//@Component
//@RabbitListener(queues = "queue.2")
public class Listener2 {

    //@RabbitHandler
    public void recived2(String msg){
        System.out.println(msg);
    }
}
