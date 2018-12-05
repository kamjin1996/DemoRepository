package GOF.RelationSchema.NeedExtends.$TemplateMethod;

/**
 * @Auther: KAM1996
 * @Date: 9:39 2018-11-08
 * @Description: 第一个继承类
 * @Version: 1.0
 */
public class Plus extends AbstractCalculator {

    @Override
    public int calculate(int num1, int num2) {
        return num1 + num2;
    }
}
