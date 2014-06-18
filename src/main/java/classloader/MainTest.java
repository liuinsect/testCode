package classloader;

/**
 * Created with IntelliJ IDEA.
 * User: liukunyang
 * Date: 13-11-28
 * Time: 下午5:09
 * To change this template use File | Settings | File Templates.
 */
public class MainTest {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        Class loadedClass = Class.forName( "classloader.LoadedClass" );
//        Class loadedClass = MainTest.class.getClassLoader().loadClass("classloader.LoadedClass");
        loadedClass.newInstance();
        System.out.println("over");

    }

}
