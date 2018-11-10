package GOF.RelationSchema.NeedExtends.StrategyTest;

/**
 * @Auther: KAM1996
 * @Date: 18:29 2018-11-07
 * @Description: -
 * @Version: 1.0
 */
public class Minus extends AbstractCalculator implements ICalculator {

    //第二种实现-
    @Override
    public int calculate(String exp) {
        int[] arrayInt = split(exp, "-");
        return arrayInt[0] - arrayInt[1];
    }
}
