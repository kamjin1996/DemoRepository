package GOF.RelationSchema.NoExtends.$ChainOfResponsibility;

import lombok.Data;

/**
 * @Auther: KAM1996
 * @Date: 11:24 2018-11-08
 * @Description: 抽象操作者
 * @Version: 1.0
 */
@Data
public abstract class AbstractHandler {

    //持有操作者对象
    private Handler handler;

}
