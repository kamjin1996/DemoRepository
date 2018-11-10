package MyCollections.mycollections.list.linkedlist;

import MyCollections.mycollections.list.entity.User;

/**
 * @Auther: Kam
 * @Date: 下午 5:32 2018-10-21
 * @Description: 测试MyLinkedList
 * @Version: 1.0
 */
public class MyLinkedListTest {
    public static void main(String[] args) throws Exception {
        MyLinkedList<User> myLinkedList = new MyLinkedList();
        User u1 = new User(1, "小黑", 20);
        User u2 = new User(2, "小明", 75);
        User u3 = new User(3, "小白", 12);
        User u4 = new User(4, "小黄", 45);
        User u5 = new User(5, "小绿", 65);
        User u6 = new User(6, "小红", 34);
        User u7 = new User(7, "小李", 19);
        myLinkedList.add(u1);
        myLinkedList.add(u2);
        myLinkedList.add(u3);
        myLinkedList.add(u4);
        myLinkedList.add(u5);
        myLinkedList.add(u6);
        myLinkedList.add(u7);
        //myLinkedList.remove(u2);
        System.out.println(myLinkedList.get(4));
        System.out.println(myLinkedList.getFirst());
        System.out.println(myLinkedList.getLast());
        System.out.println(myLinkedList.size());
    }

}
