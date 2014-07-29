package util.convertbean;

/**
 * Created with IntelliJ IDEA.
 * User: liukunyang
 * Date: 14-7-18
 * Time: 上午8:53
 * To change this template use File | Settings | File Templates.
 */
public interface Iterator<T> {


    public T next();

    public boolean equal(Object o);

    public void setNext(T t);


}
