package future;

import java.io.IOException;
import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 * User: liukunyang
 * Date: 13-12-13
 * Time: 上午9:31
 * To change this template use File | Settings | File Templates.
 */
public class AfterMainTryInterrupt {

    public static void main(String[] args) {

        Thread.currentThread().setName("AfterMainTryInterruptMain");
        ThreadPoolExecutor exec = new ThreadPoolExecutor(0, 10,
                5, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(),new ThreadPoolExecutor.CallerRunsPolicy());

        Object result = null;
        final Future future =  exec.submit(new Callable() {
            @Override
            public Object call() throws Exception {
                try{
                    Thread.sleep(1000);
                }catch(Exception e){

                }

                for(int i=0; i<10 ; i++){
                    try{
                        Thread.sleep(1000);
                    }catch(Exception e){

                    }
                    System.out.println(i);
                    System.out.println( Thread.currentThread().getName());
                    System.out.println( Thread.currentThread().isInterrupted());
                }
                return "this is callable";  //To change body of implemented methods use File | Settings | File Templates.
            }
        });


        try {
            System.out.println( Thread.currentThread().getName());
            System.out.println( "1"+future.get(1,TimeUnit.SECONDS) );

        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ExecutionException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (TimeoutException e) {
            System.out.println("超时拉");
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }finally {
            future.cancel(true);

        }

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

}
