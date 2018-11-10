package GOF.CreateSchema;


import java.util.ArrayList;

/**
 * @Auther: KAM1996
 * @Date: 13:04 2018-11-07
 * @Description: 单例测试
 * @Version: 1.0
 */
//单例模式实现
/*
使用单例类的好处
1、某些类创建比较频繁，对于一些大型的对象，这是一笔很大的系统开销。
2、省去了new操作符，降低了系统内存的使用频率，减轻GC压力。
3、有些类如交易所的核心交易引擎，控制着交易流程，如果该类可以创建多个的话，系统完全乱了。
    （比如一个军队出现了多个司令员同时指挥，肯定会乱成一团），
    所以只有使用单例模式，才能保证核心交易服务器独立控制整个流程。
 */
class SingletonTest {

    private static SingletonTest singletonTest = null;

    private synchronized static void SingletonInit() {
        if (singletonTest == null) {
            singletonTest = new SingletonTest();
        }
    }

    public SingletonTest getInstance() {
        if (singletonTest == null) {
            SingletonInit();
        }
        return singletonTest;
    }
}

//较完美的单例方式，但构造时报异常则永不会被创建
class SingletonTest2 {

    //私有化保证不会被创建
    private SingletonTest2() {
    }

    //单例类来维护对象的创建
    private static class SingletonTestFactory {
        private static SingletonTest2 singletonTest2 = new SingletonTest2();


    }

    //得到实例
    public static SingletonTest2 getInstance() {
        return SingletonTestFactory.singletonTest2;
    }

    //如果该对象被用于序列化，可以保证对象在序列化前后保持一致
    public Object readResolve() {
        return getInstance();
    }
}

//补充：采用"影子实例"的办法为单例对象的属性同步更新
class SingletonTest3 {

    private static SingletonTest3 instance = null;
    private ArrayList properties = null;

    public ArrayList getProperties() {
        return properties;
    }

    private SingletonTest3() {
    }

    private static synchronized void syncInit() {
        if (instance == null) {
            instance = new SingletonTest3();
        }
    }

    public static SingletonTest3 getInstance() {
        if (instance == null) {
            syncInit();
        }
        return instance;
    }

    public void updateProperties() {
        SingletonTest3 shadow = new SingletonTest3();
        properties = shadow.getProperties();
    }
}