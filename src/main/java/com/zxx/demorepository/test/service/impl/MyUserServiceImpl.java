package com.zxx.demorepository.test.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.EmptyWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.rabbitmq.client.UnblockedCallback;
import com.zxx.demorepository.test.common.impl.BaseServiceImpl;
import com.zxx.demorepository.test.entity.User;
import com.zxx.demorepository.test.mapper.UserMapper;
import com.zxx.demorepository.test.service.MyUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: KAM1996
 * @Date: 11:30 2018-10-18
 * @Description: 测试实现
 * @Version: 1.0
 */

@Service
public class MyUserServiceImpl extends BaseServiceImpl<UserMapper,User> implements MyUserService {
    @Override
    public boolean insertUser(User user) {

        return this.save(user);
    }

    @Override
    public boolean delUser(Integer id) {
        return this.removeById(id);
    }

    @Override
    public List<User> selectUsers() {
        return this.list(null);
    }

    @Override
    public boolean updateUser(User user) {
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq("id",user.getId());
        return this.update(user,wrapper);
    }

    @Override
    public boolean updateUser2(User user) {
        return this.baseMapper.update(user,new UpdateWrapper<User>().like("age",5))>0;
    }
}
