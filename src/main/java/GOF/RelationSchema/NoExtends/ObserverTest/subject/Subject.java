package GOF.RelationSchema.NoExtends.ObserverTest.subject;

import GOF.RelationSchema.NoExtends.ObserverTest.observer.Observer;

/**
 * @Auther: KAM1996
 * @Date: 10:23 2018-11-08
 * @Description: Subject接口
 * @Version: 1.0
 */
public interface Subject {
    //增加观察者
    void add(Observer observer);

    //删除观察者
    void del(Observer observer);

    //通知所有的观察者
    void notifyObservers();

    //自身的操作
    void operation();
}
