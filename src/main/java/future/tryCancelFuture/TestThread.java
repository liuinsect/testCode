package future.tryCancelFuture;

import java.util.concurrent.*;

/**
 * 内部在开启一个future去处理请求
 * 在future超时后，看future能否通过自己的check 终止自己
 * 减少future超时后还在占用资源的情况
 * Created with IntelliJ IDEA.
 * User: liukunyang
 * Date: 13-12-13
 * Time: 上午9:55
 * To change this template use File | Settings | File Templates.
 */
public class TestThread extends  Thread {

    private ThreadPoolExecutor threadPoolExecutor =
                                    new ThreadPoolExecutor(0, 10, 5, TimeUnit.SECONDS,
                                    new LinkedBlockingQueue<Runnable>(),new ThreadPoolExecutor.CallerRunsPolicy());

    ;


    @Override
    public void run() {
        ThreadLocal th = new ThreadLocal();
        DivideFuture df = new DivideFuture();

        final Future future =  threadPoolExecutor.submit(df);

        try {
            //5秒后在超时，
            //给子线程5秒的时间打印 变量isC1,isC2,isC3的值
            System.out.println( "1"+future.get(5,TimeUnit.SECONDS) );

        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ExecutionException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (TimeoutException e) {
            System.out.println("超时拉");
        }finally {
            df.cancel();
            future.cancel(true);
        }

    }
}
