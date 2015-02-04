package updateBuffer;

/**
 *
 * 异步更新工具类
 * 解决更新频繁的问题，
 * 可以使用多种更新策略
 * 定时/定量  等等。
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
        updateThreadPool = new UpdateThreadPool(5,buffer);
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
        updateThreadPool.add(new UpdateRequestEntry(key,object,handler));
    }


}

