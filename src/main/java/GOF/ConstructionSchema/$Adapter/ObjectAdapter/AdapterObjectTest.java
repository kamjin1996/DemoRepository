package GOF.ConstructionSchema.$Adapter.ObjectAdapter;

import GOF.ConstructionSchema.$Adapter.common.Source;
import GOF.ConstructionSchema.$Adapter.common.Targetable;

/**
 * @Auther: KAM1996
 * @Date: 14:58 2018-11-07
 * @Description: 适配对象测试
 * @Version: 1.0
 */
public class AdapterObjectTest {
    public static void main(String[] args) {
        Source source = new Source();
        Targetable targetable = new Wrapper(source);
        targetable.method1();
        targetable.method2();
    }
}
