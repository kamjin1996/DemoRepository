package com.zxx.demorepository.rabbitmq;

import com.zxx.demorepository.rabbitmq.sender.Sender1;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @auther: kam
 * @date: 15:30 2018-11-22
 * @description: 发送测试类
 */
//本项目未配置，需要配置实际rabbitMQ安装地址及端口信息：
    /*spring.application.name=Spring-boot-rabbitmq

            spring.rabbitmq.host=192.168.0.86
            spring.rabbitmq.port=5672
            spring.rabbitmq.username=admin
            spring.rabbitmq.password=123456
            */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {

    @Autowired
    private Sender1 sender1;

    @org.junit.Test
    public void sender1Test(){
        this.sender1.send();
    }

}
