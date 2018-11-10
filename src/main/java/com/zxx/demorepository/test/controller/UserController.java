package com.zxx.demorepository.test.controller;

import com.zxx.demorepository.test.entity.User;
import com.zxx.demorepository.test.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: KAM1996
 * @Date: 16:14 2018-11-08
 * @Description: 用户controller
 * @Version: 1.0
 */
@RestController
public class UserController {
    @Autowired
    private MyUserService myUserService;

    @RequestMapping("/select")
    public void select() {

        System.out.println(myUserService.selectUsers());
    }

    @RequestMapping("/del")
    public void del(){
        int id = 10;
        System.out.println(myUserService.delUser(id));
    }

    @RequestMapping("/insert")
    public void insert(){
        User user = new User();
        user.setAge(500);
        user.setUsername("xiaoli");
        user.setPassword("123");
        System.out.println(myUserService.insertUser(user));
    }


    @RequestMapping("/update")
    public void update(){
        User user = new User();
        user.setId(1);
        user.setUsername("张三");
        System.out.println(myUserService.updateUser(user));
    }
}
