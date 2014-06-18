package thread;

/**
 * 多线程操作 静态方法，静态方法内部的变量会不会线程不安全
 * Created with IntelliJ IDEA.
 * User: liukunyang
 * Date: 14-2-21
 * Time: 下午5:17
 * To change this template use File | Settings | File Templates.
 */
public class StaticAndThreadTest {

    public static int ii;

    public static int  increment( int i ){
        i++;
        return i;
    }

    public static void  incrementShaerI( ){
        ii++;
    }


    public static void main(String[] args) {

        for (int i = 0; i < 200; i++) {
            new ThreadTest().start();
        }


    }

}
