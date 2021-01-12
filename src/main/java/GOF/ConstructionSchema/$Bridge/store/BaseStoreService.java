package GOF.ConstructionSchema.$Bridge.store;

import GOF.ConstructionSchema.$Bridge.aggregation.BaseAggregationService;

import java.util.Objects;

/**
 * @author kam
 * @since 2021/1/12 0012
 *
 * <p>
 * 基础存储服务
 * </p>
 */
public abstract class BaseStoreService {

    private BaseAggregationService aggregationService;

    public BaseStoreService() {
    }

    public BaseStoreService(BaseAggregationService aggregationService) {
        this.aggregationService = aggregationService;
    }

    /**
     *
     */
    public void run() {
        if (Objects.nonNull(aggregationService)) {
            this.aggregationService.run();
        }
        this.execImpl();
    }

    /**
     * 执行实现
     */
    protected abstract void execImpl();
}
