package GOF.TypeStatusSchema.MiddleType.MediatorTest;

/**
 * @Auther: KAM1996
 * @Date: 14:33 2018-11-08
 * @Description: 具体User1
 * @Version: 1.0
 */
public class User1 extends User {

    public User1(Mediator mediator) {
        super(mediator);
    }

    @Override
    public void work() {
        System.out.println("user1 exe!");
    }
}
