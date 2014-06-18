package future.tryCancelFuture;

/**
 * Created with IntelliJ IDEA.
 * User: liukunyang
 * Date: 13-12-13
 * Time: 下午2:03
 * To change this template use File | Settings | File Templates.
 */
public class RichBoolean {

    private boolean value;

    public RichBoolean(boolean value) {
        this.value = value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public boolean isValue() {
        return value;
    }
}
