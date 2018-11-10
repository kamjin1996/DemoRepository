package GOF.TypeStatusSchema.MiddleType.VisitorTest.subject;

import GOF.TypeStatusSchema.MiddleType.VisitorTest.visitor.Visitor;

/**
 * @Auther: KAM1996
 * @Date: 14:07 2018-11-08
 * @Description: Subject接口，accept方法，接受将要访问它的对象，getSubject获取将要被访问的属性
 * @Version: 1.0
 */
public interface Subject {
    //接受访问者，访问subject
    void accept(Visitor visitor);
    String getSubject();
}
