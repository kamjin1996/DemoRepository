package com.zxx.demorepository.test;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 * @auther: tuosen
 * @date: 14:46 2019-08-05
 * @description:
 */
public class ClassLoaderTest {



    @Test
    public void sss() {
        try {
            Properties props = new Properties();
            props.load(new InputStreamReader(ClassLoaderTest.class.getClassLoader().getResourceAsStream("application.properties"), "UTF-8"));
            String envFile = "application.properties";
            String activeProfile = props.getProperty("spring.profiles.active");
            if (StringUtils.isNotBlank(activeProfile)) {
                envFile = "application-" + activeProfile + ".properties";
            }

            Properties envProps = new Properties();

            envProps.load(new InputStreamReader(ClassLoaderTest.class.getClassLoader().getResourceAsStream(envFile), "UTF-8"));

            Field[] fields = ClassLoaderTest.class.getFields();
            Field[] var5 = fields;
            int var6 = fields.length;

            for (int var7 = 0; var7 < var6; ++var7) {
                Field field = var5[var7];
                field.set((Object) null, envProps.getProperty(field.getName().replace("_", ".").toLowerCase()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
