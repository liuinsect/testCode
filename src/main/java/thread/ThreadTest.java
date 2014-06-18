package thread;

/**
 * Created with IntelliJ IDEA.
 * User: liukunyang
 * Date: 14-2-21
 * Time: 下午5:23
 * To change this template use File | Settings | File Templates.
 */
public class ThreadTest extends Thread {

    @Override
    public void run() {

        int i = 0;
        for (int j = 0; j < 500; j++) {
            i = StaticAndThreadTest.increment(i);
        }

        System.out.println(this.getName()+":"+i);
    }
}
