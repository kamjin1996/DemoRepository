package com.zxx.demorepository.test.intercept2.resolver;

import com.zxx.demorepository.test.intercept2.handler.CryptHandlerFactory;

/**
 * 简单加密处理者
 *
 * @author junliang.zhuo
 * @date 2019-03-29 13:12
 */
public class SimpleMethodEncryptResolver implements MethodEncryptResolver {

    @Override
    public Object processEncrypt(Object param) {
        return CryptHandlerFactory.getCryptHandler(param, null).encrypt(param, null);
    }
}