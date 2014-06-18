package future;

import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 * User: liukunyang
 * Date: 13-11-26
 * Time: 上午9:52
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    public static void main(String[] args) {


        long startTime = System.currentTimeMillis();
        ExecutorService exec = new ThreadPoolExecutor(0, 10,
                                                        5, TimeUnit.SECONDS,
                                                        new LinkedBlockingQueue<Runnable>(),new ThreadPoolExecutor.CallerRunsPolicy());

        Object result = null;
        Future future =  exec.submit(new Callable() {
            @Override
            public Object call() throws Exception {

                System.out.print("ok");
                try{
                    Thread.sleep(6000);
                }catch(Exception e){

                }
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });

        long start = System.currentTimeMillis();
        try{
            result = future.get();
            System.out.println( System.currentTimeMillis() - start);
        }catch (Exception e) {
           e.printStackTrace();
        }finally{
            future.cancel(true);
        }

        System.out.println( result );
        System.out.println( System.currentTimeMillis() - startTime );


    }

}
