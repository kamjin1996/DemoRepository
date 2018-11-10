package GOF.ConstructionSchema.AdapterTest.InterfaceAdapter;

/**
 * @Auther: KAM1996
 * @Date: 15:12 2018-11-07
 * @Description: 源接口实现类2
 * @Version: 1.0
 */
public class SourceSub2 extends Wrapper2 {

    @Override
    public void method2() {
        //super.method2();
        System.out.println("this is interface Sourceable's second sub2");
    }
}
