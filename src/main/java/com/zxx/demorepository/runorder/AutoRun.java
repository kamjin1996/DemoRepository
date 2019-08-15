package com.zxx.demorepository.runorder;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @auther: tuosen
 * @date: 11:10 2019-06-26
 * @description: 应用启动后自动运行内容测试
 */
//@Component
public class AutoRun implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("程序启动了");
    }
}
