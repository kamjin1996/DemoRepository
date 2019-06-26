package com.zxx.demorepository.annotition;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther: tuosen
 * @date: 15:15 2019-06-26
 * @description:
 */
public class exec {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, InvocationTargetException {

        Class myTestClass = MyTest.class;
        Object obj = myTestClass.newInstance();

        //方法处理
        Method[] methods = myTestClass.getMethods();
        for (Method m : methods) {
            boolean b = m.isAnnotationPresent(Execute.class);
            System.out.printf("是否被注解:%4s,函数名称是:%s \n", b, m.getName());
            if (b) {
                m.invoke(obj, (Object[]) null);
            }
        }

        //变量处理
        Field[] fields = myTestClass.getDeclaredFields();
        List<Field> list = new ArrayList<>();
        for (Field f : fields) {
            f.setAccessible(Boolean.TRUE);
            boolean b = f.isAnnotationPresent(TheIntegerLessToZero.class);
            if (b) {
                Class<?> type = f.getType();
                //System.out.println(type);
                if (type.equals(int.class)) {
                    Integer i = (Integer) f.get(obj);
                    TheIntegerLessToZero annotation = f.getAnnotation(TheIntegerLessToZero.class);
                    int value = annotation.value();
                    System.out.printf("变量的值是: %s ", i);
                    System.out.printf(",注解的值是: %s \n", value);
                    if (i < value) {
                        System.out.printf("变量:%s小于:%s，被置为0了 \n", f.getName(), value);
                        f.set(obj, 0);
                        list.add(f);
                    }
                }
            }
        }
        list.forEach(item -> {
            try {
                System.out.printf("被置为0的变量名称:%s", item.getName() + ":" + item.get(obj));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });

        System.out.println("当前对象的值：" + obj.toString());

    }

}
