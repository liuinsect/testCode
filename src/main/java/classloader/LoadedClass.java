package classloader;

/**
 * 被加载的Class
 * Created with IntelliJ IDEA.
 * User: liukunyang
 * Date: 13-11-28
 * Time: 下午5:10
 * To change this template use File | Settings | File Templates.
 */
public class LoadedClass {

    public static StaticClass staticClass = new StaticClass();

    static {
        System.out.println("this is static");
    }

    public LoadedClass() {
        System.out.println("this is Constructor");
    }
}
