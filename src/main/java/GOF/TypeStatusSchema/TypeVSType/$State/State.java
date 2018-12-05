package GOF.TypeStatusSchema.TypeVSType.$State;

import lombok.Data;

/**
 * @Auther: KAM1996
 * @Date: 13:46 2018-11-08
 * @Description: 状态类的核心类
 * @Version: 1.0
 */
@Data
public class State {

    private String value;

    public void method1(){
        System.out.println("execute the first opt!");
    }
    public void method2(){
        System.out.println("execute the second opt!");
    }
}
