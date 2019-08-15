package com.zxx.demorepository.test.intercept2.resolver;

/**
 * 表示方法不需要加密
 *
 * @author junliang.zhuo
 * @date 2019-03-29 13:12
 */
public class EmptyMethodEncryptResolver implements MethodEncryptResolver {

    @Override
    public Object processEncrypt(Object param) {
        return param;
    }

}