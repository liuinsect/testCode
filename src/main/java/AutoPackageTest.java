import thread.ThreadTest;

import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 * User: liukunyang
 * Date: 14-5-4
 * Time: 下午3:11
 * To change this template use File | Settings | File Templates.
 */
public class AutoPackageTest  extends ThreadTest {


    public static void a(Long l){


        long lon = l;

        lon = lon+1;

        System.out.println(lon);

    }


    public static void main(String[] args) {

          /* Method[] classes = AutoPackageTest.class.getDeclaredMethods();
           Method[] z = AutoPackageTest.class.getMethods();*/

        System.out.println(null instanceof String );
//        AutoPackageTest.a(null);

    }




}
