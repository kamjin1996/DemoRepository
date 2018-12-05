package mycontainer.hashmap;

/**
 * @Auther: KAM1996
 * @Date: 15:41 2018-11-08
 * @Description:
 * @Version: 1.0
 */ //MyEntry
class MyEntry<K,V>{
    K key;
    V value;

    public MyEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }
}
