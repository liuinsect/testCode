package util.convertbean;

/**
 * Created with IntelliJ IDEA.
 * User: liukunyang
 * Date: 14-7-18
 * Time: 上午8:54
 * To change this template use File | Settings | File Templates.
 */
public class Hash {

    private int hash(Object key) {
        int h;
        return (h = key.hashCode()) ^ (h >>> 16);
    }

    protected int position(Object key,int capacity){
        int hash = hash(key);
        return (capacity - 1) & hash;
    }
}
