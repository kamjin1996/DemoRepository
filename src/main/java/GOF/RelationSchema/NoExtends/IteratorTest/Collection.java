package GOF.RelationSchema.NoExtends.IteratorTest;

/**
 * @Auther: KAM1996
 * @Date: 10:52 2018-11-08
 * @Description: 集合接口
 * @Version: 1.0
 */
public interface Collection {

    Iterator iterator();

    //取得集合元素
    Object get(int i);

    //取得集合大小
    int size();
}
