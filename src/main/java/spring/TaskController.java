package spring;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


/**
 * Created with IntelliJ IDEA.
 * User: hesonglin
 * Date: 15-1-16
 * Time: 上午9:16
 */
public class TaskController  {

    private static final String VM_PREFIX = "task/";

    public ModelAndView index() {
        System.out.println("index");
        return new ModelAndView();
    }


}
