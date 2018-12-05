package com.zxx.demorepository.rabbitmq.listener;

/**
 * @auther: kam
 * @date: 15:29 2018-11-22
 * @description: 接收者1
 */
//@Component
public class Listener1 {

    //@RabbitListener(queues = "queue.1")
    public void recived(String msg){
        System.out.println(msg);
    }

}
