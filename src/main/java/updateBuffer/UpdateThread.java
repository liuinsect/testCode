package updateBuffer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by liukunyang on 15-1-27.
 */
public class UpdateThread implements Runnable {

    private UpdateRequestEntry updateRequestEntry;
    private Buffer<Object,BufferValue> buffer;

    public UpdateThread(Buffer<Object,BufferValue> buffer,UpdateRequestEntry updateRequestEntry) {
        this.buffer = buffer;
        this.updateRequestEntry = updateRequestEntry;
    }

    @Override
    public void run() {
        Handler handler = updateRequestEntry.getHandler();
        Object key = updateRequestEntry.getKey();
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
            }else{
                bufferValue = buffer.get(key);
            }

        }

        if (handler.doUpdate( bufferValue.getValue(), updateRequestEntry.getValue() )) {
            buffer.notifyWaiter();
        }

//        updateRequestEntry.getHandler().update(updateRequestEntry);

    }
}
