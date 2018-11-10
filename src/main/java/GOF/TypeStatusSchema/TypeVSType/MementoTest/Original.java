package GOF.TypeStatusSchema.TypeVSType.MementoTest;

import lombok.Data;

/**
 * @Auther: KAM1996
 * @Date: 12:00 2018-11-08
 * @Description: 原始类，里面有需要保存的属性value及创建一个备忘录类，用来保存value值
 * @Version: 1.0
 */
@Data
public class Original {

    private String value;

    public Original(String value) {
        this.value = value;
    }

    //创建备份
    public Memento createMemento(){
        return new Memento(value);
    }
    //恢复备份(核心方法)
    public void restoreMemento(Memento memento){
        this.value = memento.getValue();
    }
}
