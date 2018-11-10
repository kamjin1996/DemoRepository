package com.zxx.demorepository.test.common.impl;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Auther: KAM1996
 * @Date: 14:42 2018-10-18
 * @Description: 基础服务实现类
 * @Version: 1.0
 */
public class BaseServiceImpl<M extends BaseMapper<T>,T> extends ServiceImpl<M,T> implements IService<T> {

}
