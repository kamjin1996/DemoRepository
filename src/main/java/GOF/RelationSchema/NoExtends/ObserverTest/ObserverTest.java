package GOF.RelationSchema.NoExtends.ObserverTest;

/**
 * @Auther: KAM1996
 * @Date: 10:31 2018-11-08
 * @Description: 观察者测试类
 * @Version: 1.0
 */

import GOF.RelationSchema.NoExtends.ObserverTest.observer.Observer1Impl;
import GOF.RelationSchema.NoExtends.ObserverTest.observer.Observer2Impl;
import GOF.RelationSchema.NoExtends.ObserverTest.subject.MySubject;
import GOF.RelationSchema.NoExtends.ObserverTest.subject.Subject;

/**
 * MySubject类就是我们的主对象，Observer1和Observer2是依赖于MySubject的对象，
 * 当MySubject变化时，Observer1和Observer2必然变化。
 * AbstractSubject类中定义着需要监控的对象列表，可以对其进行修改：增加或删除被监控对象，
 * 且当MySubject变化时，负责通知在列表内存在的对象。
 */
public class ObserverTest {

    public static void main(String[] args) {
        Subject sub = new MySubject();
        //添加一些观察者
        sub.add(new Observer1Impl());
        sub.add(new Observer2Impl());
        //sub.add(new Observer3Impl());
        //做自己的事情
        sub.operation();
    }
}
