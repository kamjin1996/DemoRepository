package com.zxx.demorepository.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

/**
 * @auther: tuosen
 * @date: 15:47 2019-09-05
 * @description: 字符编码集工具类
 */
@Slf4j
public class CharsetUtil {

    private static Iterator<String> charsetIterator;

    /**
     * 将任何字符转成对应编码集
     *
     * @param str
     * @param charsetName
     * @return
     */
    public static String encode(String str, String charsetName) {
        try {
            return new String(str.getBytes(CharsetUtil.getCharsetName(str)), charsetName);
        } catch (UnsupportedEncodingException e) {
            log.error("字符串：{},字符集转换为：{}失败", str, charsetName);
        }
        return str;
    }

    /**
     * 获取字符的编码集
     *
     * @param str
     * @return
     */
    public static String getCharsetName(String str) {
        charsetIterator = Arrays.asList("GBK", "GB2312", "UTF-8", "ISO-8859-1").iterator();
        return matchCharsetName(str, charsetIterator.next());
    }

    private static String matchCharsetName(String str, String charsetName) {
        if (StringUtils.isNotBlank(str)) {
            try {
                boolean equals = Objects.equals(str, new String(str.getBytes(charsetName), charsetName));
                return equals ? charsetName : matchCharsetName(str, charsetIterator.next());
            } catch (Exception ignored) {
            }
        }
        return "";
    }

    public static void main(String[] args) {
        String response = "响应内容响应内容响应内容";
        System.out.println("原字符: " + response);

        String responseCharsetName = CharsetUtil.getCharsetName(response);
        System.out.println("原编码集: " + responseCharsetName);

        String coverterAfter = "UTF-8";
        String encode = CharsetUtil.encode(response, coverterAfter);
        System.out.println("新字符: " + encode);
        System.out.println("新编码集: " + coverterAfter);
    }
}
