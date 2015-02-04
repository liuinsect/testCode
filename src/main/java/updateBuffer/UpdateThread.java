package updateBuffer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by liukunyang on 15-1-27.
 */
public class UpdateThread implements Runnable {

    private UpdateRequestEntry updateRequestEntry;

    public UpdateThread(UpdateRequestEntry updateRequestEntry) {
        this.updateRequestEntry = updateRequestEntry;
    }

    @Override
    public void run() {
        updateRequestEntry.getHandler().update(updateRequestEntry);

    }
}
