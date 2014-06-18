package inner;

/**
 * Created with IntelliJ IDEA.
 * User: liukunyang
 * Date: 14-4-1
 * Time: 下午8:15
 * To change this template use File | Settings | File Templates.
 *
 * 局部变量 在匿名内部类中不能赋值，且引用是必须为final类型，而类变量和静态变量不需要赋值为final
 */
public class Main {

    public int i = 10;

    public static int staticI = 11;

    public void testScope(){
        Inter inter  = new Inter() {
            @Override
            public void sayHello() {
                i = 20;
                staticI = 21;
                System.out.println("inter："+Main.staticI);
            }
        };
        inter.sayHello();
    }



    public static void main(String[] args) {
        Main main = new Main();
        System.out.println(main.i);
        main.testScope();
        System.out.println(main.i);
        System.out.println(Main.staticI);

    }

}
