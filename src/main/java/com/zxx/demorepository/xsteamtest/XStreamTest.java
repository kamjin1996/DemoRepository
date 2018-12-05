package com.zxx.demorepository.xsteamtest;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import com.thoughtworks.xstream.io.xml.XppDriver;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @auther: kam
 * @date: 10:19 2018-11-16
 * @description: XStream测试
 */
public class XStreamTest {

    public static void main(String[] args) {
        List<String> hobbys = Arrays.asList("跑步", "游泳", "篮球", "滑雪");
        User user = User.builder().name("小明").age(10).brithDay(
               new Date()).hobbys(hobbys).married(true).build();
        //不加new XppDriver(new XmlFriendlyNameCoder("_-", "_"))会变成<user__user>
        // 我们想要的是<user_user>
        XStream xStream = new XStream(new XppDriver(new XmlFriendlyNameCoder("_-", "_")));
        //提醒xstream加载注解
        xStream.processAnnotations(User.class);
        String userstr = xStream.toXML(user);
        System.out.println(userstr);
    }

}
