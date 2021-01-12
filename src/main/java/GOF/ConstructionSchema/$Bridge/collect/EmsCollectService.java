package GOF.ConstructionSchema.$Bridge.collect;

/**
 * @author kam
 * @since 2021/1/12 0012
 *
 * <p>
 * ems收集服务
 * </p>
 */
public class EmsCollectService extends BaseCollectService {

    @Override
    public void execImpl() {
        System.out.println("ems收集");
    }
}
