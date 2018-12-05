package GOF.ConstructionSchema.$Adapter.InterfaceAdapter;

/**
 * @Auther: KAM1996
 * @Date: 15:09 2018-11-07
 * @Description: 源接口实现类1
 * @Version: 1.0
 */
public class SourceSub1 extends Wrapper2 {
    @Override
    public void method1() {
       // super.method1();
        System.out.println("this is interface Sourceable's first sub1");
    }
}
