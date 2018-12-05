package GOF.TypeStatusSchema.MiddleType.$Visitor.subject;

import GOF.TypeStatusSchema.MiddleType.$Visitor.visitor.Visitor;

/**
 * @Auther: KAM1996
 * @Date: 14:09 2018-11-08
 * @Description: 我的Subject实现类
 * @Version: 1.0
 */
public class MySubject implements Subject {


    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String getSubject() {
        return "MySub to doing...";
    }
}
