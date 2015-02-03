package updateBuffer.impl;

import updateBuffer.BufferValue;
import updateBuffer.Handler;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by liukunyang on 15-2-3.
 */
public class VisitLogHandler extends Handler {


    @Override
    protected Object initBufferLine() {
        return new ConcurrentLinkedQueue();
    }

    @Override
    protected boolean doUpdate(Object value, Object newValue) {
        ConcurrentLinkedQueue queue = (ConcurrentLinkedQueue)value;
        queue.add(newValue);
        if( queue.size() > 1 ){
            return true;
        }
        return false;
    }

    @Override
    public void persisted(Object key, Object value) {
        System.out.println("key:" + key);
        ConcurrentLinkedQueue queue = (ConcurrentLinkedQueue)value;
        Iterator it = queue.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }
    }


}
