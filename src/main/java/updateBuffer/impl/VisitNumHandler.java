package updateBuffer.impl;

import updateBuffer.Handler;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by liukunyang on 15-2-3.
 */
public class VisitNumHandler implements Handler {

    @Override
    public Object initBufferLine() {
        return new AtomicInteger();
    }

    @Override
    public boolean doUpdate(Object value, Object newValue) {
        AtomicInteger atomicInteger = (AtomicInteger)value;
        atomicInteger.addAndGet((Integer) newValue);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public void persisted(Object key, Object value) {
        System.out.println("VisitNumHandler,key:" + key);
        System.out.println("VisitNumHandler,value:" + value);
    }

}
