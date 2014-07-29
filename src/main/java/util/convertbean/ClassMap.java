package util.convertbean;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 * User: liukunyang
 * Date: 14-7-18
 * Time: 上午10:19
 * To change this template use File | Settings | File Templates.
 */
public class ClassMap extends Chunk<ClassFieldMap> {

    private ClassMapChunkSupport classMapChunkSupport = new ClassMapChunkSupport();

    public ClassMap(int size) {
        super(size);
    }

    public void addChunk(Class clazz, Field field, Method method, boolean isSetter) {
        final String className = clazz.getName();
        ClassMapSupportParam param = new ClassMapSupportParam();
        param.clazz=clazz;
        param.className=className;
        param.field=field;
        param.method=method;
        param.isSetter=isSetter;

        super.addChunk(clazz.getName(),classMapChunkSupport,param);
    }
    public void addChunk(Class clazz) {
        addChunk(clazz,null,null,false);
    }

    public Object lookUpChunk(Class clazz,Field field) {
        ClassFieldMap classFieldMap = (ClassFieldMap) super.lookUpChunk(clazz.getName());
        if( classFieldMap!= null ){
            return classFieldMap.lookUpChunk(field.getName());
        }
        return null;
    }

    public Class lookClass(String className) {
        ClassFieldMap classFieldMap = (ClassFieldMap) super.lookUpChunk(className);
        if( classFieldMap!= null ){
            return classFieldMap.getClazz();
        }
        return null;
    }



    static class ClassMapChunkSupport implements ChunkSupport<ClassFieldMap> {

        @Override
        public ClassFieldMap createChunk(ChunkSupportParam chunkSupportParam) {
            ClassMapSupportParam classMapSupportParam = (ClassMapSupportParam) chunkSupportParam;
            ClassFieldMap classFieldMap = new ClassFieldMap(10);
            classFieldMap.setClazz(classMapSupportParam.clazz);
            classFieldMap.setClassName(classMapSupportParam.className);

            if( classMapSupportParam.field != null ){
                classFieldMap.addFieldMethodMap(classMapSupportParam.field, classMapSupportParam.method, classMapSupportParam.isSetter);
            }

            return classFieldMap;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public int update(ClassFieldMap classFieldMap,ChunkSupportParam chunkSupportParam) {
            ClassMapSupportParam classMapSupportParam = (ClassMapSupportParam) chunkSupportParam;
            classFieldMap.setClazz(classMapSupportParam.clazz);
            classFieldMap.setClassName(classMapSupportParam.className);

            if( classMapSupportParam.field != null ){
                classFieldMap.addFieldMethodMap(classMapSupportParam.field,classMapSupportParam.method,classMapSupportParam.isSetter);
            }
            return 0;  //To change body of implemented methods use File | Settings | File Templates.
        }

    }

    static class ClassMapSupportParam implements ChunkSupportParam{

        private Class clazz;
        private String className;
        private Field field;
        private Method method;
        private boolean isSetter;

    }
}
