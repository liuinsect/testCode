package updateBuffer;

import org.apache.log4j.Logger;

import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by liukunyang on 15-1-27.
 */
public class Buffer<K, V> {

    private Logger logger = Logger.getLogger(Buffer.class);

    private ConcurrentHashMap<K, V> concurrentHashMap = new ConcurrentHashMap<K, V>();
    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private Condition writeLockCondition = readWriteLock.writeLock().newCondition();
    ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();
    ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();

    public V get(Object key) {
        try {
            readLock.lock();
            return concurrentHashMap.get(key);
        } finally {
            readLock.unlock();
        }
    }


    public V putIfAbsent(K key, V value) {
        try {
            writeLock.lock();
            return concurrentHashMap.putIfAbsent(key, value);
        } finally {
            writeLock.unlock();
        }
    }

    public V remove(K key) {
        try {
            writeLock.lock();
            return concurrentHashMap.remove(key);
        } finally {
            writeLock.unlock();
        }
    }

    public Enumeration<K> keys() {
        try {
            readLock.lock();
            return concurrentHashMap.keys();
        } finally {
            readLock.unlock();
        }
    }


    public boolean isEmpty() {
        try {
            readLock.lock();
            return concurrentHashMap.isEmpty();
        } finally {
            readLock.unlock();
        }
    }

    public void waitForTrigger(){
        try {
            writeLock.lock();
            writeLockCondition.await();
        } catch (InterruptedException e) {
            logger.error("buffer writeLockCondition has been interrupted",e);
        } finally {
            writeLock.unlock();
        }
    }

    public void notifyWaiter(){
        try {
            writeLock.lock();
            writeLockCondition.signalAll();
        } finally {
            writeLock.unlock();
        }
    }

}
