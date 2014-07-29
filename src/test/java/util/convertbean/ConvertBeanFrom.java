package util.convertbean;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: liukunyang
 * Date: 14-7-29
 * Time: 上午11:21
 * To change this template use File | Settings | File Templates.
 */
public class ConvertBeanFrom {

    private String id;

    private String name;

    private int del;

    //属性名不对，拷贝不会成功
    private ConvertBeanFromChild convertBeanFromChild;

    private List<ConvertBeanFromChild> childList;


    public ConvertBeanFromChild getConvertBeanFromChild() {
        return convertBeanFromChild;
    }

    public void setConvertBeanFromChild(ConvertBeanFromChild convertBeanFromChild) {
        this.convertBeanFromChild = convertBeanFromChild;
    }

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
}
