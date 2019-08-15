package com.zxx.demorepository.mycontainer.mycollections.list.arraylist;

import com.zxx.demorepository.mycontainer.mycollections.list.entity.User;

/**
 * @Auther: Kam
 * @Date: 下午 3:33 2018-10-21
 * @Description: 测试数组
 * @Version: 1.0
 */
public class MyArrayListTest {
    public static void main(String[] args) throws Exception {
        MyArrayList<User> arr = new MyArrayList();
        User u1 = new User(1, "小黑", 20);
        User u2 = new User(2, "小明", 75);
        User u3 = new User(4, "小李", 16);
        arr.add(u1);
        arr.add(u2);
        arr.add(u3);
        arr.add(u1);
        //arr.add(u2);
        //arr.add(u3);
        //arr.add(u1);
        //arr.add(u2);
        //arr.add(u3);
        //arr.add(u1);
        //arr.add(u2);
        //arr.add(u3);
        //arr.remove(1);
        System.out.println(arr.size());
        System.out.println(arr.get(1));
        //System.out.println(arr.remove(u2));
        System.out.println(arr.size());
        System.out.println(arr.LastIndexOf(u1));
    }


}
