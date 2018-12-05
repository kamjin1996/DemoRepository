package GOF.TypeStatusSchema.MiddleType.$Mediator;

/**
 * @Auther: KAM1996
 * @Date: 14:30 2018-11-08
 * @Description: 中介者实现, 核心类
 * @Version: 1.0
 */
public class MyMediator implements Mediator {

    private User user1;
    private User user2;

    @Override
    public void createMediator() {
        user1 = new User1(this);
        user2 = new User2(this);
    }

    @Override
    public void workAll() {
        user1.work();
        user2.work();
    }
}
