package juc.lock.condition;

/**
 * Created with IntelliJ IDEA.
 * User: liukunyang
 * Date: 14-1-27
 * Time: 下午1:34
 * To change this template use File | Settings | File Templates.
 */
 abstract  class ConditionTest {

    public void  enq(){
        System.out.println("this is enq");
    }


    public class Condition{

        public void signal(){
            System.out.println("this is signal");
            enq();
        }

    }

}
