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
        Handler handler = updateRequestEntry.getHandler();
        Object key = updateRequestEntry.getKey();
        Buffer<Object,BufferValue> buffer = updateRequestEntry.getBuffer();
        BufferValue bufferValue = buffer.get(key);
        if (bufferValue == null) {
            Object value = handler.initBufferLine();
            bufferValue = new BufferValue();
            bufferValue.setHandler(handler);
            bufferValue.setKey( key );
            bufferValue.setValue(value);

            BufferValue newBufferValue = buffer.putIfAbsent( key, bufferValue);
            if( newBufferValue != null ){
                bufferValue = newBufferValue;
            }

        }

        if (handler.doUpdate( bufferValue.getValue(), updateRequestEntry.getValue() )) {
            buffer.notifyWaiter();
        }

//        updateRequestEntry.getHandler().update(updateRequestEntry);

    }
}
