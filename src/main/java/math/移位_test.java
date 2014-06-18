package math;

/**
 * Created with IntelliJ IDEA.
 * User: liukunyang
 * Date: 14-1-14
 * Time: 下午7:54
 * To change this template use File | Settings | File Templates.
 */
public class 移位_test {

    public static void main(String[] args) {
        System.out.println( Integer.toBinaryString( (1 << 16) - 1 )  );
        System.out.println( 65536>>>16 );
        System.out.println(Integer.MAX_VALUE);
//        System.out.println( -1>>>1 );
}

}
