package updateBuffer;

import org.apache.log4j.Logger;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 单线程方式处理
 * Created with IntelliJ IDEA.
 * User: liukunyang
 * Date: 14-6-18
 * Time: 下午5:54
 * To change this template use File | Settings | File Templates.
 */
public abstract class Handler {

    private Logger logger = Logger.getLogger(Handler.class);

    public void update(UpdateRequestEntry entry) {
        Buffer<Object,BufferValue> buffer = entry.getBuffer();
        BufferValue bufferValue = (BufferValue) buffer.get(entry.getKey());
        if (bufferValue == null) {
            Object value = initBufferLine();
            bufferValue = new BufferValue();
            bufferValue.setHandler(entry.getHandler());
            bufferValue.setKey(entry.getKey());
            bufferValue.setValue(value);

            BufferValue newBufferValue = (BufferValue) buffer.putIfAbsent(entry.getKey(), bufferValue);
            if( newBufferValue != null ){
                bufferValue = newBufferValue;
            }

        }

        if (doUpdate(bufferValue.getValue(),entry.getValue())) {
            notifyEntryWaiter(entry);
        }

    }

    protected abstract Object initBufferLine();

    private void notifyEntryWaiter(UpdateRequestEntry entry) {
        Buffer buffer = entry.getBuffer();
        synchronized (buffer) {
            buffer.notify();
            logger.debug("notify over");
        }

    }

    protected abstract boolean doUpdate(Object value,Object newValue);

    public abstract void persisted( Object key , Object value );


}
