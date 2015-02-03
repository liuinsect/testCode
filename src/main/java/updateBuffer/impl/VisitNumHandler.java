package updateBuffer.impl;

import updateBuffer.Handler;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by liukunyang on 15-2-3.
 */
public class VisitNumHandler extends Handler {

    @Override
    protected Object initBufferLine() {
        return new AtomicInteger();
    }

    @Override
    protected boolean doUpdate(Object value, Object newValue) {
        AtomicInteger atomicInteger = (AtomicInteger)value;
        System.out.println("oldvalue"+value);
        System.out.println("newvalue"+newValue);
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
