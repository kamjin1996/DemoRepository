package GOF.TypeStatusSchema.MiddleType.MediatorTest;

/**
 * @Auther: KAM1996
 * @Date: 14:35 2018-11-08
 * @Description: 具体User2
 * @Version: 1.0
 */
public class User2 extends User {

    public User2(Mediator mediator) {
        super(mediator);
    }

    @Override
    public void work() {
        System.out.println("user2 exe!");
    }
}
