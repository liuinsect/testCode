package synchronize.syncMethod;

/**
 * Created with IntelliJ IDEA.
 * User: liukunyang
 * Date: 14-3-13
 * Time: 上午10:51
 * To change this template use File | Settings | File Templates.
 */
public class Main {


    public static void main(String[] args) {
        final X x = new X();

         Thread t = new Thread(new Runnable(){

             @Override
             public void run() {
                 try {
                     x.setX(3);
                 } catch (InterruptedException e) {
                     e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                 }
             }
         });

        Thread t1 = new Thread(new Runnable(){

            @Override
            public void run() {

                System.out.println( " i'm get :" + x.getX());

            }
        });

        t.start();
        t1.start();

    }




}
