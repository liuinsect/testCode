package main.java.juc.lock.readWriteLock;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created with IntelliJ IDEA.
 * User: liukunyang
 * Date: 14-6-18
 * Time: 上午11:16
 * To change this template use File | Settings | File Templates.
 */
public class Main {

     //为什么感觉写锁的优先权更高？ 是因为写锁的请求会放在第一位的原因吗？
    public static void main(String[] args) {
        final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(10, 10,
                60L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>());

        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    readWriteLock.readLock().lock();
                    try {
                        System.out.println("拿到读锁");
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }

                    readWriteLock.readLock().unlock();
                    System.out.println("读锁释放");
                }
            }
        });


        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                for (int i = 0; i < 20; i++) {
                    readWriteLock.writeLock().lock();
                    try {
                        System.out.println("拿到写锁");
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }

                    readWriteLock.writeLock().unlock();

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }
            }
        });

    }

}
