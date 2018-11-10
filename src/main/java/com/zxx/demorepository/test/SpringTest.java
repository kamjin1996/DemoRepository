package com.zxx.demorepository.test;

import com.zxx.demorepository.DemorepositoryApplication;
import com.zxx.demorepository.test.entity.User;
import com.zxx.demorepository.test.service.MyUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Auther: KAM1996
 * @Date: 15:52 2018-10-18
 * @Description: junit测试
 * @Version: 1.0
 */
@ContextConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemorepositoryApplication.class)
public class SpringTest {

    @Value("${msg}")
    private String msg;

    @Test
    public void testMsg(){
        System.out.println(this.msg);
    }



    //新增
    @Test
    public void insert() {

    }

    //修改
    @Test
    public void update() {

    }

}
