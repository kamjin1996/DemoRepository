package GOF.RelationSchema.NoExtends.CommandTest;

/**
 * @Auther: KAM1996
 * @Date: 11:50 2018-11-08
 * @Description: 命令执行
 * @Version: 1.0
 */
public class Invoker {

    private Command command;

    public Invoker(Command command){
        this.command = command;
    }
    public void action(){
        command.exe();
    }
}
