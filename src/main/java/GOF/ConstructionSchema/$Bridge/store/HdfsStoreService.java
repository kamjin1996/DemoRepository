package GOF.ConstructionSchema.$Bridge.store;

import GOF.ConstructionSchema.$Bridge.aggregation.BaseAggregationService;

/**
 * @author kam
 * @since 2021/1/12 0012
 *
 * <p>
 * Hdfs入库服务
 * </p>
 */
public class HdfsStoreService extends BaseStoreService {

    public HdfsStoreService(BaseAggregationService aggregationService) {
        super(aggregationService);
    }

    @Override
    protected void execImpl() {
        System.out.println("Hdfs入库");
    }
}
