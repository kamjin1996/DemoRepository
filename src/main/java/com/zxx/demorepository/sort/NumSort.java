package com.zxx.demorepository.sort;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @auther: tuosen
 * @date: 14:45 2019-08-09
 * @description: 数字排序
 */
public class NumSort {

    int[] arr = {126, 844, 121, 444, 741, 62, 231, 0, 56456, 658, 22, 238, 1313};

    @Test
    public void bubbleSort() {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        for (int i : arr) {
            System.out.println(i);
        }
    }

    @Test
    public void sum() {
        int n = 1;
        System.out.println(sum(n));
    }

    private int sum(int n) {
        if (n == 1) {
            return 1;
        } else if (n > 1) {
            return n + sum(n - 1);
        } else {
            return 0;
        }

    }

    @Test
    public void ss(){
        Integer a = 17504;
        Integer b = 6;

        int i = a >>> b & 1;
        System.out.println(i);
    }
}
