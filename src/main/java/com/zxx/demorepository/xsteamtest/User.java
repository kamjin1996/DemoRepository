package com.zxx.demorepository.xsteamtest;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.converters.basic.BooleanConverter;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @auther: kam
 * @date: 10:13 2018-11-16
 * @description: 转换为xml的user
 */
@Data
@Builder
@XStreamAlias("user_user")
public class User {

    /**
     * 年龄
     */
    @XStreamAlias("age")
    private Integer age;

    /**
     * 名字
     */
    @XStreamAlias("name")
    private String name;

    /**
     * 生日
     */
    @XStreamAlias("brithday")
    @XStreamConverter(value = XDateCover.class)
    private Date brithDay;

    /**
     * 是否已婚
     */
    @XStreamAlias("married")
    @XStreamConverter(value = BooleanConverter.class, booleans = {true, false}, strings = {"yes", "no"})
    private boolean married;

    /**
     * 爱好
     */
    @XStreamAlias("hobby")
    @XStreamImplicit(itemFieldName = "hobby")
    private List<String> hobbys;

}
