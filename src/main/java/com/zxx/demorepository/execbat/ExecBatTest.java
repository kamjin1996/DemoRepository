package com.zxx.demorepository.execbat;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @auther: kam
 * @date: 11:03 2019-06-03
 * @description: bat文件测试
 */
public class ExecBatTest {

    private static final String cmd = "cmd /c start /b";

    private static final String path = (ClassLoader.getSystemResource("") + "test1.bat").replace("file:/", "");

    private StringBuilder bat;

    private static final List<String> params = Lists.newArrayList("111", "12", "14", "156");

    public void init() {
        System.out.println(path);
        bat = new StringBuilder();
        if (StringUtils.isNotBlank(cmd)) {
            bat.append(cmd);
            bat.append(" ");
        }

        if (StringUtils.isNotBlank(path)) {
            bat.append(path);
            bat.append(" ");
        }

        if (!CollectionUtils.isEmpty(params)) {
            for (String param : params) {
                bat.append(param);
                bat.append(" ");
            }
        }
    }

    public static void main(String[] args) {
        ExecBatTest batTest = new ExecBatTest();
        batTest.init();
        batTest.cmd();
    }

    public void cmd() {
        try {
            Process process = Runtime.getRuntime().exec(bat.toString());
            //Desktop.getDesktop().open(new File( path.toString()));
            System.out.println("CMD执行>>>>>>>>>");
            process.waitFor();

            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), Charset.forName("GBK")));
            this.readAndPrint(br);
            System.out.println("CMD执行情况: " + (process.exitValue() == 1 ? Boolean.TRUE : Boolean.FALSE));
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void readAndPrint(BufferedReader br) throws IOException {
        String line = null;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
    }
}
