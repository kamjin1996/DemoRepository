package GOF.ConstructionSchema.DecoratorTest;

/**
 * @Auther: KAM1996
 * @Date: 15:37 2018-11-07
 * @Description: 装饰者
 * @Version: 1.0
 */
/*
装饰器模式的应用场景：
    1、需要扩展一个类的功能。
    2、动态的为一个对象增加功能，而且还能动态撤销。（继承不能做到这一点，继承的功能是静态的，不能动态增删。）

缺点：产生过多相似的对象，不易排错！
*/
public class Decorator implements Sourceable {

    private Sourceable source;

    //将被装饰者传入进来
    public Decorator(Sourceable source) {
        this.source = source;
    }

    @Override
    public void method() {
        System.out.println("befor decorator！");
        source.method();
        System.out.println("after decorator！");
    }
}
