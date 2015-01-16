package agent;

import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.File;
import java.io.IOException;
import java.lang.management.*;
import java.net.MalformedURLException;

import com.sun.management.HotSpotDiagnosticMXBean;
import com.sun.tools.attach.*;

import java.util.Properties;

/**
 * Created by liukunyang on 15-1-16.
 */
public class AttachMain {

    public static void main(String[] args) throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException {
        // 被监控jvm的pid(windows上可以通过任务管理器查看)
        String targetVmPid = "6145";
        // Attach到被监控的JVM进程上
        VirtualMachine virtualmachine = VirtualMachine.attach(targetVmPid);

        // 让JVM加载jmx Agent
        String javaHome = virtualmachine.getSystemProperties().getProperty("java.home");
        String jmxAgent = javaHome + File.separator + "lib" + File.separator + "management-agent.jar";
        virtualmachine.loadAgent(jmxAgent, "com.sun.management.jmxremote");

        // 获得连接地址
        Properties properties = virtualmachine.getAgentProperties();
        String address = (String) properties.get("com.sun.management.jmxremote.localConnectorAddress");

        // Detach
        virtualmachine.detach();
//        // 通过jxm address来获取RuntimeMXBean对象，从而得到虚拟机运行时相关信息
        JMXServiceURL url = new JMXServiceURL(address);
        JMXConnector connector = JMXConnectorFactory.connect(url);


        RuntimeMXBean rmxb = ManagementFactory.newPlatformMXBeanProxy(connector.getMBeanServerConnection(), "java.lang:type=Runtime",
                RuntimeMXBean.class);

        // 得到目标虚拟机占用cpu时间
        System.out.println(rmxb.getUptime());

        MemoryMXBean memoryMXBean = ManagementFactory.newPlatformMXBeanProxy(connector.getMBeanServerConnection(), "java.lang:type=Memory",
                MemoryMXBean.class);

        System.out.println(memoryMXBean.getHeapMemoryUsage());

        HotSpotDiagnosticMXBean hotSpotDiagnostic = ManagementFactory.newPlatformMXBeanProxy(connector.getMBeanServerConnection(), "com.sun.management:type=HotSpotDiagnostic",
                HotSpotDiagnosticMXBean.class);

        hotSpotDiagnostic.dumpHeap("/home/liukunyang/dumpFile",true);


        ThreadMXBean threadMXBean = ManagementFactory.newPlatformMXBeanProxy(connector.getMBeanServerConnection(), "java.lang:type=Threading",
                ThreadMXBean.class);

        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(true, true);

        for (int i = 0; i < threadInfos.length; i++) {
            if (threadInfos[i].getThreadName().startsWith("BufferThreadFactory")) {
                System.out.println(threadInfos[i].getStackTrace());
            }

        }


    }

}
