package GOF.ConstructionSchema.DecoratorTest;

/**
 * @Auther: KAM1996
 * @Date: 15:33 2018-11-07
 * @Description: 源接口，被装饰者和装饰者共同实现
 * @Version: 1.0
 */
/*
装饰模式就是给一个对象增加一些新的功能，而且是动态的，
要求装饰对象和被装饰对象实现同一个接口，装饰对象持有被装饰对象的实例
Source类是被装饰类，Decorator类是一个装饰类，可以为Source类动态的添加一些功能
*/
public interface Sourceable {
    void method();
}
