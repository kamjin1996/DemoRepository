package com.zxx.demorepository.test.intercept2.executor;

/**
 * @auther: tuosen
 * @date: 14:15 2019-07-31
 * @description: 特殊加解密执行者
 */
public class SpecialCryptExecutor implements CryptExecutor {

    @Override
    public String encrypt(String str) {
        return "特殊加密" + str;
    }

    @Override
    public String decrypt(String str) {
        return "特殊解密" + str;
    }
}
