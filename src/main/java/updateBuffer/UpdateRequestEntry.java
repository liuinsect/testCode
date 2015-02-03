package updateBuffer;

/**
 * Created by liukunyang on 15-1-28.
 */
public class UpdateRequestEntry {


    private Object key;
    private Object value;
    private Handler handler;
    private Buffer<Object,BufferValue> buffer;

    public UpdateRequestEntry(Buffer buffer, Object key, Object value, Handler handler) {
        this.buffer = buffer;
        this.key = key;
        this.value = value;
        this.handler = handler;
    }

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public Buffer getBuffer() {
        return buffer;
    }

    public void setBuffer(Buffer buffer) {
        this.buffer = buffer;
    }

}
