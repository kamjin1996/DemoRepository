package GOF.ConstructionSchema.$Bridge;

/**
 * @Auther: KAM1996
 * @Date: 16:34 2018-11-07
 * @Description: 桥的实现
 * @Version: 1.0
 */
public class MyBridge extends Bridge {

    @Override
    public void method() {
        //注意这里,不使用super.method();此方法只会调用被桥的类的method
        getSource().method();
    }
}
