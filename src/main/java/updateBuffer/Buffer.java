package updateBuffer;

import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by liukunyang on 15-1-27.
 */
public class Buffer<K, V> {

    private ConcurrentHashMap<K, V> concurrentHashMap = new ConcurrentHashMap<K, V>();
    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public V get(Object key) {
        ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
        try {
            readLock.lock();
            return concurrentHashMap.get(key);
        } finally {
            readLock.unlock();
        }
    }


    public V putIfAbsent(K key, V value) {
        ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();
        try {
            writeLock.lock();
            return concurrentHashMap.putIfAbsent(key, value);
        } finally {
            writeLock.unlock();
        }
    }

    public V remove(K key) {
        ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();
        try {
            writeLock.lock();
            return concurrentHashMap.remove(key);
        } finally {
            writeLock.unlock();
        }
    }

    public Enumeration<K> keys() {
        ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
        try {
            readLock.lock();
            return concurrentHashMap.keys();
        } finally {
            readLock.unlock();
        }
    }


    public boolean isEmpty() {
        ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
        try {
            readLock.lock();
            return concurrentHashMap.isEmpty();
        } finally {
            readLock.unlock();
        }
    }

}
