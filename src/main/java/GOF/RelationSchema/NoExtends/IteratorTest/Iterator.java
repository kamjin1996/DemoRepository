package GOF.RelationSchema.NoExtends.IteratorTest;

/**
 * @Auther: KAM1996
 * @Date: 10:54 2018-11-08
 * @Description: 迭代器接口
 * @Version: 1.0
 */
public interface Iterator {

    //前移
    Object previous();

    //后移
    Object next();

    //是否有下一个
    boolean hastNext();

    //取得第一个元素
    Object first();
}
