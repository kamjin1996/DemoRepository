package com.zxx.demorepository.annotition;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @auther: tuosen
 * @date: 14:28 2019-06-26
 * @description: 将会执行
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Execute {

}
