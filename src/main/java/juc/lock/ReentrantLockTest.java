package juc.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA.
 * User: liukunyang
 * Date: 14-1-26
 * Time: 下午5:26
 * To change this template use File | Settings | File Templates.
 */
public class ReentrantLockTest {

    public static void main(String[] args) throws InterruptedException {
        final ReentrantLock reentrantLock = new ReentrantLock();
        final Condition condition = reentrantLock.newCondition();
        int i = 0;
        do{
            System.out.println("ff");
            i++;
        }while(i<1);

//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    reentrantLock.juc.lock();
//                    System.out.println("我要等一个新信号" + this );
//                    condition.await();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//                }
//                System.out.println("拿到一个信号！！"+ this);
//                reentrantLock.unlock();
//            }
//        },"waitThread1");
//
//        thread.start();
//        Thread thread1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                reentrantLock.juc.lock();
//                System.out.println("我拿到锁了");
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//                }
//                condition.signalAll();
//                System.out.println("我发了一个信号！！");
//                reentrantLock.unlock();
//            }
//        },"singalThread");
//
//        thread1.start();

    }

}
