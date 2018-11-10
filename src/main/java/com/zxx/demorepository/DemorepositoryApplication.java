package com.zxx.demorepository;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
@MapperScan("com.zxx.demorepository.test.mapper")
public class DemorepositoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemorepositoryApplication.class, args);
    }
}
