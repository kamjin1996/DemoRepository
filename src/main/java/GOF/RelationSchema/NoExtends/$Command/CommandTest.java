package GOF.RelationSchema.NoExtends.$Command;

/**
 * @Auther: KAM1996
 * @Date: 11:52 2018-11-08
 * @Description: 命令模式测试类
 * @Version: 1.0
 */

/**
 * 命令模式很好理解，举个例子，司令员下令让士兵去干件事情，从整个事情的角度来考虑，司令员的作用是，发出口令，
 * 口令经过传递，传到了士兵耳朵里，士兵去执行。
 * 这个过程好在，三者相互解耦，任何一方都不用去依赖其他人，只需要做好自己的事儿就行，
 * 司令员要的是结果，不会去关注到底士兵是怎么实现的。
 *
 * 这个很好理解，命令模式的目的：
 *      就是达到命令的发出者和执行者之间解耦，实现请求和执行分开，
 * Struts其实就是一种将请求和呈现分离的技术，其中必然涉及命令模式的思想！
 */
public class CommandTest {

    public static void main(String[] args) {
        //创建接收者
        Receiver receiver = new Receiver();
        //创建命令，并传入需接收命令接收者
        Command cmd = new MyCommand(receiver);
        //创建执行者，传入需执行命令
        Invoker invoker = new Invoker(cmd);
        //执行命令
        invoker.action();
    }
}
