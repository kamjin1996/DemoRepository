package com.zxx.demorepository.test.intercept2.executor;
import com.zxx.demorepository.test.intercept2.annotation.CryptField;
import com.zxx.demorepository.test.intercept2.exception.MyRuntimeException;

import java.util.Objects;

/**
 * @auther: tuosen
 * @date: 14:12 2019-07-31
 * @description: 加解密执行者工厂
 */
public class CryptExecutorFactory {
    private static CryptExecutor COMMON_HANDLER = new CommonCryptExecutor();
    private static CryptExecutor SPECIAL_HANDLER = new SpecialCryptExecutor();

    public static CryptExecutor getTypeHandler(CryptField cryptField) {
        CryptExecutor cryptExecutor;
        if (Objects.equals(cryptField.value(), CryptType.COMMON)) {
            cryptExecutor = COMMON_HANDLER;
        } else if (Objects.equals(cryptField.value(), CryptType.SPECIAL)) {
            cryptExecutor = SPECIAL_HANDLER;
        } else {
            throw new MyRuntimeException();
        }
        return cryptExecutor;
    }

}
