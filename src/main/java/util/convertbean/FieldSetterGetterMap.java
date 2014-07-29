package util.convertbean;

import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 * User: liukunyang
 * Date: 14-7-18
 * Time: 上午10:40
 * To change this template use File | Settings | File Templates.
 */
public class FieldSetterGetterMap implements Iterator<FieldSetterGetterMap>{

    private String fieldName;

    private Method setter;

    private Method getter;

    private FieldSetterGetterMap next = null;

    @Override
    public FieldSetterGetterMap next() {
        return next;
    }


    @Override
    public boolean equal(Object o) {
        if( o instanceof  FieldSetterGetterMap ){
            fieldName.equals( ( (FieldSetterGetterMap)o ).getFieldName() );
        }

        return fieldName.equals(o);
    }

    @Override
    public void setNext(FieldSetterGetterMap fieldSetterGetterMap) {
        this.next=fieldSetterGetterMap;
    }


    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Method getGetter() {
        return getter;
    }

    public void setGetter(Method getter) {
        this.getter = getter;
    }

    public Method getSetter() {
        return setter;
    }

    public void setSetter(Method setter) {
        this.setter = setter;
    }
}
