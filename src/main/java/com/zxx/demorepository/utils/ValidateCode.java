package com.zxx.demorepository.utils;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;

/**
 * @auther: kam
 * @date: 9:30 2019-03-29
 * @description:
 */
public class ValidateCode {

    public static void main(String[] args) {
        //定义图形验证码的长、宽、验证码字符数、干扰元素个数
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(200, 100, 4, 20);
        //CircleCaptcha captcha = new CircleCaptcha(200, 100, 4, 20);
        //图形验证码写出，可以写出到文件，也可以写出到流
        captcha.write("d:/circle.png");
        //验证图形验证码的有效性，返回boolean值
        boolean verify = captcha.verify("1234");
        System.out.println(verify);
    }
}
