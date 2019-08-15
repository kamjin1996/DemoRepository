package com.zxx.demorepository.test.intercept2.executor;

/**
 * @auther: tuosen
 * @date: 14:13 2019-07-31
 * @description: 普通加密解密执行者
 */
public class CommonCryptExecutor implements CryptExecutor {

    @Override
    public String encrypt(String str) {
        return "普通加密"+str;
    }

    @Override
    public String decrypt(String str) {
        return "普通解密"+str;
    }
}
