package GOF.ConstructionSchema.$Bridge.aggregation;

import GOF.ConstructionSchema.$Bridge.collect.BaseCollectService;

import java.util.Objects;

/**
 * @author kam
 * @since 2021/1/12 0012
 *
 * <p>
 * 基础汇聚服务
 * </p>
 */
public abstract class BaseAggregationService {

    private BaseCollectService collectService;

    public BaseAggregationService() {
    }

    public BaseAggregationService(BaseCollectService collectService) {
        this.collectService = collectService;
    }

    public void run() {
        if (Objects.nonNull(collectService)) {
            collectService.run();
        }
        execImpl();
    }

    /**
     * 执行收集实现
     */
    protected abstract void execImpl();

    ;
}
