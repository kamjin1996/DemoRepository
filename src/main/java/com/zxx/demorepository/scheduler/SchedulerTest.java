package com.zxx.demorepository.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @auther: kam
 * @date: 14:14 2018-11-22
 * @description: SpringBoot的定时任务使用
 */
//@Service
public class SchedulerTest {
    //1、在启动类注解:@EnbaleScheduling
    private int count = 0;
    //2、在方法上注解:@Scheduled
    @Scheduled(cron = "*/3 * * * * ?")
    public void test1(){
        System.out.println("this is test1! count:" + count++);
    }

    /**
     *
     * 第一次任务的结束到第二次任务的开始
     * fixedDelay = 2000
     *
     * 两次任务的开始相隔，指频率，不管第一个有没有执行完，都会执行第二个
     * fixedRate = 1000
     */
    @Scheduled(fixedDelay = 2000)
    public void test2(){
        System.out.println("this is test2! count: " + count++);
    }

}

