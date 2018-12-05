package com.zxx.demorepository.xsteamtest;

import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.converters.basic.AbstractSingleValueConverter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @auther: kam
 * @date: 11:26 2018-11-16
 * @description: XStreamDateCover
 */
public class XDateCover extends AbstractSingleValueConverter{

    private static final DateFormat DEFULT_DATEFOMART = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public boolean canConvert(Class type) {
        return type.equals(Date.class);
    }

    @Override
    public Object fromString(String str) {
        //将string转换为date类型
        try {
            return DEFULT_DATEFOMART.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        throw new ConversionException("转换失败");
    }
}
