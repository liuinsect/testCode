package util.convertbean;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: liukunyang
 * Date: 14-7-29
 * Time: 上午11:22
 * To change this template use File | Settings | File Templates.
 */
public class ConvertBeanTo {

    private String id;

    private String name;

    private int del;

    private ConvertBeanToChild convertBeanToChild;

    private List<ConvertBeanFromChild> childList;


    public int getDel() {
        return del;
    }

    public void setDel(int del) {
        this.del = del;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ConvertBeanFromChild> getChildList() {
        return childList;
    }

    public void setChildList(List<ConvertBeanFromChild> childList) {
        this.childList = childList;
    }

    public ConvertBeanToChild getConvertBeanToChild() {
        return convertBeanToChild;
    }

    public void setConvertBeanToChild(ConvertBeanToChild convertBeanToChild) {
        this.convertBeanToChild = convertBeanToChild;
    }
}
