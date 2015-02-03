package updateBuffer;

/**
 *
 * 异步更新工具类
 * Created with IntelliJ IDEA.
 * User: liukunyang
 * Date: 14-6-17
 * Time: 下午3:55
 * To change this template use File | Settings | File Templates.
 */
public class UpdateBuffer {

    private static Buffer<Object,BufferValue> buffer = new Buffer<Object,BufferValue>();

    private static UpdateThreadPool updateThreadPool = null;

    private static PersistentThreadPool persistentThreadPool = null;

    static{
        updateThreadPool = new UpdateThreadPool(buffer);
        persistentThreadPool = new PersistentThreadPool(5,buffer);

        new Thread(new Runnable() {
            @Override
            public void run() {
                updateThreadPool.start();

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                persistentThreadPool.start();
            }
        }).start();


    }

    public static void update(final Object key , final Object object ,final Handler handler ){
        updateThreadPool.add(new UpdateRequestEntry(buffer,key,object,handler));
    }


}

