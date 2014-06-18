package reflect;

import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: liukunyang
 * Date: 14-4-12
 * Time: 上午9:38
 * To change this template use File | Settings | File Templates.
 */
public class ParameterTypeMain {

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();

        ParameterTypeMain main = new ParameterTypeMain();

        TypeVariable<? extends Class<? extends List>>[] typeVariable =  list.getClass().getTypeParameters();

        TypeVariable<? extends Class<? extends ParameterTypeMain>>[] typeVariableArray = main.getClass().getTypeParameters();

          for( TypeVariable typeVariable1 : typeVariableArray ){
              System.out.println(typeVariable1.getName());
          }





    }

}
