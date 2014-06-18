package javaType;

public class BaseGeneircInteface<T> implements GeneircInteface {

//    protected R result;


    public T method2(T obj) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object method1(Object obj) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

//    private static Class getGenericClass(ParameterizedType parameterizedType, int i) {
//        Object genericClass = parameterizedType.getActualTypeArguments()[i];
//        if (genericClass instanceof ParameterizedType) { // 处理多级泛型
//            return (Class) ((ParameterizedType) genericClass).getRawType();
//        } else if (genericClass instanceof GenericArrayType) { // 处理数组泛型
//            return (Class) ((GenericArrayType) genericClass).getGenericComponentType();
//        } else if (genericClass instanceof TypeVariable) { // 处理泛型擦拭对象
//            return (Class) getClass(((TypeVariable) genericClass).getBounds()[0], 0);
//        } else {
//            return (Class) genericClass;
//        }
//    }
}
