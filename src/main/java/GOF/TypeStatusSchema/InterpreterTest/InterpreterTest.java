package GOF.TypeStatusSchema.InterpreterTest;


/**
 * @Auther: KAM1996
 * @Date: 14:53 2018-11-08
 * @Description: 解释器模式测试类
 * @Version: 1.0
 */

/**
 * 一般主要应用在OOP开发中的编译器的开发中，所以适用面比较窄。
 * 基本就这样，解释器模式用来做各种各样的解释器，如正则表达式等的解释器等等
 *
 * Context类是一个上下文环境类，Plus和Minus分别是用来计算的实现
 */

public class InterpreterTest {

    public static void main(String[] args) {
        //计算9+2-8的值
        int result = new Minus().interpret(new Context(new Plus().interpret(new Context(9,2)),8));
        System.out.println(result);//3
    }
}
