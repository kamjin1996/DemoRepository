package GOF.RelationSchema.NoExtends.$Command;

/**
 * @Auther: KAM1996
 * @Date: 11:47 2018-11-08
 * @Description: 命令实现类
 * @Version: 1.0
 */
public class MyCommand implements Command {

    private Receiver receiver;

    public MyCommand(Receiver receiver){
        this.receiver = receiver;
    }

    @Override
    public void exe() {
        receiver.action();
    }
}
