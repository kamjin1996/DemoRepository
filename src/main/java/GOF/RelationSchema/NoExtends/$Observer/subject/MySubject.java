package GOF.RelationSchema.NoExtends.$Observer.subject;

/**
 * @Auther: KAM1996
 * @Date: 10:29 2018-11-08
 * @Description: 我的Subject实现,主对象
 * @Version: 1.0
 */
public class MySubject extends AbstractSubject {

    @Override
    public void operation() {
        System.out.println("update self!");
        notifyObservers();
    }
}
