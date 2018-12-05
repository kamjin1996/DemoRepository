package GOF.RelationSchema.NoExtends.$Iterator;


/**
 * @Auther: KAM1996
 * @Date: 10:58 2018-11-08
 * @Description: 我的迭代器实现
 * @Version: 1.0
 */
public class MyIterator implements Iterator {

    private Collection collection;

    private int pos = -1;

    public MyIterator(Collection collection) {
        this.collection = collection;
    }

    @Override
    public Object previous() {
        if (pos > 0) {
            pos--;
        }
        return collection.get(pos);
    }

    @Override
    public Object next() {
        if (pos < collection.size() - 1) {
            pos++;
        }
        return collection.get(pos);
    }

    @Override
    public boolean hastNext() {
        if (pos < collection.size() - 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Object first() {
        pos = 0;
        return collection.get(pos);
    }
}
