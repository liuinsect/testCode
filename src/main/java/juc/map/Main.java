package juc.map;

import java.util.Enumeration;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by liukunyang on 15-1-28.
 */
public class Main {


    public static void main(String[] args) throws InterruptedException {

        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 5,
                60L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(),
                new ThreadPoolExecutor.CallerRunsPolicy());

        final ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();

        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 50; i++) {
                    System.out.println("生产者开始生产");
//                    double key = Math.random() * 1000;
//                    double value = Math.random() * 1000;
                    concurrentHashMap.put("key"+i, "value"+i);

                    synchronized (concurrentHashMap) {
                        concurrentHashMap.notify();
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {

                    }
                    System.out.println("生产者生产完毕，当前长度：" + concurrentHashMap.size());
                }
            }
        });

//        Thread.sleep(3000);
        System.out.println(concurrentHashMap.size());


        final AtomicInteger num = new AtomicInteger(0);
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                String key;
                while (true) {
                    System.out.println("消费者开始消费" + concurrentHashMap.size());
                    Enumeration enumeration = concurrentHashMap.keys();
                    while (enumeration.hasMoreElements()) {
                        num.incrementAndGet();
                        key = (String) enumeration.nextElement();
                        System.out.println(key);
                        concurrentHashMap.remove(key);
                    }

                    System.out.println("消费者消费完毕，准备沉睡");
                    try {
                        synchronized (concurrentHashMap) {
                            concurrentHashMap.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


    }

}

