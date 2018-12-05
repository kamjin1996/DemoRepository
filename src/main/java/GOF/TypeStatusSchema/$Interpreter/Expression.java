package GOF.TypeStatusSchema.$Interpreter;

/**
 * @Auther: KAM1996
 * @Date: 14:48 2018-11-08
 * @Description: 表达式接口
 * @Version: 1.0
 */
public interface Expression {
    int interpret(Context context);
}
