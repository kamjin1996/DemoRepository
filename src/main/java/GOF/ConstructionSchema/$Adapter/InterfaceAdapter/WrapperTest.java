package GOF.ConstructionSchema.$Adapter.InterfaceAdapter;

/**
 * @Auther: KAM1996
 * @Date: 15:14 2018-11-07
 * @Description: 接口适配测试
 * @Version: 1.0
 */
public class WrapperTest {

    public static void main(String[] args) {
        Sourceable sub1 = new SourceSub1();
        Sourceable sub2 = new SourceSub2();
        ////第一个实现的对象
        sub1.method1();
        //sub1.method2();
        //第二个实现类的对象
       // sub2.method1();
        sub2.method2();
    }
}
