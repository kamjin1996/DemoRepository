package GOF.ConstructionSchema.Decorator;

/** @Auther: KAM1996 @Date: 15:42 2018-11-07 @Description: 测试装饰者模式 @Version: 1.0 */
public class DecoratorTest {

  public static void main(String[] args) {
    //// 被装饰者对象
    // Sourceable source = new Source();
    //// 传入装饰器中
    // Sourceable decorator = new Decorator(source);
    // decorator.method();

    // new Decorator(new Source()).method();

    // 匿名内部类调用自身写法
    new Decorator(
            new Sourceable() {
              @Override
              public void method() {
                System.out.println("me");
              }
            })
        .method();

    // lambda
    new Decorator(() -> System.out.println("lambda")).method();
  }
}
