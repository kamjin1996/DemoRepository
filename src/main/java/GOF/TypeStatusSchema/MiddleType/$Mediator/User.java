package GOF.TypeStatusSchema.MiddleType.$Mediator;

/**
 * @Auther: KAM1996
 * @Date: 14:31 2018-11-08
 * @Description:
 * @Version: 1.0
 */
//辅助类，使User们的父类兼容中介者
public abstract class User {

    private Mediator mediator;

    public Mediator getMediator() {
        return mediator;
    }

    public User(Mediator mediator) {
        this.mediator = mediator;
    }

    //抽象方法：工作
    public abstract void work();
}
