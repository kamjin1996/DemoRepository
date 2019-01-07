package com.zxx.demorepository;

import org.junit.Test;

import java.io.*;
import java.util.*;

/**
 * @auther: kam
 * @date: 17:40 2019-01-03
 * @description: 数值测试
 */
public class NumTest {

    /**
     * 问题1：
     * 1-1000放在含有1001个元素的数组中，只有唯一的一个元素值重复，其它均只出现
     * 一次。每个数组元素只能访问一次，设计一个算法，将它找出来；不用辅助存储空
     * 间，设计一个算法将其实现:
     * 算法：
     * 将所有的数全部异或，得到的结果与1^2^3^...^1000的结果进行异或，得到的结果就是重复数。
     */
    @Test
    public void xorTest() {
        //生成1000个数字
        List<Integer> numList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            numList.add(i);
        }
        numList.add(108);// 重复值
        int temp = 0;// 异或储存值
        Integer currentNum = 0;// 当前值
        Integer nextNum = 0;// 计数值

        for (int j = 0; j < 1000; j++) {
            currentNum = temp;
            nextNum = numList.get(j + 1);
            temp = currentNum ^ nextNum;
        }
        System.out.println(temp);
    }

    //自增测试1
    @Test
    public void foo() {
        int i = 10;
        try {
            System.out.println("A: " + i);
            i = --i / 0;
            System.out.println("B: " + i);//未执行
            i--;
        } catch (Exception e) {
            System.out.println("C: " + i);
            i = i-- / 0;
            System.out.println("D: " + i);//未执行
            --i;
        } finally {
            System.out.println("E: " + i);
            --i;
            System.out.println("F: " + i);
            i--;
            System.out.println("G: " + i);
        }
    }

    //自增测试2
    @Test
    public void increment() {
        int i = 0;
        int j = i++ + ++i;//0 + 2
        int k = --i + i--;//1 + 1
        System.out.println("i: " + i);
        System.out.println("j: " + j);
        System.out.println("k: " + k);
    }

    /**
     * 对象序列化为文件测试
     */
    @Test
    public void setSerilezibleTest() {
        Set<Integer> set = new HashSet<>();
        set.add(11111111);
        set.add(222222222);
        System.out.println(set);
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("d:/set.txt"));
            outputStream.writeObject(set);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //set.clear();
        System.out.println(set);
        Object o = null;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("d:/set.txt"));
            o = objectInputStream.readObject();
            System.out.println(o);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(Objects.equals(set, o));
        Set<Integer> o1 = (Set<Integer>) o;
        System.out.println(Objects.equals(set, o1));
    }
}
