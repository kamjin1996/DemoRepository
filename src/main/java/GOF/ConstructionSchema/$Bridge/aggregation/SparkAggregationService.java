package GOF.ConstructionSchema.$Bridge.aggregation;

import GOF.ConstructionSchema.$Bridge.collect.BaseCollectService;

/**
 * @author kam
 * @since 2021/1/12 0012
 *
 * <p>
 * spark聚合服务
 * </p>
 */
public class SparkAggregationService extends BaseAggregationService {

    public SparkAggregationService(BaseCollectService collectService) {
        super(collectService);
    }

    @Override
    protected void execImpl() {
        System.out.println("spark收集");
    }
}
