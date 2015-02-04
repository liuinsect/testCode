package updateBuffer;



import org.apache.log4j.Logger;

import java.util.Enumeration;
import java.util.concurrent.*;

/**
 * Created by liukunyang on 15-1-28.
 */
public class PersistentThreadPool {

    private final Logger logger = Logger.getLogger(PersistentThreadPool.class);

    private Buffer<Object, BufferValue> buffer;

    private ThreadPoolExecutor threadPool;

    public PersistentThreadPool(Integer workerSize,Buffer<Object, BufferValue> buffer) {
        this.buffer = buffer;
        threadPool = new ThreadPoolExecutor(workerSize, workerSize,
                60L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(),
                new BufferThreadFactory("PersistentThreadPool"),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

    public void start() {

        while(true){
            Enumeration enumeration = buffer.keys();



            while (enumeration.hasMoreElements()) {
                Object key = enumeration.nextElement();
                BufferValue bufferValue = (BufferValue) buffer.remove(key);
                if (bufferValue == null) {
                    continue;
                }

                threadPool.execute(new PersistentThread(bufferValue));

            }
            if( buffer.isEmpty() ){
                buffer.waitForTrigger();
            }
        }


    }

}
