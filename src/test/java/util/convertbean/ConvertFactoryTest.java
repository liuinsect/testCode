package util.convertbean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ConvertFactoryTest {


    public static void main(String[] args) {
        long time=0;
        for (int i = 0; i < 20; i++) {
            time+=testOne();
        }
        System.out.println("平均："+time/1000/1000/20);
    }

    public static long testOne(){
        int workerSize = 10;
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(workerSize, workerSize,
                60L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(),
                new ThreadPoolExecutor.CallerRunsPolicy());

        final CountDownLatch countDownLatch = new CountDownLatch(30);

       final  ConvertBeanFrom newFromBean = newFromBean();
        //        final List<AbTest> abTestList = new Con
        long start = System.nanoTime();

        for (int i = 0; i < 30; i++) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    ConvertBeanTo convertBeanTo = null;
                    boolean isE = false;
                    for (int i = 0; i < 10000; i++) {
                        convertBeanTo = ConvertFactory.from(newFromBean, ConvertBeanTo.class);


                    }
                    //                    abTestList.add(abTest);
                    countDownLatch.countDown();
                }
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        long end = System.nanoTime();
        System.out.println("take:" + (end - start) / 1000 / 1000);
        threadPool.shutdown();
        return end-start;
    }


    public static ConvertBeanFrom newFromBean() {
        ConvertBeanFrom convertBeanFrom = new ConvertBeanFrom();
        convertBeanFrom.setId("1");
        convertBeanFrom.setDel(0);
        convertBeanFrom.setName("from");

        ConvertBeanFromChild child1 = new ConvertBeanFromChild();
        child1.setId("2");
        child1.setName("child1");
        child1.setDel(0);

        ConvertBeanFromChild child2 = new ConvertBeanFromChild();
        child2.setId("2");
        child2.setName("child2");
        child2.setDel(1);

        ConvertBeanFromChild child3 = new ConvertBeanFromChild();
        child3.setId("2");
        child3.setName("child3");
        child3.setDel(0);

        ConvertBeanFromChild child4 = new ConvertBeanFromChild();
        child4.setId("3");
        child4.setName("child3");
        child4.setDel(0);

        List<ConvertBeanFromChild> childList = new ArrayList<ConvertBeanFromChild>();
        childList.add(child1);
        childList.add(child2);
        childList.add(child3);

        convertBeanFrom.setChildList(childList);
        convertBeanFrom.setConvertBeanFromChild(child4);

        return convertBeanFrom;
    }


}