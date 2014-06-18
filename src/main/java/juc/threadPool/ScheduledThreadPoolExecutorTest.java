package juc.threadPool;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: liukunyang
 * Date: 14-6-17
 * Time: 下午1:56
 * To change this template use File | Settings | File Templates.
 */
public class ScheduledThreadPoolExecutorTest {


    public static void main(String[] args) {
        ScheduledThreadPoolExecutor poolExecutor = new ScheduledThreadPoolExecutor(2);
        poolExecutor.schedule(new Runnable(){
            @Override
            public void run() {
                try {
                    System.out.println("1");
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        },10, TimeUnit.SECONDS);

        poolExecutor.schedule(new Runnable(){
            @Override
            public void run() {
                try {
                    System.out.println("2");
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        },10, TimeUnit.SECONDS);


        poolExecutor.schedule(new Runnable(){
            @Override
            public void run() {
                try {
                    System.out.println("3");
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        },10, TimeUnit.SECONDS);

    }

}
