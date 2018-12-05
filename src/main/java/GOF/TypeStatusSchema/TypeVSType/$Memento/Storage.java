package GOF.TypeStatusSchema.TypeVSType.$Memento;

import lombok.Data;

/**
 * @Auther: KAM1996
 * @Date: 12:06 2018-11-08
 * @Description: 存储类，持有备忘录类的实例
 * @Version: 1.0
 */
@Data
public class Storage {
    private Memento memento;

    public Storage (Memento memento){
        this.memento = memento;
    }
}
