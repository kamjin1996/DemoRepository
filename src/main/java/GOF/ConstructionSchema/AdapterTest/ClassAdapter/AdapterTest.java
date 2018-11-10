package GOF.ConstructionSchema.AdapterTest.ClassAdapter;

import GOF.ConstructionSchema.AdapterTest.common.Targetable;

/**
 * @Auther: KAM1996
 * @Date: 14:48 2018-11-07
 * @Description: 测试适配器
 * @Version: 1.0
 */
public class AdapterTest {
    public static void main(String[] args) {
        Targetable targetable = new Adapter();
        targetable.method1();
        targetable.method2();
    }
}
