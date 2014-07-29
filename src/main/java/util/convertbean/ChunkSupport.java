package util.convertbean;

/**
 * Created with IntelliJ IDEA.
 * User: liukunyang
 * Date: 14-7-18
 * Time: 上午8:56
 * To change this template use File | Settings | File Templates.
 */
public interface ChunkSupport<T> {

    public T createChunk(ChunkSupportParam chunkSupportParam);

    public int update(T t,ChunkSupportParam chunkSupportParam);

}
