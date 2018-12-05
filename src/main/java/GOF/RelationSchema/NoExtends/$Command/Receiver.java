package GOF.RelationSchema.NoExtends.$Command;

/**
 * @Auther: KAM1996
 * @Date: 11:47 2018-11-08
 * @Description: 接收者
 * @Version: 1.0
 */
public class Receiver {

    //收到了消息
    public void action(){
        System.out.println("command received!");
    }
}
