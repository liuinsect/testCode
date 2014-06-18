package thread.queue;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created with IntelliJ IDEA.
 * User: liukunyang
 * Date: 14-3-21
 * Time: 下午5:32
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingQueue queue = new  LinkedBlockingQueue(5);
        queue.put(new Object());
        queue.put(new Object());
        queue.put(new Object());
        queue.put(new Object());
        queue.put(new Object());


        queue.take();

    }
}
