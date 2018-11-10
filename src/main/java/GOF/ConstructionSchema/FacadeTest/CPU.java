package GOF.ConstructionSchema.FacadeTest;

/**
 * @Auther: KAM1996
 * @Date: 16:09 2018-11-07
 * @Description: 电脑CPU
 * @Version: 1.0
 */
public class CPU {

    //CPU启动
    public void startup(){
        System.out.println("CPU startup!");
    }
    //CPU关闭
    public void shutdown(){
        System.out.println("CPU shutdown!");
    }
}
