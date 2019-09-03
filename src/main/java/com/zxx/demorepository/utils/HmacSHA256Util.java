package com.zxx.demorepository.utils;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * @Description TODO
 * @Param
 * @Date 2019/5/17 11:14
 * @Create caizhihui
 */
@Slf4j
public class HmacSHA256Util {

    public static String getSign(String jsonContent,String projectSecret){
        //String secretKey = SecretKeyUtil.getSecretKey();
        String hash = "";
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(projectSecret.getBytes(), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            byte[] bytes = sha256_HMAC.doFinal(jsonContent.getBytes());
            for (int i = 0; i < bytes.length; i++) {
                hash += Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hash;
    }

}
