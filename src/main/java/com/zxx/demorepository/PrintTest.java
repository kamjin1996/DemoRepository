package com.zxx.demorepository;

import org.junit.Test;

import java.util.*;

/**
 * @auther: kam
 * @date: 17:58 2018-11-22
 * @description: 打印测试
 */
public class PrintTest {

    /**
     * 打印：🙅🐞🎁
     */
    @Test
    public void character() {
        System.out.println("\uD83D\uDE45\uD83D\uDC1E\uD83C\uDF81");
    }

    /**
     * 生成1000个6位随机数
     */
    @Test
    public void random() {
        Set<Integer> nums = new HashSet<>();
        for (int i = 0; i < 1000; i++) {
            int random = new Random().nextInt(999999);
            if (random < 100000) {
                random += 100000;
            }
            nums.add(random);
        }
        if (nums.size() == 1000) {
            for (Integer num : nums) {
                System.out.println(num);
            }
        }
    }

}
