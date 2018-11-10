package GOF.ConstructionSchema.FacadeTest;

/**
 * @Auther: KAM1996
 * @Date: 16:12 2018-11-07
 * @Description: 电脑内存
 * @Version: 1.0
 */
public class Memory {

    //内存启动
    public void startup(){
        System.out.println("Memory startup!");
    }
    //内存关闭
    public void shutdown(){
        System.out.println("Memory shutdown!");
    }
}
