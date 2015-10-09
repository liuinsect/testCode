package thread.ThreadLocal;

/**
 * Created by liukunyang on 15-10-9.
 */
public class InheritableThreadLocalTest {

    private static ThreadLocal<String> stringItl2 = new InheritableThreadLocal<String>(){
        protected String initialValue() {
            System.out.println(Thread.currentThread().getName() + " initialize stringItl2 variable.");
            return "String2 init";
        }
    };

    private static ThreadLocal<StringBuffer> stringBufferItl = new InheritableThreadLocal<StringBuffer>(){
        protected StringBuffer initialValue() {
            System.out.println(Thread.currentThread().getName() + " initialize stringBufferItl variable.");
            return new StringBuffer("StringBuffer init");
        }
    };

    public static void main(String[] args) {

        System.out.println(stringItl2.get());


        new Thread(){
            public void run(){
                System.out.println(Thread.currentThread().getName() + " first get stringItl : " + stringItl2.get());
//                stringItl.set(Thread.currentThread().getName() + "Child");
//                System.out.println(Thread.currentThread().getName() + " get after set stringItl : " + stringItl.get());
            }

        }.start();

    }

}
