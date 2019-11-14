package com.zxx.demorepository.test;

import org.junit.Test;

import java.math.BigDecimal;

/** @Auther: KAM1996 @Date: 11:19 2018-10-31 @Description: 数值测试 @Version: 1.0 */
public class ValuesTest {

  @Test
  public void bigDecimalTest() {
    BigDecimal bigDecimal = new BigDecimal("63.490295");
    double v = bigDecimal.doubleValue();
    System.out.println(v);
  }

  @Test
  public void maxInt() {
    System.out.println(Integer.MAX_VALUE);
    int i = Integer.MAX_VALUE + 2;
    System.out.println(i);
  }

  @Test
  public void maxLong() {
    System.out.println(Long.MAX_VALUE);
    long i = Long.MAX_VALUE + 2L;
    System.out.println(i);
  }


}



