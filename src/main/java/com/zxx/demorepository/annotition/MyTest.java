package com.zxx.demorepository.annotition;

/**
 * @auther: tuosen
 * @date: 15:16 2019-06-26
 * @description:
 */
public class MyTest {

    @TheIntegerLessToZero(value = 8)
    private int a = 9;

    @TheIntegerLessToZero(value = 30)
    private int b = 20;

    @TheIntegerLessToZero
    private int c = 16;

    @Execute
    public void test1() {
        System.out.println("test1执行了!!!");
    }

    public void test2() {
        System.out.println("test2执行了!!!");
    }

    @Override
    public String toString() {
        return "MyTest{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                '}';
    }
}
