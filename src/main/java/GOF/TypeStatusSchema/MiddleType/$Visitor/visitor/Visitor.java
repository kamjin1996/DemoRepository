package GOF.TypeStatusSchema.MiddleType.$Visitor.visitor;

import GOF.TypeStatusSchema.MiddleType.$Visitor.subject.Subject;


/**
 * @Auther: KAM1996
 * @Date: 14:05 2018-11-08
 * @Description: 访问者接口
 * @Version: 1.0
 */
public interface Visitor {
    void visit(Subject sub);
}
