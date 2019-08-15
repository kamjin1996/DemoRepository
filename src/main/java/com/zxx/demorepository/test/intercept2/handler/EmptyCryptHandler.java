package com.zxx.demorepository.test.intercept2.handler;

import com.zxx.demorepository.test.intercept2.annotation.CryptField;

/**
 * 空的加解密执行者，避免过多空指针判断
 *
 * @author junliang.zhuo
 * @date 2019-03-29 11:40
 */
public class EmptyCryptHandler implements CryptHandler<Object> {

    @Override
    public Object encrypt(Object param, CryptField cryptField) {
        return param;
    }

    @Override
    public Object decrypt(Object param, CryptField cryptField) {
        return param;
    }
}
