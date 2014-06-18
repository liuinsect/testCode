package future.tryCancelFuture;

import java.util.concurrent.Callable;

/**
 * Created with IntelliJ IDEA.
 * User: liukunyang
 * Date: 13-12-13
 * Time: 上午11:05
 * To change this template use File | Settings | File Templates.
 */
public class DivideFuture implements Callable {

    private ThreadLocal thISC1 = new ThreadLocal();
    private ThreadLocal<Boolean> thISC2 = new ThreadLocal<Boolean>();
    private ThreadLocal thISC3 = new ThreadLocal();

    private boolean isC1;
    private Boolean isC2;
    private RichBoolean isC3;


    /**
     * 修改isC1,isC2,isC3的标志位
     */
    public void cancel(){
        isC1 = true;
        isC2 = true;
        isC3.setValue(true);

    }

    @Override
    public Object call() throws Exception {
        //在线程调用时，再将isC初始化，
        //是的threadLocal取到的线程是执行该方法的线程
        isC1 = false;
        isC2 = false;
        isC3 = new RichBoolean(false);
        thISC1.set(isC1);
        thISC2.set(isC2);
        thISC3.set(isC3);
        for(int i=0; i<10 ; i++){
            try{
                Thread.sleep(1000);
            }catch(Exception e){

            }
            System.out.println(i);
            System.out.println( "变量 isC1 的值:" + isC1 );
            System.out.println( "变量 isC1 通过ThreadLocal获取的值:" + thISC1.get() );

            System.out.println( "变量 isC2 的值:" + isC2 );
            System.out.println( "变量 isC2 通过ThreadLocal获取的值:" + thISC2.get() );

            System.out.println( "变量 isC3 的值:" + isC3 );
            System.out.println( "变量 isC3 通过ThreadLocal获取的值:" + thISC3.get() );
        }
        return "this is callable";
    }
}
