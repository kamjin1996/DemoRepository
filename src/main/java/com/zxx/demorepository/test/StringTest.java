package com.zxx.demorepository.test;

import com.timevale.mandarin.base.util.JsonUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

/**
 * @auther: tuosen
 * @date: 16:27 2019-09-03
 * @description:
 */
public class StringTest {

    @Test
    public void SBCCaseTest(){
        A a = new A();

        //半角空格
        a.setName(" ");
        //全角空格：
        a.setName("　");
        a.setName("１　２ 　　　　　　！@##￥%……&（）——+&……%");
        String json = JsonUtils.obj2json(a);
        A a1 = JsonUtils.json2pojo(json, A.class);
        System.out.println(a1.getName());
    }



}

@Data
@NoArgsConstructor
@AllArgsConstructor
class A {
    private String name;
}
