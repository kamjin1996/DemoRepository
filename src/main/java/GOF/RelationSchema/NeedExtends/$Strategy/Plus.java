package GOF.RelationSchema.NeedExtends.$Strategy;

/**
 * @Auther: KAM1996
 * @Date: 18:27 2018-11-07
 * @Description: \\+
 * @Version: 1.0
 */
public class Plus extends AbstractCalculator implements ICalculator {

    //第一种实现+
    @Override
    public int calculate(String exp) {
        int[] arrayInt = split(exp, "\\+");
        return arrayInt[0] + arrayInt[1];
    }
}
