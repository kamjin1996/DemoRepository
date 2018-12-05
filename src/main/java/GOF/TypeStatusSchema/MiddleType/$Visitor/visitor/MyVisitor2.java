package GOF.TypeStatusSchema.MiddleType.$Visitor.visitor;

import GOF.TypeStatusSchema.MiddleType.$Visitor.subject.Subject;

/**
 * @Auther: KAM1996
 * @Date: 14:16 2018-11-08
 * @Description: 我的访问者实现类2
 * @Version: 1.0
 */
public class MyVisitor2 implements Visitor {

    @Override
    public void visit(Subject sub) {
        System.out.println("MyVisitor2 visit the subject: "+sub.getSubject());
    }
}
