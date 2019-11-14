package com.zxx.demorepository.test.util;

/**
 * @auther: tuosen
 * @date: 10:35 2019-09-06
 * @description:
 */
public class FloatUtil {

  public static boolean isNotBlank(Float f) {
    return !isBlank(f);
  }

  public static boolean isBlank(Float f) {
    return f == null || f == 0F;
  }
}
