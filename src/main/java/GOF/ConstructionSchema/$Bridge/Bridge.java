package GOF.ConstructionSchema.$Bridge;

/**
 * @Auther: KAM1996
 * @Date: 16:30 2018-11-07
 * @Description: 桥，持有一个Sourceable实例
 * @Version: 1.0
 */
public abstract class Bridge{

    private Sourceable source;

    //桥的method实现，实际是Sourceable的method的实现
    public void method(){
        //System.out.println("bridge self method!");
        source.method();
    }

    public Sourceable getSource() {
        return source;
    }

    public void setSource(Sourceable source) {
        this.source = source;
    }
}
