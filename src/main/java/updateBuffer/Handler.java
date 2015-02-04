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
public interface Handler {

    Object initBufferLine();


    boolean doUpdate(Object value,Object newValue);

    void persisted( Object key , Object value );


}
