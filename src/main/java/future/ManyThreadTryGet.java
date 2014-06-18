package future;

import java.util.concurrent.*;
import java.lang.Runnable;

/**
 *
 * 多线程尝试获取同一个 future
 *
 * Created with IntelliJ IDEA.
 * User: liukunyang
 * Date: 13-12-11
 * Time: 下午8:02
 * To change this template use File | Settings | File Templates.
 */
public class ManyThreadTryGet {

    public static void main(String[] args) {

        ThreadPoolExecutor exec = new ThreadPoolExecutor(0, 10,
                5, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(),new ThreadPoolExecutor.CallerRunsPolicy());

        Object result = null;
        final Future future =  exec.submit(new Callable() {
            @Override
            public Object call() throws Exception {
                try{
                    Thread.sleep(10000);
                }catch(Exception e){

                }
                return "this is callable";  //To change body of implemented methods use File | Settings | File Templates.
            }
        });


        exec.submit(new Runnable() {
            @Override
            public void run() {
                try {

                    System.out.println( "1"+future.get() );
                    future.cancel(true);
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (ExecutionException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        });


        exec.submit(new Runnable() {
            @Override
            public void run() {
                try {

                    System.out.println( "2"+future.get() );
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (ExecutionException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        });

    }

}
