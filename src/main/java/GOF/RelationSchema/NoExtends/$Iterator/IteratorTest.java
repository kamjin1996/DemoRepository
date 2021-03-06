package GOF.RelationSchema.NoExtends.$Iterator;

/**
 * @Auther: KAM1996
 * @Date: 11:06 2018-11-08
 * @Description: 迭代模式测试
 * @Version: 1.0
 */

/**
 * 迭代器模式就是顺序访问聚集中的对象，一般来说，集合中非常常见，如果对集合类比较熟悉的话，理解本模式会十分轻松。
 * 这句话包含两层意思：
 *  一是需要遍历的对象，即聚集对象，二是迭代器对象，用于对聚集对象进行遍历访问。
 */
public class IteratorTest {

    public static void main(String[] args) {
        Collection collection = new MyCollection();
        Iterator it = collection.iterator();

        while (it.hastNext()) {
            System.out.println(it.next());
        }
    }
}
