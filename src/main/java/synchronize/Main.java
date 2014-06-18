package synchronize;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IntelliJ IDEA.
 * User: liukunyang
 * Date: 13-12-25
 * Time: 上午9:49
 * To change this template use File | Settings | File Templates.
 */
public class Main {


    public static void test(){
        try{
            System.out.println("asdfasdfasd");
            if(true){
                Integer i = new Integer("11$#%");
            }
        }  finally {
            System.out.println("this is final");
        }
    }

    public static void main(String[] args) throws IOException {
        Main.test();
    }

}
