package synchronize;

/**
 * Created with IntelliJ IDEA.
 * User: liukunyang
 * Date: 14-1-10
 * Time: 下午5:33
 * To change this template use File | Settings | File Templates.
 */
public class syn方法重入功能_test {

    public synchronized void doSync(){
        System.out.println("this is synchronized");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public static void main(String[] args) {
        syn方法重入功能_test t = new syn方法重入功能_test();

        t.doSync();
        t.doSync();
        t.doSync();
        t.doSync();
        t.doSync();

    }

}
