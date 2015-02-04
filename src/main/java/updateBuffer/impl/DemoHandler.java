package updateBuffer.impl;

import updateBuffer.*;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by liukunyang on 15-1-28.
 */
public class DemoHandler implements Handler {

    @Override
    public Object initBufferLine() {
        return  new AtomicInteger(0);
    }

    @Override
    public boolean doUpdate(Object value, Object newValue) {
        AtomicInteger num = (AtomicInteger)value;
        num.addAndGet((Integer) newValue);
        if( num.get() > 2 ){
            return true;
        }
        return false;
    }

    @Override
    public void persisted(Object key, Object value) {
        System.out.println("key:" + key);
        System.out.println("value:" + value);
    }


}
