package GOF.ConstructionSchema.$Bridge.aggregation;

/**
 * @author kam
 * @since 2021/1/12 0012
 *
 * <p>
 * strom聚合服务
 * </p>
 */
public class StormAggregationService extends BaseAggregationService {

    @Override
    protected void execImpl() {
        System.out.println("storm收集");
    }
}
