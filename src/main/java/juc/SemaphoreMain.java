package juc;

import java.io.IOException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Semaphore;

/**
 * Created with IntelliJ IDEA.
 * User: liukunyang
 * Date: 14-4-22
 * Time: 下午1:25
 * To change this template use File | Settings | File Templates.
 */
public class SemaphoreMain {


    public static void main(String[] args) throws InterruptedException, IOException {
        final Semaphore semaphore = new Semaphore(5);

        semaphore.acquire(5);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    semaphore.acquire(1);
                    System.out.println("get it ");
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        });
        t1.start();;

        Thread.sleep(3000);

        semaphore.release(3);
        System.out.println("release 3");

        System.in.read();


    }

}
