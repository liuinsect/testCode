package juc.lock.condition;

/**
 * Created with IntelliJ IDEA.
 * User: liukunyang
 * Date: 14-1-27
 * Time: 下午1:44
 * To change this template use File | Settings | File Templates.
 */
public class Main extends ConditionTest{

    public Condition newCondition(){
        return new Condition();
    }


    public static void main(String[] args) {
        Main main = new Main();

        Condition condition = main.newCondition();
        condition.signal();

    }

}
