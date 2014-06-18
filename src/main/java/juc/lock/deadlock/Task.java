package juc.lock.deadlock;

/**
 * Created with IntelliJ IDEA.
 * User: liukunyang
 * Date: 14-4-9
 * Time: 上午10:40
 * To change this template use File | Settings | File Templates.
 */
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * A simple thread task representation
 * @author Pierre-Hugues Charbonneau
 *
 */
public class Task {

    // Object used for FLAT juc.lock
    private final Object sharedObject = new Object();
    // ReentrantReadWriteLock used for WRITE & READ locks
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    /**
     *  Execution pattern #1
     */
    public void executeTask1() {

        // 1. Attempt to acquire a ReentrantReadWriteLock READ juc.lock
        lock.readLock().lock();
        System.out.println("拿到读锁了");
        // Wait 2 seconds to simulate some work...
        try { Thread.sleep(2000);}catch (Throwable any) {}

        try {
            // 2. Attempt to acquire a Flat juc.lock...
            synchronized (sharedObject) {
                System.out.println("executeTask1:同步sharedObject");
            }
        }
        // Remove the READ juc.lock
        finally {
            lock.readLock().unlock();
        }

        System.out.println("executeTask1() :: Work Done!");
    }

    /**
     *  Execution pattern #2
     */
    public void executeTask2() {

        // 1. Attempt to acquire a Flat juc.lock
        synchronized (sharedObject) {
            System.out.println("executeTask2:同步sharedObject");
            // Wait 2 seconds to simulate some work...
            try { Thread.sleep(2000);}catch (Throwable any) {}

            // 2. Attempt to acquire a WRITE juc.lock
            lock.writeLock().lock();
            System.out.println("executeTask2:拿到写锁了");
            try {
                // Do nothing
            }

            // Remove the WRITE juc.lock
            finally {
                lock.writeLock().unlock();
            }
        }

        System.out.println("executeTask2() :: Work Done!");
    }

    public ReentrantReadWriteLock getReentrantReadWriteLock() {
        return lock;
    }
}
