package GOF.RelationSchema.NoExtends.ObserverTest.observer;

/**
 * @Auther: KAM1996
 * @Date: 10:21 2018-11-08
 * @Description: 观察者实现类 观察者2
 * @Version: 1.0
 */
public class Observer2Impl implements Observer {
    @Override
    public void update() {
        System.out.println("observer2 has received!");
    }
}
