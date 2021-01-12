package GOF.ConstructionSchema.$Bridge.collect;

/**
 * @author kam
 * @since 2021/1/12 0012
 *
 * <p>
 * 基础收集服务
 * </p>
 */
public abstract class BaseCollectService {

    public BaseCollectService() {
    }

    public void run() {
        execImpl();
    }

    public abstract void execImpl();

}
