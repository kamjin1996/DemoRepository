package com.zxx.demorepository.runorder;

import org.springframework.beans.BeansException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @auther: tuosen
 * @date: 11:26 2019-06-26
 * @description:
 */
@Component
public class OrderRun implements CommandLineRunner, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("验证order注解>>>");
        Result result = applicationContext.getBean(Result.class);
        result.print();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
