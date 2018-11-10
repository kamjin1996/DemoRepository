package GOF.RelationSchema.NoExtends.ChainOfResponsibilityTest;

/**
 * @Auther: KAM1996
 * @Date: 11:26 2018-11-08
 * @Description: 我的Handler实现类
 * @Version: 1.0
 */
public class MyHandler extends AbstractHandler implements Handler {

    private String name;

    public MyHandler(String name) {
        this.name = name;
    }

    @Override
    public void operator() {
        System.out.println(name + "deal!");
        if(getHandler()!=null){
            getHandler().operator();
        }
    }
}
