package GOF.TypeStatusSchema.MiddleType.VisitorTest;

import GOF.TypeStatusSchema.MiddleType.VisitorTest.subject.MySubject;
import GOF.TypeStatusSchema.MiddleType.VisitorTest.subject.Subject;
import GOF.TypeStatusSchema.MiddleType.VisitorTest.visitor.MyVisitor1;
import GOF.TypeStatusSchema.MiddleType.VisitorTest.visitor.MyVisitor2;
import GOF.TypeStatusSchema.MiddleType.VisitorTest.visitor.Visitor;

/**
 * @Auther: KAM1996
 * @Date: 14:10 2018-11-08
 * @Description: 访问者模式测试类
 * @Version: 1.0
 */

/**
 * 该模式适用场景：如果我们想为一个现有的类增加新功能，不得不考虑几个事情：
 *      1、新功能会不会与现有功能出现兼容性问题？
 *      2、以后会不会再需要添加？
 *      3、如果类不允许修改代码怎么办？面对这些问题，最好的解决方法就是使用访问者模式，
 *      访问者模式适用于数据结构相对稳定的系统，把数据结构和算法解耦，
 */
public class VisitorTest {

    public static void main(String[] args) {
        Visitor visitor = new MyVisitor1();
        Visitor visitor2 = new MyVisitor2();
        Subject subject = new MySubject();
        subject.accept(visitor);
        subject.accept(visitor2);
    }
}
