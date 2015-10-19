package spring;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by liukunyang on 2015/10/18.
 */
public class Main {


    public void method2(){
        TaskController taskController = new TaskController();
        AspectJProxyFactory factory = new AspectJProxyFactory();
        factory.setTarget(taskController);
        factory.setOptimize(true);
        factory.addAspect(ControllerAspect.class);
        TaskController proxy = factory.getProxy();
        proxy.index();
    }

    public static void main(String[] args) {
        try {
            long start = System.currentTimeMillis();
            System.out.println("正在加载配置文件...");

            ApplicationContext appContext = new ClassPathXmlApplicationContext("spring-config.xml");//初始化spring 容器});
            System.out.println("配置文件加载完毕,耗时：" + (System.currentTimeMillis() - start));

            TaskController taskController = (TaskController) appContext.getBean("taskController");

            taskController.index();


            Main main = new Main();
            main.method2();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
