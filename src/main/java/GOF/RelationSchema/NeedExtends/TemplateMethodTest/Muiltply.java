package GOF.RelationSchema.NeedExtends.TemplateMethodTest;

/**
 * @Auther: KAM1996
 * @Date: 9:50 2018-11-08
 * @Description: 继承于模板，乘
 * @Version: 1.0
 */
public class Muiltply extends AbstractCalculator {

    @Override
    public int calculate(int num1, int num2) {
        return num1 * num2;
    }
}
