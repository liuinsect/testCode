package util.convertbean;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.Iterator;

/**
 * 对象拷贝工具类
 * TODO 对多泛型变量的拷贝还支持的不好
 * Created with IntelliJ IDEA.
 * User: liukunyang
 * Date: 14-7-7
 * Time: 上午11:33
 * To change this template use File | Settings | File Templates.
 */
public class ConvertFactory {

//    private static Logger log = Logger.getLogger(ConvertFactory.class);

    private static final String SET_METHOD_FIX = "set";
    private static final String GET_METHOD_FIX = "get";

    private static final String IS_METHOD_FIX = "is";

    private static ClassMap classMap = new ClassMap(30);

    /**
     * 从from对象中将属性拷贝到 toClass的对象中
     * 以toClass的的属性值为准，只拷贝同名属性
     * @param from
     * @param toClass
     * @param <T>
     * @return
     */
    public static <T> T from(Object from,Class<T> toClass) {
        T toObject = newInstance(toClass);
        if( toObject == null ){
            return null;
        }
        Method setMethod = null;
        Object fromValue = null;
        Field[] toFields = toClass.getDeclaredFields();
        for(Field field : toFields){
            //get from对象中的值
            fromValue = getFromFieldValue(from,field);
            if( fromValue == null ){
                continue;
            }

            fromValue = convertFieldValue(field,from,fromValue);
            if(fromValue == null){
                continue;
            }

            setMethod = getSetMethod(toClass, field);
            if( setMethod == null ){
                continue;
            }
            try {
                setMethod.invoke(toObject,fromValue);
            } catch (Exception e) {
//                log.debug(e);
            }
        }
         return toObject;
    }

    /**
     * 实例化一个class
     * @param clazz
     * @param <T>
     * @return
     */
    private static <T> T newInstance(Class<T> clazz) {
        try {
            T toObejct = clazz.newInstance();
            return toObejct;
        } catch (InstantiationException e) {

        } catch (IllegalAccessException e) {

        }
        return null;
    }

    /**
     * 获取set方法
     * @param clazz
     * @param field
     * @return
     */
    private static Method getSetMethod(Class clazz, Field field) {
        FieldSetterGetterMap fieldSetterGetterMap = (FieldSetterGetterMap) classMap.lookUpChunk(clazz, field);
        if( fieldSetterGetterMap != null && fieldSetterGetterMap.getSetter()!= null ){
            return fieldSetterGetterMap.getSetter();
        }

        String fieldName = field.getName();
        if( fieldName.startsWith(IS_METHOD_FIX) ){
            fieldName = fieldName.substring(2);
        }

        String setMethodName = SET_METHOD_FIX + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        try {
            Method setMethod = clazz.getMethod(setMethodName, field.getType());
            classMap.addChunk(clazz, field, setMethod, true);
            return setMethod;
        } catch (NoSuchMethodException e) {

        }
        return null;
    }

    /**
     * 从from对象中获取指定field的属性值
     * @param from
     * @param field
     * @return
     */
    private static Object getFromFieldValue(Object from,Field field){
        try {
            Method getMethod = getGetMethod(from,field);
            if( getMethod != null ){
                Object fieldValue = getMethod.invoke(from, null);
                return fieldValue;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private static Method getGetMethod(Object from,Field field){
        FieldSetterGetterMap fieldSetterGetterMap = (FieldSetterGetterMap) classMap.lookUpChunk(from.getClass(),field);
//        FieldSetterGetterMap fieldSetterGetterMap = classMap.lookUpSetterGetterMap(from.getClass(),field);
        if( fieldSetterGetterMap != null && fieldSetterGetterMap.getGetter() != null){
            return fieldSetterGetterMap.getGetter();
        }

        String fieldName = field.getName();
        String getMethodName = null;
        if( isBoolean(field.getType()) ){
            if( fieldName.startsWith(IS_METHOD_FIX) ){
                getMethodName = fieldName;
            }else{
                getMethodName = IS_METHOD_FIX + fieldName.substring(0, 1).toUpperCase() +fieldName.substring(1);
            }
        }else{
            getMethodName = GET_METHOD_FIX + fieldName.substring(0, 1).toUpperCase() +fieldName.substring(1);
        }
        Method getMethod = null;
        try {
            getMethod = from.getClass().getMethod(getMethodName, null);
//            classMap.addGetMethod(from.getClass(),field,getMethod);
            classMap.addChunk(from.getClass(),field,getMethod,false);

        } catch (NoSuchMethodException e) {

        }
         return getMethod;
    }

    /**
     * 检查源字段的类型和目标对象的是否一致，
     * 如果不一致，则需要转换成目标字段的类型
     * @param toField
     * @param from
     * @param fromValue
     * @return
     */
    private static Object convertFieldValue(Field toField, Object from,Object fromValue){
        Field fromField = null;
        Class fromFieldClass = null;
        Class toFieldClass = null;

        fromField = lookUpField(from.getClass(),toField.getName());
        if( fromField == null ){
            return null;
        }

        //泛型属性，单独检查
        if( isGeneric(fromField.getGenericType()) && isGeneric(toField.getGenericType()) ){
            return convertGenericFieldValue(fromField,toField,fromValue);
        }

        fromFieldClass = fromField.getType();
        toFieldClass = toField.getType();

        if(!isAssignableFrom(fromFieldClass,toFieldClass)){
            return from(fromValue,toFieldClass);
        }
        //两者是同一类型，直接返回使用即可
        return fromValue;

    }

    /**
     * clazz1 是否是clazz2的父类
     * 基本类型因为JDK可以手动封包，解包，所有手动判断为true
     * @param clazz1
     * @param clazz2
     * @return
     */
    private static boolean isAssignableFrom(Class clazz1,Class clazz2){
        if( clazz1.isAssignableFrom(clazz2)){
            return true;
        }

        //基本类型无法通过 继承树判断，穷举后，通过JDK 自动封包，解包实现
        if( (isIntOrInteger(clazz1))&&( isIntOrInteger(clazz2) )  ) {
            return true;
        }

        if( (isLong(clazz1))&&( isLong(clazz2) )  ) {
            return true;
        }

        if( (isDouble(clazz1))&&( isDouble(clazz2) )  ) {
            return true;
        }

        if( (isBoolean(clazz1))&&( isBoolean(clazz2) )  ) {
            return true;
        }

        return false;
    }

    /**
     * 是否是泛型理性
     * @param type
     * @return
     */
    private static boolean isGeneric(Type type){
        if( type == null ){
            return false;
        }
        return type instanceof ParameterizedType;
    }

    /**
     * List<xxx> --> list<xxx> 的转化
     * @param fromValue
     * @param toClass
     * @return
     */
    public static List convertListField(List fromValue ,Class toClass){
        int length = fromValue.size();

        List toList = new ArrayList(length);
        Object tmp = null;
        for (int i = 0; i < length; i++) {
            tmp = from(fromValue.get(i),toClass);
            toList.add(tmp);
        }

        return toList;
    }

    /**
     * Map<xx,xx> --> Map<xx,xx>
     * @param fromValue
     * @param genericClassArray
     * @return
     */
    public static Map convertMapField( Map fromValue,Class[] genericClassArray ){
        Map toMap = new HashMap();
        Iterator keyIt = fromValue.keySet().iterator();
        Object key = null;
        Object value = null;
        boolean keyEqual = false;
        boolean valueEqual = false;
        while( keyIt.hasNext() ){
            key = keyIt.next();
            value = fromValue.get(key);
            //简单起见，之比较两次泛型
            if( key!=null && value!=null
                    && (keyEqual = key.getClass().equals(genericClassArray[0]))
                    && (valueEqual = value.getClass().equals(genericClassArray[1]) )
                    ){
                return fromValue;
            }

            if( !keyEqual ){
                key = from(key,genericClassArray[0]);
            }
            if( !valueEqual ){
                value = from(value,genericClassArray[1]);
            }

            toMap.put(key,value);
        }
        return toMap;
    }

    /**
     * 从ParameterizedType中提取出class 对象
     * @param type
     * @return
     */
    private static Class extractClassFromType(Type type){
        String name = type.toString().substring(6);
        Class clazz = classMap.lookClass(name);
        if( clazz != null ){
            return clazz;
        }


        ClassLoader classLoader = ConvertFactory.class.getClassLoader();
        try {
            clazz = classLoader.loadClass(name);
            classMap.addChunk(clazz);
            return clazz;
        } catch (ClassNotFoundException e) {

        }
        return null;
    }

    /**
     * 将fromField的值尝试转换成toField的要求的类型
     * 可能递归调用from方法
     * @param fromField
     * @param toField
     * @param fromValue
     * @return
     */
    private static Object convertGenericFieldValue(Field fromField,Field toField, Object fromValue){
        ParameterizedType fromFieldType = (ParameterizedType) fromField.getGenericType();
        ParameterizedType toFieldType = (ParameterizedType) toField.getGenericType();

        if( fromFieldType.getActualTypeArguments().length != toFieldType.getActualTypeArguments().length ){
            return null;
        }

        if( fromFieldType.getRawType() != toFieldType.getRawType() ){
            return null;
        }

        //TODO 公共方法提取
        //泛型情况下只考虑了list的情况
        if( isAssignableFrom(fromField.getType(),List.class) && isAssignableFrom(toField.getType(),List.class) ){
            boolean isGenericEqual = true;
            Type[] fromFieldTypeArguments = fromFieldType.getActualTypeArguments();
            Type[] toFieldTypeArguments = toFieldType.getActualTypeArguments();
            for (int i = 0; i < fromFieldTypeArguments.length; i++) {
                if( !fromFieldTypeArguments[i].equals( toFieldTypeArguments[i] ) ){
                    isGenericEqual = false;
                }
            }
            //泛型类型有不同才去提取toField里面的类型，并转化
            //只支持1个泛型参数
            if( !isGenericEqual ){
                return convertListField((List)fromValue,extractClassFromType(toFieldType.getActualTypeArguments()[0]));
            }

            return fromValue;
        }

        //泛型情况下只考虑了list的情况
        if( isAssignableFrom(fromField.getType(),Map.class) && isAssignableFrom(toField.getType(),Map.class) ){
            Type[] toFieldTypeArguments = toFieldType.getActualTypeArguments();
            Class[] toFieldGenericClassArray = new Class[toFieldTypeArguments.length];

            for (int i = 0; i < toFieldGenericClassArray.length; i++) {
                toFieldGenericClassArray[i] = extractClassFromType(toFieldTypeArguments[i]);
            }
            //泛型类型有不同才去提取toField里面的类型，并转化

            return convertMapField((Map)fromValue,toFieldGenericClassArray);
        }



        return null;
    }

    /**
     * 递归找属性值，性能非常差
     * @param clazz
     * @param fieldName
     * @return
     */
    private static Field lookUpField(Class clazz,String fieldName){
        Field field = null;
        try {
            field = clazz.getDeclaredField(fieldName);
            return field;
        } catch (NoSuchFieldException e) {
            if( clazz.getSuperclass() != null ){
                return lookUpField(clazz.getSuperclass(),fieldName);
            }
        }

        return null;
    }

    private static boolean isIntOrInteger(Class clazz){
        return clazz == int.class || clazz == Integer.class;
    }


    private static boolean isLong(Class clazz){
        return clazz == long.class || clazz == Long.class;
    }

    private static boolean isDouble(Class clazz){
        return clazz == double.class || clazz == Double.class;
    }

    private static boolean isBoolean(Class clazz){
        return clazz == boolean.class || clazz == Boolean.class;
    }


}
