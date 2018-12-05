package GOF.TypeStatusSchema.TypeVSType.$Memento;

import lombok.Data;

/**
 * @Auther: KAM1996
 * @Date: 12:02 2018-11-08
 * @Description: 备忘录类
 * @Version: 1.0
 */
@Data
public class Memento {

     private String value;

    public Memento(String value) {
        this.value = value;
    }
}
