package reflect;

/**
 * Created with IntelliJ IDEA.
 * User: liukunyang
 * Date: 14-4-12
 * Time: 上午10:36
 * To change this template use File | Settings | File Templates.
 */
public class GenerisFooMain {

    public static void main(String[] args) {
        GenericsFoo<Integer>  gf; gf = new GenericsFoo<Integer>();
        gf.setX(new Integer(1));

//        GenericsFoo<?> gf2 = new GenericsFoo<Object>();
//        gf2.setX( new Object() );                 // 报错！！！
//        String str = gf2.getX();   // 报错！！！
//        gf2.setX(gf2.getX());             // 报错！！！
    }


}
