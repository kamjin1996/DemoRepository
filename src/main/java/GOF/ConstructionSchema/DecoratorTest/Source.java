package GOF.ConstructionSchema.DecoratorTest;

/**
 * @Auther: KAM1996
 * @Date: 15:37 2018-11-07
 * @Description: 被装饰类
 * @Version: 1.0
 */
public class Source implements Sourceable {

    @Override
    public void method() {
        System.out.println("this is source method!");
    }
}
