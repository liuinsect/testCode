package asm;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by liukunyang on 15-1-12.
 */
public class AOPTest {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException, MalformedURLException {
        URL[] urls = new URL[1];
        urls[0] = new URL("file:/home/liukunyang/develop/workspace/github/testCode/");
        URLClassLoader classLoader = new URLClassLoader(urls);
        Class<?> clazz = classLoader.loadClass("asmTest");

        Object o = clazz.newInstance();
        Method[] methods = clazz.getMethods();
        for(Method method : methods){
            method.invoke(o,null);
        }
    }


}
