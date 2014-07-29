package util.convertbean;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 * User: liukunyang
 * Date: 14-7-18
 * Time: 上午10:20
 * To change this template use File | Settings | File Templates.
 */
public class ClassFieldMap extends Chunk<FieldSetterGetterMap>  implements Iterator<ClassFieldMap>{

    private ClassFieldMap next;
    private String className;
    private Class clazz;

    private ClassFieldMapChunkSupport classFieldMapChunkSupport = new ClassFieldMapChunkSupport();

    protected ClassFieldMap(int size) {
        super(size);
    }

    public void addFieldMethodMap(Field field,Method method,boolean isSetter){
        ClassFieldMapSupportParam classFieldMapSupportParam = new ClassFieldMapSupportParam();
        classFieldMapSupportParam.field=field;
        classFieldMapSupportParam.method=method;
        classFieldMapSupportParam.isSetter=isSetter;

        super.addChunk(field.getName(),classFieldMapChunkSupport,classFieldMapSupportParam);

    }

    @Override
    public ClassFieldMap next() {
        return next;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean equal(Object o) {
        if( o instanceof  ClassFieldMap ){
            return className.equals(((ClassFieldMap)(ClassFieldMap) o).getClassName());
        }

        return className.equals(o);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setNext(ClassFieldMap classFieldMap) {
        this.next=classFieldMap;
    }


    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    static class ClassFieldMapChunkSupport implements ChunkSupport<FieldSetterGetterMap>{

        @Override
        public FieldSetterGetterMap createChunk(ChunkSupportParam chunkSupportParam) {
            ClassFieldMapSupportParam classFieldMapSupportParam = (ClassFieldMapSupportParam)chunkSupportParam;
            FieldSetterGetterMap fieldSetterGetterMap = new FieldSetterGetterMap();
            fieldSetterGetterMap.setFieldName(classFieldMapSupportParam.field.getName());
            if( classFieldMapSupportParam.isSetter ){
                fieldSetterGetterMap.setSetter(classFieldMapSupportParam.method);
            }else{
                fieldSetterGetterMap.setGetter(classFieldMapSupportParam.method);
            }
            return fieldSetterGetterMap;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public int update(FieldSetterGetterMap fieldSetterGetterMap, ChunkSupportParam chunkSupportParam) {
            ClassFieldMapSupportParam classFieldMapSupportParam = (ClassFieldMapSupportParam)chunkSupportParam;
            if( classFieldMapSupportParam.isSetter ){
                fieldSetterGetterMap.setSetter(classFieldMapSupportParam.method);
            }else{
                fieldSetterGetterMap.setGetter(classFieldMapSupportParam.method);
            }
            return 0;  //To change body of implemented methods use File | Settings | File Templates.
        }
    }

    static class ClassFieldMapSupportParam implements ChunkSupportParam{

        private Field field;
        private Method method;
        private boolean isSetter;

    }

}
