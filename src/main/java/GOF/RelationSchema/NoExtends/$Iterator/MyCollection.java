package GOF.RelationSchema.NoExtends.$Iterator;

/**
 * @Auther: KAM1996
 * @Date: 10:58 2018-11-08
 * @Description: 集合的实现
 * @Version: 1.0
 */
public class MyCollection implements Collection {

    public String[] strArr = {"A", "B", "C", "D", "E"};

    @Override
    public Iterator iterator() {
        return new MyIterator(this);
    }

    @Override
    public Object get(int i) {
        return strArr[i];
    }

    @Override
    public int size() {
        return strArr.length;
    }
}
