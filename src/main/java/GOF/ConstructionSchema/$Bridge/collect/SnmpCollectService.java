package GOF.ConstructionSchema.$Bridge.collect;

/**
 * @author kam
 * @since 2021/1/12 0012
 *
 * <p>
 * Snmp收集服务
 * </p>
 */
public class SnmpCollectService extends BaseCollectService {

    @Override
    public void execImpl() {
        System.out.println("snmp收集");
    }
}
