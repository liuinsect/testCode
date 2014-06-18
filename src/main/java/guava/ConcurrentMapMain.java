package guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created with IntelliJ IDEA.
 * User: liukunyang
 * Date: 14-4-4
 * Time: 下午3:32
 * To change this template use File | Settings | File Templates.
 */
public class ConcurrentMapMain {

    public static void main(String[] args) {


        final ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 200,
                10, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(),new ThreadPoolExecutor.CallerRunsPolicy());

        final AtomicLong total = new AtomicLong(0);
        for (int i = 0; i < 200; i++) {
            threadPoolExecutor.execute( new Runnable() {
                @Override
                public void run() {
                    long start = System.currentTimeMillis();
                    for (int i = 0; i < 100000; i++) {
                        concurrentHashMap.put("a"+i,"a"+i);
                        concurrentHashMap.get("a"+i);
                    }

                    long end = System.currentTimeMillis();
                    total.addAndGet((end - start));
                }
            });
        }

        threadPoolExecutor.shutdown();
        while( true ){
            if( threadPoolExecutor.isTerminated()){
                System.out.println( "take:" + total.get()/100 +" ms" );
                return;
            }

        }

    }

}
