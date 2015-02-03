package updateBuffer;

/**
 * Created by liukunyang on 15-1-28.
 */
public class PersistentThread implements  Runnable {

    private BufferValue bufferValue;

    public PersistentThread(BufferValue bufferValue) {
        this.bufferValue = bufferValue;
    }

    @Override
    public void run() {
        Handler handler = bufferValue.getHandler();
        handler.persisted(bufferValue.getKey(),bufferValue.getValue());
    }
}
