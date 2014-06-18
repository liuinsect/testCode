package juc;

import java.io.IOException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created with IntelliJ IDEA.
 * User: liukunyang
 * Date: 14-4-11
 * Time: 下午5:21
 * To change this template use File | Settings | File Templates.
 */
public class CyclicBarrierMain {

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException, TimeoutException, IOException {
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(5,new Runnable() {
            @Override
            public void run() {
                System.out.println("这里是主线程"+  Thread.currentThread().getName() );
            }
        });

        Thread t1 = null;
        for (int i = 0; i < 5; i++) {
            t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println( Thread.currentThread().getName() );
                        cyclicBarrier.await();
                        System.out.println("await over~!"+Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }

                }
            });
            t1.start();;
        }

        System.out.println("t1 is " + t1.getName());
        t1.join();

        System.out.println( Thread.currentThread().getName() );
        System.in.read();




    }


}
