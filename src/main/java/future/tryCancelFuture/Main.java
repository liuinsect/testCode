package future.tryCancelFuture;

import java.util.concurrent.*;

/**
 * 通过线程池 开启一个线程去处理
 * 模拟请求来时，开启一个线程处理请求的场景
 * Created with IntelliJ IDEA.
 * User: liukunyang
 * Date: 13-12-13
 * Time: 上午9:51
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    public static void main(String[] args) throws Exception {

        ThreadPoolExecutor exec = new ThreadPoolExecutor(0, 10,
                5, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(),new ThreadPoolExecutor.CallerRunsPolicy());

        TestThread tt = new TestThread();
        exec.submit( tt );

        //保持主线程存货， console端观察结果
        System.in.read();
    }

}

