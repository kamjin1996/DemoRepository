package com.zxx.demorepository.mycontainer.mycollections.list.arraylist;


/**
 * @Auther: Kam
 * @Date: 下午 2:13 2018-10-21
 * @Description: 自己实现Arraylist，待实现截取
 * @Version: 1.0
 */
public class MyArrayList<E> {
    private static final int INITDEFAULT_CAP = 10;

    private static final int GROW_NUM = 2;

    private int size;

    private transient Object[] elementData;


    public MyArrayList() {
        this.elementData = new Object[INITDEFAULT_CAP];
    }

    public MyArrayList(int init_cap) {
        this.elementData = new Object[init_cap];
    }

    /**
     * MyArrayList数组大小
     *
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * 拓容大小
     */
    private void grow() {
        MyArrayList newArr = null;
        if (size == elementData.length) {
            newArr = new MyArrayList(size * GROW_NUM);
            System.arraycopy(elementData, 0, newArr.elementData, 0, size);
            this.elementData = newArr.elementData;
        }
    }

    /**
     * 向MyArraylist中添加数据
     *
     * @param e
     */
    public void add(E e) {
        this.grow();
        elementData[size++] = e;
    }

    /**
     * 下标是否越界检查
     *
     * @param index
     * @throws Exception
     */
    private void rangeCheck(int index) throws Exception {
        if (index < 0 || index >= size) {
            throw new Exception("数组越界！");
        }
    }

    /**
     * 获取元素
     *
     * @param index
     * @return
     */
    private E elementData(int index) {
        return (E) elementData[index];
    }

    /**
     * 根据下标获取元素
     *
     * @param index
     * @return
     * @throws Exception
     */
    public E get(int index) throws Exception {
        rangeCheck(index);
        return (E) elementData[index];
    }


    /**
     * 根据下标移除
     *
     * @param index
     * @return
     * @throws Exception
     */
    public E remove(int index) throws Exception {
        rangeCheck(index);
        //声明新的数组，从移除的地方开始开始将数据拷贝，将数组改变为新数组，并将原值返回
        E oldElement = elementData(index);
        //需要拷贝的长度
        int modIndex = size - index - 1;
        if (modIndex > 0) {
            //打个比方，比如下标是5，那么从6的下标开始，将6包括6以后的数据copy到原先5的地方，拷贝modindex的长度个元素
            System.arraycopy(elementData, index + 1, elementData, index, modIndex);
        }
        //将数组最后一位赋值为null（因为从modindex开始整体向前移动一位，所以最后一位已无意义），释放资源
        elementData[size--] = null;
        return oldElement;
    }

    /**
     * 通过一个值来获取在数组内对应的下标
     *
     * @param obj
     * @return
     * @throws Exception
     */
    public int indexOf(Object obj) throws Exception {
        if (obj == null) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (obj.equals(elementData[i])) {
                    return i;
                }
            }
        }
        return -1;
    }


    /**
     * 获取元素在数组内最后出现的地方
     *
     * @param obj
     * @return
     */
    public int LastIndexOf(Object obj) {
        if (obj == null) {
            for (int i = size - 1; i >= 0; i--) {
                if (elementData[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = size - 1; i >= 0; i--) {
                if (obj.equals(elementData[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * 根据下标快速移除,在根据对象移除的方法中来套用
     *
     * @param index
     */
    private void fastRemove(int index) {
        int modIndex = size - index - 1;
        System.arraycopy(elementData, index + 1, elementData, index, modIndex);
        elementData[--size] = null;
    }

    /**
     * 根据对象移除
     *
     * @param u2
     * @return
     * @throws Exception
     */
    public boolean remove(E u2) throws Exception {
        if (u2 == null) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) {
                    fastRemove(i);
                    return true;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (elementData[i].equals(u2)) {
                    fastRemove(i);
                    return true;
                }
            }
            return true;
        }
        return false;
    }
}