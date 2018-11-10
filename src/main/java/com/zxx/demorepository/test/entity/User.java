package com.zxx.demorepository.test.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Auther: KAM1996
 * @Date: 17:55 2018-10-17
 * @Description: 用户
 * @Version: 1.0
 */


@Data
@TableName("t_user")
public class User {

    @TableField("id")
    private Integer id;

    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    @TableField("age")
    private int age;
}
