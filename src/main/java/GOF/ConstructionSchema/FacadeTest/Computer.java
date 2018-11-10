package GOF.ConstructionSchema.FacadeTest;

/**
 * @Auther: KAM1996
 * @Date: 16:16 2018-11-07
 * @Description: 电脑
 * @Version: 1.0
 */
public class Computer {
    private CPU cpu;
    private Disk disk;
    private Memory memory;

    public Computer(){
        cpu = new CPU();
        disk = new Disk();
        memory = new Memory();
    }
    //电脑启动
    public void startup(){
        System.out.println("start the computer!");
        cpu.startup();
        memory.startup();
        disk.startup();
        System.out.println("start computer finish!");
    }
    //电脑关闭
    public void shutdown(){
        System.out.println("shutdown the computer!");
        cpu.shutdown();
        memory.shutdown();
        disk.shutdown();
        System.out.println("shutdown computer finish!");
    }
}
