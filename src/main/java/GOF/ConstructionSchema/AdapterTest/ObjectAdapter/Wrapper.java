package GOF.ConstructionSchema.AdapterTest.ObjectAdapter;

import GOF.ConstructionSchema.AdapterTest.common.Source;
import GOF.ConstructionSchema.AdapterTest.common.Targetable;

/**
 * @Auther: KAM1996
 * @Date: 14:54 2018-11-07
 * @Description:
 * @Version: 1.0
 */
//基本思路和类的适配器模式相同，只是将Adapter类作修改，
// 这次不继承Source类，而是持有Source类的实例，以达到解决兼容性的问题。
public class Wrapper implements Targetable {

    private Source source;

    public Wrapper(Source source) {
        //super();
        this.source = source;
    }

    @Override
    public void method1() {
        source.method1();
    }

    @Override
    public void method2() {
        System.out.println("this is targetable method!");
    }
}
