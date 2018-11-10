package GOF.RelationSchema.NoExtends.ObserverTest.subject;

import GOF.RelationSchema.NoExtends.ObserverTest.observer.Observer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Auther: KAM1996
 * @Date: 10:25 2018-11-08
 * @Description: 抽象Subject接口实现类
 * @Version: 1.0
 */
public abstract class AbstractSubject implements Subject {

    //观察者集合
   private List<Observer> list = new ArrayList<>();

    @Override
    public void add(Observer observer) {
        list.add(observer);
    }

    @Override
    public void del(Observer observer) {
        list.remove(observer);
    }

    /**
     * 通知观察者实现类
     */
    @Override
    public void notifyObservers() {
        Iterator<Observer> oIterator = list.iterator();
        while (oIterator.hasNext()){
            oIterator.next().update();
        }

    }
}
