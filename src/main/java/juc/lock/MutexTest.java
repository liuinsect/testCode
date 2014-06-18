package juc.lock;

import com.sun.corba.se.impl.orbutil.concurrent.Mutex;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: liukunyang
 * Date: 14-1-10
 * Time: 下午5:41
 * To change this template use File | Settings | File Templates.
 */
public class MutexTest {

    public static void main(String[] args) {

        Mutex mutex = new Mutex();
        Runner runner = null;
        for (int i = 0; i < 10; i++) {
            if( runner != null ){
                runner.interrupt();
            }
            runner = new Runner(mutex);
            runner.start();
        }

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }


   static class Runner extends Thread{

        private Mutex mutex;

        Runner(Mutex mutex) {
            this.mutex = mutex;
        }

        @Override
        public void run() {
//            System.out.println("准备上锁");
            try {
                mutex.acquire();
                System.out.println("上锁成功");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("上锁失败");
            }
            mutex.release();
            System.out.println("释放成功");
        }
    }

}
