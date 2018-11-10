package GOF.ConstructionSchema.FacadeTest;

/**
 * @Auther: KAM1996
 * @Date: 16:14 2018-11-07
 * @Description: 电脑硬盘
 * @Version: 1.0
 */
public class Disk {

    //硬盘启动
    public void startup(){
        System.out.println("Disk startup!");
    }
    //硬盘关闭
    public void shutdown(){
        System.out.println("Disk shutdown!");
    }
}
