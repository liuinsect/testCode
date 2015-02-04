package updateBuffer;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by liukunyang on 15-1-27.
 */
public class UpdateThreadPool {

    private Buffer<Object, BufferValue> buffer;

    private ThreadPoolExecutor threadPool;


    public UpdateThreadPool( int workerSize , Buffer<Object, BufferValue> buffer ) {
        this.buffer = buffer;
        threadPool = new ThreadPoolExecutor(workerSize, workerSize,
                60L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(),
                new BufferThreadFactory("PersistentThreadPool"),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

    private BlockingQueue<UpdateRequestEntry> updateRequestQueue = new LinkedBlockingQueue<UpdateRequestEntry>();

    public void start() {
        while (true) {
            UpdateRequestEntry updateRequestEntry = null;
            try {
                updateRequestEntry = updateRequestQueue.take();
                threadPool.execute(new UpdateThread(buffer,updateRequestEntry));
            } catch (InterruptedException e) {

            }

        }
    }

    public void add(UpdateRequestEntry updateRequestEntry) {
        updateRequestQueue.add(updateRequestEntry);
    }

}
