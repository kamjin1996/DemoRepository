package GOF.ConstructionSchema.$Bridge.store;

/**
 * @author kam
 * @since 2021/1/12 0012
 *
 * <p>
 * Mppdb入库服务
 * </p>
 */
public class MppdbStoreService extends BaseStoreService {

    @Override
    protected void execImpl() {
        System.out.println("Mppdb入库");
    }
}
