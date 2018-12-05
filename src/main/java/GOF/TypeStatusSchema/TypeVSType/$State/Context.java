package GOF.TypeStatusSchema.TypeVSType.$State;

import lombok.Data;

/**
 * @Auther: KAM1996
 * @Date: 13:48 2018-11-08
 * @Description: 状态模式的切换类
 * @Version: 1.0
 */

@Data
public class Context {

    private State state;

    public Context(State state) {
        this.state = state;
    }
    public void method(){
        if(state.getValue().equals("state1")){
            state.method1();
        } else if(state.getValue().equals("state2")){
            state.method2();
        }
    }
}
