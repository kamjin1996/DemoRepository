package com.zxx.demorepository.test;

import GOF.ConstructionSchema.$Adapter.common.Source;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.PatternMatchUtils;

import java.util.regex.Pattern;

/**
 * @auther: kam
 * @date: 16:58 2019-07-05
 * @description: 临时测试
 */

public class TempTest {
    String source = "";

    @Before
    public void init() {
        source = "2019年02月12日";
        //source = "2019-02-12";
        //source = "2018/02/12";
    }

    @Test
    public void dateparestest() {
        String source = this.source;
        soutr(source);
    }

    @Test
    public void dateRegexTest() {
        String source = this.source;
        String regex1 = "^\\d{4}年\\d{2}月\\d{2}日$";
        String regex2 = "^\\d{4}-\\d{2}-\\d{2}$";
        String regex3 = "^\\d{4}/\\d{2}/\\d{2}$";

        boolean match = Pattern.compile(regex1).matcher(source).matches()
                || Pattern.compile(regex2).matcher(source).matches()
                || Pattern.compile(regex3).matcher(source).matches();
        System.out.println("是否有匹配的: " + match);

    }

    private void soutr(String source) {
        System.out.println(source.substring(0, 4));
        System.out.println(source.substring(5, 7));
        System.out.println(source.substring(8, 10));
    }


}
