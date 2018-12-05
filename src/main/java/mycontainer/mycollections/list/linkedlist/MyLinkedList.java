package mycontainer.mycollections.list.linkedlist;

/**
 * @Auther: Kam
 * @Date: 下午 2:13 2018-10-21
 * @Description: 自己实现Linkedlist
 * @Version: 1.0
 */
public class MyLinkedList<E> {
    private MyNode<E> first;
    private MyNode<E> last;

    public MyLinkedList() {
    }

    private int size;

    /**
     * 根据下标获取数据
     *
     * @param index
     * @return
     * @throws Exception
     */
    public E get(int index) throws Exception {
        return MyNode(index).data;
    }

    /**
     * 添加数据，由于是双向链表，所以不考虑容量问题
     *
     * @param e
     * @return
     */
    public boolean add(E e) {
        linkedLast(e);
        return true;
    }

    /**
     * 向末尾添加数据
     *
     * @param e
     */
    public void addLast(E e) {
        linkedLast(e);
    }

    /**
     * 向头部添加数据
     *
     * @param e
     */
    public void addFirst(E e) {
        linkedFirst(e);
    }

    /**
     * 得到第一个节点数据
     *
     * @return
     * @throws Exception
     */
    public E getFirst() throws Exception {
        MyNode<E> f = first;
        if (f == null) {
            throw new Exception("没有元素");
        }
        return f.data;
    }

    /**
     * 得到末尾节点的数据
     *
     * @return
     * @throws Exception
     */
    public E getLast() throws Exception {
        MyNode<E> l = last;
        if (l == null) {
            throw new Exception("没有元素");
        }
        return l.data;
    }

    /**
     * 连接至第一个元素
     *
     * @param e
     */
    private void linkedFirst(E e) {
        MyNode<E> firstMyNode = first;
        MyNode<E> newMyNode = new MyNode<E>(null, e, firstMyNode);
        first = newMyNode;

        if (firstMyNode == null) {
            firstMyNode = newMyNode;
        } else {
            firstMyNode.privious = newMyNode;
        }
        size++;
    }

    /**
     * 连接至最后的元素
     *
     * @param e
     */
    private void linkedLast(E e) {
        MyNode<E> lastMyNode = last;
        //生成新的节点
        MyNode<E> newMyNode = new MyNode<E>(lastMyNode, e, null);
        //新的赋值给last节点作为last
        last = newMyNode;
        if (lastMyNode == null) {
            first = newMyNode;
        } else {
            lastMyNode.next = newMyNode;
        }
        size++;
    }

    /**
     * 当前集合大小
     *
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * 将节点从它的位置向前后解除链关系
     *
     * @param x
     * @return
     */
    E unlink(MyNode<E> x) {
        E element = x.data;
        MyNode<E> next = x.next;
        MyNode<E> privious = x.privious;
        if (privious == null) {
            first = next;
        } else {
            privious.next = next;
            x.privious = null;
        }

        if (next == null) {
            last = privious;
        } else {
            next.privious = privious;
            x.next = null;
        }
        x.data = null;
        size--;
        return element;
    }

    /**
     * 移除数据
     *
     * @param obj
     * @return
     */
    public boolean remove(Object obj) {
        if (obj == null) {
            //遍历节点，找到节点的data为null的节点
            for (MyNode<E> n = first; n != null; n = n.next) {
                if (n.data == null) {
                    unlink(n);
                    return true;
                }
            }
        } else {
            for (MyNode<E> n = first; n != null; n = n.next) {
                if (obj.equals(n.data)) {
                    unlink(n);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 检查下标是否合理
     *
     * @param index
     * @throws Exception
     */
    void checkIndex(int index) throws Exception {
        if (index > size && index < 0) {
            throw new Exception("下标错误");
        }
    }

    /**
     * 根据下标查找节点
     *
     * @param index
     * @return
     * @throws Exception
     */
    private MyNode<E> MyNode(int index) throws Exception {
        checkIndex(index);
        //为了提高效率，这里采用二分法
        //注意这里的括号，先进行除以2（即移位1），在进行比较
        if (index < (size >> 1)) {
            //说明在前边，则从前到后进行遍历
            MyNode<E> n = first;
            for (int i = 0; i < index; i++) {
                //下一个即是元素
                n = n.next;
            }
            return n;
        } else {
            //反之在后边，从后边向前边遍历
            MyNode<E> n = last;
            for (int i = size - 1; i > index; i--) {
                //上一个即是元素
                n = n.privious;
            }
            return n;
        }
    }

}

