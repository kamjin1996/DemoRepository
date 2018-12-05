package GOF.TypeStatusSchema.MiddleType.$Visitor.visitor;

import GOF.TypeStatusSchema.MiddleType.$Visitor.subject.Subject;

/**
 * @Auther: KAM1996
 * @Date: 14:06 2018-11-08
 * @Description: 我的访问者实现类
 * @Version: 1.0
 */
public class MyVisitor1 implements Visitor {

    @Override
    public void visit(Subject sub) {
        System.out.println("MyVisitor1 visit the subject: "+sub.getSubject());
    }
}
