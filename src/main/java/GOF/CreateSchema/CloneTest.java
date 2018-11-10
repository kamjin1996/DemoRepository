package GOF.CreateSchema;

import java.io.*;

/**
 * @Auther: KAM1996
 * @Date: 13:38 2018-11-07
 * @Description: 深浅拷贝测试
 * @Version: 1.0
 */
//原型模式
/*
原型模式虽然是创建型的模式，但是与工程模式没有关系，
从名字即可看出，该模式的思想就是将一个对象作为原型，对其进行复制、克隆，产生一个和原对象类似的新对象。
实现方式即：浅拷贝或深拷贝
对象深、浅复制的概念：

浅复制：
    将一个对象复制后，基本数据类型的变量都会重新创建，而引用类型，指向的还是原对象所指向的。

深复制：
    将一个对象复制后，不论是基本数据类型还有引用类型，都是重新创建的。
    简单来说，就是深复制进行了完全彻底的复制，而浅复制不彻底。
*/
public class CloneTest implements Cloneable,Serializable {

    private String name;

    private SerializableObject obj;

    //浅拷贝
    public Object getClone() throws CloneNotSupportedException {
        CloneTest cloneTest  = (CloneTest) super.clone();
        return cloneTest;
    }

    //深拷贝
    public Object getCloneDeep() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        //写入当前对象的二进制流
        oos.writeObject(this);

        //读出二进制产生的新对象
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        return ois.readObject();
    }

    public void setObj(SerializableObject obj){
        this.obj = obj;
    }
    public SerializableObject getObj(){
        return obj;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}

class SerializableObject implements Serializable{
    private static final Long serializableVersionUID = 1L;
}