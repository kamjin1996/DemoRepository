package com.zxx.demorepository.test;

import com.zxx.demorepository.test.util.QRCodeUtil;
import org.junit.Test;

/**
 * @auther: kam
 * @date: 14:21 2018-12-07
 * @description: 别名问题测试
 */
public class AliasTest {

  @Test
  public void test() {
      String content = "奥术大师";
      String logoImgPath = "D:\\微信图片_20190905174346.png";
      String destPath = "D:\\qrcode.png";

      try {
          QRCodeUtil.encode(content, logoImgPath, destPath,true);
      } catch (Exception e) {
          e.printStackTrace();
      }
  }
}
