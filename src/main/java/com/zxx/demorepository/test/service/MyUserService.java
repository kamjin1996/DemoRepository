package com.zxx.demorepository.test.service;



import com.zxx.demorepository.test.common.BaseService;
import com.zxx.demorepository.test.entity.User;

import java.util.List;

/**
 * @Auther: KAM1996
 * @Date: 11:16 2018-10-18
 * @Description: 测试
 * @Version: 1.0
 */
public interface MyUserService extends BaseService<User> {

    /**
     *     crud
     */

    boolean insertUser(User user);

    boolean delUser(Integer id);

    List<User> selectUsers();

    boolean updateUser(User user);
}
