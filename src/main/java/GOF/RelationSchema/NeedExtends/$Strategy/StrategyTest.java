package GOF.RelationSchema.NeedExtends.$Strategy;

/**
 * @Auther: KAM1996
 * @Date: 18:33 2018-11-07
 * @Description: 策略模式测试类
 * @Version: 1.0
 */
public class StrategyTest {

    public static void main(String[] args) {
        //Plus
        String exp = "6+8";
        System.out.println(new Plus().calculate(exp));
        //Minus
        String exp2 = "6-9";
        System.out.println(new Minus().calculate(exp2));
        //Muiltply
        String exp3 = "4*5";
        System.out.println(new Muiltply().calculate(exp3));
    }
}

