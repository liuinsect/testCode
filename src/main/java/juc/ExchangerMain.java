package juc;

import java.util.concurrent.Exchanger;

/**
 * Created with IntelliJ IDEA.
 * User: liukunyang
 * Date: 14-4-22
 * Time: 下午7:38
 * To change this template use File | Settings | File Templates.
 */
public class ExchangerMain {


    public static void main(String[] args) {
        Exchanger exchanger = new Exchanger();

//        ExchangerRunnable exchangerRunnable1 = new ExchangerRunnable(exchanger, "A");

//        ExchangerRunnable exchangerRunnable2 = new ExchangerRunnable(exchanger, "B");
//
//        ExchangerRunnable exchangerRunnable3 = new ExchangerRunnable(exchanger, "C");
//
//        ExchangerRunnable exchangerRunnable4 = new ExchangerRunnable(exchanger, "D");

//        new Thread(exchangerRunnable1).start();
//        new Thread(exchangerRunnable2).start();
//        new Thread(exchangerRunnable3).start();
//        new Thread(exchangerRunnable4).start();


        long id = 4;
        int hash = (((int)(id ^ (id >>> 32))) ^ 0x811c9dc5) * 0x01000193;

        int m = 2;
        int nbits = (((0xfffffc00  >> m) & 4) | // Compute ceil(log2(m+1))
                ((0x000001f8 >>> m) & 2) | // The constants hold
                ((0xffff00f2 >>> m) & 1)); // a lookup table
        int index;
        while ((index = hash & ((1 << nbits) - 1)) > m)       // May retry on
            hash = (hash >>> nbits) | (hash << (33 - nbits)); // non-power-2 m

        System.out.println(index);




    }


    static class ExchangerRunnable implements Runnable{

        Exchanger exchanger = null;
        Object    object    = null;

        public ExchangerRunnable(Exchanger exchanger, Object object) {
            this.exchanger = exchanger;
            this.object = object;
        }

        public void run() {
            try {
                Object previous = this.object;

                System.out.println(
                        Thread.currentThread().getName() + "pre exchange"
                );

                this.object = this.exchanger.exchange(this.object);

                System.out.println(
                        Thread.currentThread().getName() +
                                " exchanged " + previous + " for " + this.object
                );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
