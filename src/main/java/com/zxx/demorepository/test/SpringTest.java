package com.zxx.demorepository.test;

import com.zxx.demorepository.test.entity.User;
import com.zxx.demorepository.test.service.MyUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.List;
import java.util.function.Predicate;

/**
 * @Auther: KAM1996
 * @Date: 15:52 2018-10-18
 * @Description: junit测试
 * @Version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringTest {

    @Value("${msg}")
    private String msg;

    @Autowired
    private MyUserService myUserService;

    @Test
    public void testMsg() {
        System.out.println(this.msg);
    }

    //新增
    @Test
    public void insert() {
        User user = User.builder().age(10).username("李四").password("123456").build();
        this.myUserService.insertUser(user);
    }

    //修改
    @Test
    public void update1() {
        User user = User.builder().age(8).username("李四").build();
        this.myUserService.updateUser2(user);
    }
    //查找
    @Test
    public void find() {
        List<User> users = this.myUserService.selectUsers();
        Predicate<User> predicate = user -> user.getAge() > 8;
        users.stream().filter(predicate).forEach(System.out::println);

    }

    //删除
    @Test
    public void del(){
        this.myUserService.delUser(9);
    }

}
