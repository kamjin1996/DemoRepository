package mycontainer.mycollections;

import java.util.*;

/**
 * @auther: kam
 * @date: 17:18 2019-01-16
 * @description: 集合工具测试类
 */
public class CollectionsUtilTest {

    private static final Integer INTEGER_ONE = 1;

    /**
     * @param coll
     * @return
     * @see {@link org.apache.commons.collections.CollectionUtils}#getCardinalityMap
     * 统计，计数
     */
    public static Map getCardinalityMap(final Collection coll) {
        Map count = new HashMap();
        for (Iterator it = coll.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            Integer c = (Integer) (count.get(obj));
            if (c == null) {
                count.put(obj, INTEGER_ONE);
            } else {
                count.put(obj, new Integer(c.intValue() + 1));
            }
        }
        return count;
    }

    /**
     * 集合的并集
     *
     * @param a
     * @param b
     * @return
     */
    public static Collection union(final Collection a, final Collection b) {
        ArrayList list = new ArrayList();
        Map mapa = getCardinalityMap(a);
        Map mapb = getCardinalityMap(b);
        Set elts = new HashSet(a);
        elts.addAll(b);
        Iterator it = elts.iterator();
        while (it.hasNext()) {
            Object obj = it.next();
            for (int i = 0, m = Math.max(getFreq(obj, mapa), getFreq(obj, mapb)); i < m; i++) {
                list.add(obj);
            }
        }
        return list;
    }

    /**
     * 根据键obj得到值，即Integer类型的个数
     *
     * @param obj
     * @param freqMap
     * @return
     */
    private static final int getFreq(final Object obj, final Map freqMap) {
        Integer count = (Integer) freqMap.get(obj);
        if (count != null) {
            return count.intValue();
        }
        return 0;
    }

    public static void main(String[] args) {
        List<String> lista = Arrays.asList("小明", "小李", "小张", "小黄", "小黑", "小白", "小红", "小李", "小李", null, null, "");
        System.out.println(lista.size());
        List<Object> listb = Arrays.asList("A", "B", "B", false, "C", true, "E", true, "小李", null, null, "");
        Collection union = union(lista, listb);
        System.out.println(union);
        //{null=2, =1, 小明=1, 小李=3, 小白=1, 小红=1, 小黑=1, 小张=1, 小黄=1}
        //debug F9
    }

}
