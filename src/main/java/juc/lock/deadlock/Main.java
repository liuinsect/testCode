package juc.lock.deadlock;

/**
 * Created with IntelliJ IDEA.
 * User: liukunyang
 * Date: 14-4-9
 * Time: 上午10:40
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    public static void main(String[] args) {
        final Task task = new Task();

        new Thread(new Runnable() {
            @Override
            public void run() {
                task.executeTask1();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                task.executeTask2();
            }
        }).start();


    }

}
