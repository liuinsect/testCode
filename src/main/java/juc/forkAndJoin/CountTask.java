package juc.forkAndJoin;

import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 * User: liukunyang
 * Date: 14-4-30
 * Time: 下午4:21
 * To change this template use File | Settings | File Templates.
 */
public class CountTask   extends RecursiveTask<Integer> {


    private static final int THRESHOLD = 2;

    private int start;

    private int end;


    public CountTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        boolean canCompute = (end - start)<=THRESHOLD;

        if(canCompute){
            for (int i = start; i <= end; i++) {
                sum +=i;
            }
        }else{
            int middle = (start + end)/2;
            CountTask leftTask = new CountTask(start, middle);
            CountTask rightTask = new CountTask( middle+1 ,end);
            leftTask.fork();
            rightTask.fork();

            int leftResult = leftTask.join();
            int rightResult = rightTask.join();

            sum = leftResult + rightResult;

        }

        return sum;

    }

    public static void main(String[] args) {


        ForkJoinPool forkPool = new ForkJoinPool();
        CountTask task = new CountTask(1, 4);

        Future<Integer> result = forkPool.submit(task);

        try {
            System.out.println(result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ExecutionException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


    }


}
