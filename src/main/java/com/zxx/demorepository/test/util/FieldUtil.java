package com.zxx.demorepository.test.util;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Objects;

/**
 * @auther: tuosen
 * @date: 17:54 2019-08-16
 * @description: 字段工具
 */
public class FieldUtil {

  /**
   * 修改变量的值
   *
   * @param cls
   * @param obj
   * @param filedName
   * @param val
   */
  public static void modify(Class cls, Object obj, String filedName, String val)
      throws NoSuchFieldException, IllegalAccessException {
    if (StringUtils.isBlank(filedName) || StringUtils.isBlank(val)) {
      throw new IllegalArgumentException("filedName or val not be null");
    }
    Field field = null;
    if (Objects.nonNull(cls)) {
      field = cls.getDeclaredField(filedName);
    }
    if (Objects.isNull(cls) && Objects.nonNull(obj)) {
      field = obj.getClass().getDeclaredField(filedName);
    }
    if (Objects.isNull(field) || !assignmentWithFinal(obj, field, val)) {
      throw new RuntimeException("by reflection get field fall");
    }
  }

  /**
   * 为字段赋值（final类型）
   *
   * @param instance
   * @param field
   * @param val
   */
  private static boolean assignmentWithFinal(Object instance, Field field, Object val)
      throws IllegalAccessException {
    boolean isRemoveFinal = removeFinalModifier(field);
    boolean assignmentField = assignment(instance, field, val);
    if (isRemoveFinal) {
      addFinalModifier(field);
    }
    return assignmentField;
  }

  /**
   * 给字段赋值
   *
   * @param instance
   * @param field
   * @param val
   * @return
   */
  private static boolean assignment(Object instance, Field field, Object val)
      throws IllegalAccessException {
    field.setAccessible(Boolean.TRUE);
    field.set(Objects.nonNull(instance) ? instance : null, val);
    return true;
  }

  /**
   * 去掉final修饰符
   *
   * @param field
   * @return
   */
  private static boolean removeFinalModifier(Field field) {
    try {
      Field modifiers = field.getClass().getDeclaredField("modifiers");
      modifiers.setAccessible(Boolean.TRUE);
      modifiers.setInt(field, field.getModifiers() & ~Modifier.FINAL);
      return true;
    } catch (Exception e) {
      // 忽略
      return false;
    }
  }

  /**
   * 添加final修饰符
   *
   * @param field
   */
  private static boolean addFinalModifier(Field field) {
    try {
      Field modifiers = field.getClass().getDeclaredField("modifiers");
      modifiers.setInt(field, field.getModifiers() & ~Modifier.FINAL);
      return true;
    } catch (Exception e) {
      // 忽略
      return false;
    }
  }
}
