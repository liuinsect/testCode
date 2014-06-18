package reflect;

/**
 * Created with IntelliJ IDEA.
 * User: liukunyang
 * Date: 14-4-12
 * Time: 上午10:07
 * To change this template use File | Settings | File Templates.
 */
public class GenericsFoo <T extends Integer> {
    private T x;
    public T getX() {
        return x;
    }

    public void setX(T x) {
        this.x = x;
    }
}