package util.convertbean;

/**
 * Created with IntelliJ IDEA.
 * User: liukunyang
 * Date: 14-7-18
 * Time: 上午8:53
 * To change this template use File | Settings | File Templates.
 */
public class Chunk<T extends  Iterator<T>> extends Hash{

    private T[] chunkArray;

    protected Chunk(int size){
        chunkArray = (T[]) new Iterator[size];
    }


    protected Object  lookUpChunk(Object positionKey){
        int pos = position(positionKey,chunkArray.length);
        T t = chunkArray[pos];
        if( t == null ){
            return t;
        }

        boolean found = false;
        while( ! (found = t.equal(positionKey) ) && t.next() != null ){
            t = t.next();
        }

        if( found ){
            return t;
        }

        return null;

    }


    protected void  addChunk(Object positionKey,ChunkSupport<T> chunkSupport,ChunkSupportParam chunkSupportParam){
        int pos = position(positionKey,chunkArray.length);
        if( chunkArray[pos] == null ){
            synchronized (this){
                if( chunkArray[pos] == null ){
                    chunkArray[pos] = chunkSupport.createChunk(chunkSupportParam);
                    return  ;
                }
            }
        }

        T head = chunkArray[pos];
        T t = chunkArray[pos];
        T addChunk = chunkSupport.createChunk(chunkSupportParam);
        boolean found = false;
        while( ! (found = t.equal(addChunk) ) && t.next() != null ){
            t = t.next();
        }

        if( found ){
            //找到了调用update
            chunkSupport.update(t,chunkSupportParam);
        }else{
            t = head;
            synchronized (t){
                while( ! (found = t.equal(addChunk) ) && t.next() != null ){
                    t = t.next();
                }

                if( !found ){
                    t.setNext( chunkSupport.createChunk(chunkSupportParam) );
                    return;
                }

                chunkSupport.update(t,chunkSupportParam);
                return;
            }

        }

    }

}
