package GOF.ConstructionSchema.ProxyTest;

/**
 * @Auther: KAM1996
 * @Date: 16:04 2018-11-07
 * @Description: 代理测试
 * @Version: 1.0
 */
public class ProxyTest {

    public static void main(String[] args) {
        Sourceable source = new Proxy();
        source.method();
    }
}
