package GOF.ConstructionSchema.$Bridge;

/**
 * @Auther: KAM1996
 * @Date: 16:29 2018-11-07
 * @Description: 源接口实现类1
 * @Version: 1.0
 */
public class SourceSub2 implements Sourceable {

    @Override
    public void method() {
        System.out.println("this is the second sub2!");
    }
}
