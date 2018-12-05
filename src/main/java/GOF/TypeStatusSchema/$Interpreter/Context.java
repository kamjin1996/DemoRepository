package GOF.TypeStatusSchema.$Interpreter;

import lombok.Data;

/**
 * @Auther: KAM1996
 * @Date: 14:51 2018-11-08
 * @Description: 上下文环境类
 * @Version: 1.0
 */
@Data
public class Context {

    private int num1;
    private int num2;

    public Context(int num1, int num2) {
        this.num1 = num1;
        this.num2 = num2;
    }

}
