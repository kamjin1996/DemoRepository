package GOF.RelationSchema.NoExtends.$Observer.observer;

/**
 * @Auther: KAM1996
 * @Date: 10:48 2018-11-08
 * @Description: 观察者实现类 观察者3
 * @Version: 1.0
 */
public class Observer3Impl implements Observer {

    @Override
    public void update() {
        System.out.println("observer3 has received!");
    }
}
