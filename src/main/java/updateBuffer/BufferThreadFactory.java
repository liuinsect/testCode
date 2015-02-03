package updateBuffer;

import org.apache.log4j.Logger;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by liukunyang on 15-1-28.
 */
public class BufferThreadFactory implements ThreadFactory{

    private Logger logger = Logger.getLogger(BufferThreadFactory.class);

    private final AtomicInteger threadNum = new AtomicInteger(0);

    private String name;

    public BufferThreadFactory(String name) {
        this.name = name;
    }

    @Override
    public synchronized Thread newThread(Runnable r) {
        Thread t = Executors.defaultThreadFactory().newThread(r);
        t.setName( name + " -" + threadNum.getAndIncrement());
        t.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                logger.error(t.getName() + " has Exception:", e);

            }
        });
        return t;
    }

}
