package com.zxx.demorepository.test.intercept2.annotation;
import com.zxx.demorepository.test.intercept2.executor.CryptType;

import java.lang.annotation.*;

/**
 * 加解密注解
 *
 * @author junliang.zhuo
 * @date 2019-03-29 14:49
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD})
public @interface CryptField {

    CryptType value() default CryptType.COMMON;

    boolean encrypt() default true;

    boolean decrypt() default true;
}

