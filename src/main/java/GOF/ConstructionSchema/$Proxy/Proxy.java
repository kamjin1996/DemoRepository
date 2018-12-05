package GOF.ConstructionSchema.$Proxy;

/**
 * @Auther: KAM1996
 * @Date: 16:00 2018-11-07
 * @Description: 代理类
 * @Version: 1.0
 */
public class Proxy implements Sourceable {

    private Sourceable source;

    public Proxy(){
        this.source = new SourceImpl();
    }

    @Override
    public void method() {
        befor();
        //源方法
        source.method();
        after();
    }

    private void befor(){
        System.out.println("proxy method befor！");
    }
    private void after(){
        System.out.println("proxy method after!");
    }
}
