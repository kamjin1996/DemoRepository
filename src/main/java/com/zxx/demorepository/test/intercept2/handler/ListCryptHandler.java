package com.zxx.demorepository.test.intercept2.handler;
import com.zxx.demorepository.test.intercept2.annotation.CryptField;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 处理 List 对象的加解密
 *
 * @author junliang.zhuo
 * @date 2019-03-29 11:40
 */
public class ListCryptHandler implements CryptHandler<List> {

    @Override
    public Object encrypt(List list, CryptField cryptField) {
        if (!needCrypt(list)) {
            return list;
        }
        return list.stream()
            .map(item -> CryptHandlerFactory.getCryptHandler(item, cryptField).encrypt(item, cryptField))
            .collect(Collectors.toList());
    }

    @Override
    public Object decrypt(List param, CryptField cryptField) {
        if (!needCrypt(param)) {
            return param;
        }
        return param.stream()
            .map(item -> CryptHandlerFactory.getCryptHandler(item, cryptField).decrypt(item, cryptField))
            .collect(Collectors.toList());
    }

    private boolean needCrypt(List list) {
        return list != null && list.size() != 0;
    }
}
