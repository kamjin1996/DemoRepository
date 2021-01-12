package GOF.ConstructionSchema.$Bridge;

import GOF.ConstructionSchema.$Bridge.aggregation.BaseAggregationService;
import GOF.ConstructionSchema.$Bridge.aggregation.SparkAggregationService;
import GOF.ConstructionSchema.$Bridge.collect.BaseCollectService;
import GOF.ConstructionSchema.$Bridge.collect.EmsCollectService;
import GOF.ConstructionSchema.$Bridge.store.BaseStoreService;
import GOF.ConstructionSchema.$Bridge.store.HdfsStoreService;

/**
 * @author kam
 * @since 2021/1/12 0012
 *
 * <p>
 * 桥接模式就是把事物和其具体实现分开，使他们可以各自独立的变化。
 * 桥接的用意是：     将抽象化与实现化解耦，使得二者可以独立变化， 像我们常用的JDBC桥DriverManager一样，
 * JDBC进行连接数据库的时候，在各个数据库之间进行切换，基本不需要动太多的代码， 甚至丝毫不用动，
 * 原因就是JDBC提供统一接口，每个数据库提供各自的实现，用一个叫做数据库驱动的程序来桥接就行了。
 * <p>
 * 以下例子中，构造器传参即==>搭桥行为
 * run方法是末尾的桥开始执行入口，
 * execImpl抽象方法，是每个桥的抽象实现 由子类服务来实现
 * </p>
 * 桥接模式中每个桥又可以独立运行，也可以通过前置步骤桥传入作为铺垫执行
 * <p>
 * 下面例子中，每个服务可以看成是桥，即： 收集桥==>聚合桥==>入库桥  每个服务都有不同的实现，但这些实现不必知道各自具体的存在
 * 父类实现了抽象行为，子类提供具体实现即可，在使用时通过构造传入进行”搭桥“
 */
public class BridgeTest {

    public static void main(String[] args) {

        //收集
        BaseCollectService collectService = new EmsCollectService();

        //采集搭桥
        BaseAggregationService aggregationService = new SparkAggregationService(collectService);

        //入库桥
        BaseStoreService storeService = new HdfsStoreService(aggregationService);

        //启动桥
        storeService.run();
    }
}
