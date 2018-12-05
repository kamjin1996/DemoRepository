package GOF.TypeStatusSchema.$Interpreter;

/**
 * @Auther: KAM1996
 * @Date: 14:52 2018-11-08
 * @Description: 减 表达式实现
 * @Version: 1.0
 */
public class Minus implements Expression {

    @Override
    public int interpret(Context context) {
        return context.getNum1() - context.getNum2();
    }
}
