package com.zxx.demorepository.test.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zxx.demorepository.test.common.BaseModel;
import com.zxx.demorepository.test.intercept2.annotation.CryptField;
import lombok.*;

/**
 * @Auther: KAM1996
 * @Date: 17:55 2018-10-17
 * @Description: 用户
 * @Version: 1.0
 */


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_user")
@ToString
public class User extends BaseModel {

    @TableId(value = "id",type = IdType.INPUT)
    private Integer id;

    @CryptField
    @TableField("username")
    private String username;

    @CryptField
    @TableField("password")
    private String password;

    @TableField("age")
    private int age;
}
