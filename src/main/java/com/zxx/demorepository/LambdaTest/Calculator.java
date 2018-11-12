package com.zxx.demorepository.LambdaTest;

/**
 * @Auther: KAM1996
 * @Date: 11:28 2018-11-10
 * @Description: Lambda表达式语法测试
 * @Version: 1.0
 */
public class Calculator {
    interface IntegerMath {
        int operation(int a, int b);
    }

    public int operatBinary(int a, int b, IntegerMath in) {
        return in.operation(a, b);
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        //操作本身即定义实例
        IntegerMath addition = (a, b) -> a + b;
        IntegerMath subtraction = (a, b) -> a - b;
        System.out.println(
                calculator.operatBinary(10,20,addition)
        );
        System.out.println(
                calculator.operatBinary(10,20,subtraction)
        );
    }
}