package GOF.ConstructionSchema.$Facade;

/**
 * @Auther: KAM1996
 * @Date: 16:22 2018-11-07
 * @Description: 持有并使用电脑的人
 * @Version: 1.0
 */
/*
如果我们没有Computer类，那么，CPU、Memory、Disk他们之间将会相互持有实例，产生关系，这样会造成严重的依赖，
修改一个类，可能会带来其他类的修改，这不是我们想要看到的，
有了Computer类，他们之间的关系被放在了Computer类里，这样就起到了解耦的作用，外观模式就是如此
*/
public class User {
    public static void main(String[] args) {
        Computer computer = new Computer();
        computer.startup();
        computer.shutdown();
    }
}
