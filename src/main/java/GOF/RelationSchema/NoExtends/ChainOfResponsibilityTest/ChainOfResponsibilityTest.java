package GOF.RelationSchema.NoExtends.ChainOfResponsibilityTest;

/**
 * @Auther: KAM1996
 * @Date: 11:28 2018-11-08
 * @Description: 责任链模式测试类
 * @Version: 1.0
 */

/**
 * 责任链模式，有多个对象，每个对象持有对下一个对象的引用，这样就会形成一条链，请求在这条链上传递，
 * 直到某一对象决定处理该请求。但是发出者并不清楚到底最终那个对象会处理该请求，
 * 所以，责任链模式可以实现，在隐瞒客户端的情况下，对系统进行动态的调整。
 */

/**
 * Abstracthandler类提供了get和set方法，方便MyHandle类设置和修改引用对象，MyHandle类是核心，
 * 实例化后生成一系列相互持有的对象，构成一条链。
 */

/**
 * 强调一点就是，链接上的请求可以是一条链，可以是一个树，还可以是一个环，模式本身不约束这个，
 * 需要我们自己去实现，同时，在一个时刻，命令只允许由一个对象传给另一个对象，而不允许传给多个对象。
 */
public class ChainOfResponsibilityTest {

    public static void main(String[] args) {
        MyHandler h1 = new MyHandler("h1");
        MyHandler h2 = new MyHandler("h2");
        MyHandler h3 = new MyHandler("h3");
        MyHandler h4 = new MyHandler("h4");

        h1.setHandler(h2);
        //会导致栈溢出错误
        //h2.setHandler(h1);
        h2.setHandler(h3);
        //会跳过h3，验证了同一时刻命令只允许传递给一个对象，不允许多个
       // h2.setHandler(h4);
        h3.setHandler(h4);
        //会导致栈溢出错误
        //h4.setHandler(h1);
        h1.operator();
    }
}
