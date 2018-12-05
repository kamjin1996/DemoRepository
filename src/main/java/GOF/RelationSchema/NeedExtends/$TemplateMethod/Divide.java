package GOF.RelationSchema.NeedExtends.$TemplateMethod;

/**
 * @Auther: KAM1996
 * @Date: 9:51 2018-11-08
 * @Description: 继承于模板 除
 * @Version: 1.0
 */
public class Divide extends AbstractCalculator {

    @Override
    public int calculate(int num1, int num2) {
        return num1 / num2;
    }
}
