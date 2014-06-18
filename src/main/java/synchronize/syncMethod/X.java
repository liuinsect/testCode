package synchronize.syncMethod;

/**
 * Created with IntelliJ IDEA.
 * User: liukunyang
 * Date: 14-3-13
 * Time: 上午10:50
 * To change this template use File | Settings | File Templates.
 */
public class X {

    private int  x;

    public void setX(int x) throws InterruptedException {
        synchronized (X.class){
            this.x = x;               // (1)
            System.out.println("我上锁了");
            Thread.sleep(30000);
            System.out.println("我解锁了");
        }

    }

    public synchronized int getX() {
        return x;                 // (2)
    }
}
