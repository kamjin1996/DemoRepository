package GOF.TypeStatusSchema.InterpreterTest;

/**
 * @Auther: KAM1996
 * @Date: 14:49 2018-11-08
 * @Description: 加，表达实现
 * @Version: 1.0
 */
public class Plus implements Expression {

    @Override
    public int interpret(Context context) {
        return context.getNum1()+context.getNum2();
    }
}
