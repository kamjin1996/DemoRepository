package com.zxx.demorepository.test;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @auther: kam
 * @date: 13:54 2018-12-07
 * @description: 域混乱测试
 */

public class RegionTest {

    public RegionTest(float smallNum){
        this.smallNum = smallNum;
    }

    float smallNum;

}

class RegionTest2{
    public static void main(String[] args) {
        RegionTest regionTest = new RegionTest(9F);
        RegionTest regionTest1 = new RegionTest(47F);
        System.out.println(" regionTest.smallNum:"+regionTest.smallNum +" regionTest1.smallNum:"+regionTest1.smallNum);
        //regionTest = regionTest1;
        regionTest.smallNum = regionTest1.smallNum;
        System.out.println(" regionTest.smallNum:"+regionTest.smallNum +" regionTest1.smallNum:"+regionTest1.smallNum);
        regionTest1.smallNum = 10;
        System.out.println(" regionTest.smallNum:"+regionTest.smallNum +" regionTest1.smallNum:"+regionTest1.smallNum);
    }
}
