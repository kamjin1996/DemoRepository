package MyCollections.mycollections.list.linkedlist;

/**
 * @Auther: KAM1996
 * @Date: 15:40 2018-11-08
 * @Description:
 * @Version: 1.0
 */
public class MyNode<E> {
    MyNode<E> privious;
    E data;
    MyNode<E> next;

    public MyNode(MyNode<E> privious, E data, MyNode<E> next) {
        this.privious = privious;
        this.data = data;
        this.next = next;
    }
}