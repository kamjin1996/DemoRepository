package com.zxx.demorepository.test;

import java.math.BigDecimal;

/**
 * @Auther: KAM1996
 * @Date: 11:19 2018-10-31
 * @Description: 数值测试
 * @Version: 1.0
 */
public class ValuesTest {

    public static void main(String[] args) {
        BigDecimal bigDecimal = new BigDecimal("63.490295");
        double v = bigDecimal.doubleValue();
        System.out.println();
    }
}
