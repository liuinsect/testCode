package guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Range;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created with IntelliJ IDEA.
 * User: liukunyang
 * Date: 14-4-3
 * Time: 下午5:12
 * To change this template use File | Settings | File Templates.
 */
public class LoadingCacheMain {






    public static void main(String[] args) throws ExecutionException, IOException {
        Range range = Range.open("a","d");


        System.out.println(range.contains("c"));;


        final LoadingCache graphs = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(100000, TimeUnit.MILLISECONDS)
                .build(new CacheLoader() {
                    @Override
                    public Object load(Object key) throws Exception {
                        return key;
                    }
                });
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 200,
                                                                    10, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(),new ThreadPoolExecutor.CallerRunsPolicy());
        final AtomicLong total = new AtomicLong(0);
        for (int i = 0; i < 1; i++) {
            threadPoolExecutor.execute( new Runnable() {
                @Override
                public void run() {
                    long start = System.currentTimeMillis();
                    for (int i = 0; i < 100000; i++) {
                        try {
                            graphs.get("a"+i);
                        } catch (ExecutionException e) {
                            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                        }
                    }

                    long end = System.currentTimeMillis();
                    total.addAndGet((end-start));
                }
            });
        }

        System.in.read();
        threadPoolExecutor.shutdown();
        while( true ){
            if( threadPoolExecutor.isTerminated()){
                System.out.println( "take:" + total.get()/100 +" ms" );
                return;
            }

        }

    }

}
