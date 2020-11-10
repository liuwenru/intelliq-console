package JMXapps;

import javax.management.*;
import javax.management.openmbean.CompositeDataSupport;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryUsage;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.*;

public class EpointTomcatApps {
    public static void main(String[] args) throws IOException, MalformedObjectNameException, AttributeNotFoundException, MBeanException, ReflectionException, InstanceNotFoundException {
        String jmxURL = "service:jmx:rmi:///jndi/rmi://192.168.188.150:7999/jmxrmi";//tomcat jmx url
        JMXServiceURL serviceURL = new JMXServiceURL(jmxURL);

        Map map = new HashMap();
        String[] credentials = new String[]{"monitorRole", "QED"};
        map.put("jmx.remote.credentials", credentials);
        JMXConnector connector = JMXConnectorFactory.connect(serviceURL, map);
        MBeanServerConnection mbsc = connector.getMBeanServerConnection();
        // Tomcat 版本 $(UUID).tomcat.version
        ObjectName runtimeObjName = new ObjectName("Catalina:type=Server");
        System.out.println("版本:" + (String) mbsc.getAttribute(runtimeObjName, "serverInfo"));
        Set names = mbsc.queryNames(new ObjectName("Catalina:type=Connector,*"), null);
        Iterator iterator = names.iterator();
        ObjectName name = null;
        while (iterator.hasNext()) {
            name = (ObjectName) iterator.next();
            String protocol = mbsc.getAttribute(name, "protocol").toString();
            String scheme = mbsc.getAttribute(name, "scheme").toString();
            // 获取tomcat端口 $(UUID).tomcat.port
            String port = mbsc.getAttribute(name, "port").toString();
            System.out.println(protocol + " : " + scheme + " : " + port);
        }

        ObjectName heapObjName = new ObjectName("java.lang:type=Memory");
        MemoryUsage heapMemoryUsage = MemoryUsage.from((CompositeDataSupport) mbsc.getAttribute(heapObjName,"HeapMemoryUsage"));

        // $(UUID).tomcat.mem.alloc
        long allocMb=heapMemoryUsage.getCommitted()/1024/1024;
        System.out.println("tomcat.mem.alloc"+ allocMb);
        // $(UUID).tomcat.mem.alloc.free
        long freeMb=(heapMemoryUsage.getCommitted()/1024/1024)-(heapMemoryUsage.getUsed()/1024/1024);
        System.out.println("tomcat.mem.alloc.free"+ freeMb);
        // $(UUID).tomcat.mem.max
        long MaxMb=heapMemoryUsage.getMax()/1024/1024;
        System.out.println("tomcat.mem.max"+ MaxMb);

        // tomcat.handled.time.max Catalina:name="http-nio-8080",type=GlobalRequestProcessor
        ObjectName handledMaxtimeObjName = new ObjectName("Catalina:name=\"http-nio-8080\",type=GlobalRequestProcessor");
        System.out.println("tomcat.handled.time.max:" +  mbsc.getAttribute(handledMaxtimeObjName, "maxTime"));


        // tomcat.handled.time.avg Catalina:type=ThreadPool,name="http-nio-8080"
        ObjectName handledAvgtimeObjName = new ObjectName("Catalina:name=\"http-nio-8080\",type=GlobalRequestProcessor");
        System.out.println("tomcat.handled.time.avg:" +  mbsc.getAttribute(handledMaxtimeObjName, "processingTime"));

        // tomcat.network.recv
        ObjectName recvObjName = new ObjectName("Catalina:name=\"http-nio-8080\",type=GlobalRequestProcessor");
        System.out.println("tomcat.network.recv:" +  mbsc.getAttribute(handledMaxtimeObjName, "bytesReceived"));

        // tomcat.network.send
        ObjectName sendObjName = new ObjectName("Catalina:name=\"http-nio-8080\",type=GlobalRequestProcessor");
        System.out.println("tomcat.network.recv:" +  mbsc.getAttribute(handledMaxtimeObjName, "bytesSent"));

        // tomcat.request.num
        ObjectName srequest_numObjName = new ObjectName("Catalina:name=\"http-nio-8080\",type=GlobalRequestProcessor");
        System.out.println("tomcat.request.num:" +  mbsc.getAttribute(handledMaxtimeObjName, "requestCount"));

        // tomcat.request.err
        ObjectName request_errObjName = new ObjectName("Catalina:name=\"http-nio-8080\",type=GlobalRequestProcessor");
        System.out.println("tomcat.request.err:" +  mbsc.getAttribute(handledMaxtimeObjName, "errorCount"));

        // tomcat.process.thread.max
        ObjectName thread_maxObjName = new ObjectName("Catalina:type=ThreadPool,name=\"http-nio-8080\"");
        System.out.println("tomcat.process.thread.max:" +  mbsc.getAttribute(thread_maxObjName, "maxThreads"));

        // tomcat.process.thread.now
        ObjectName thread_nowObjName = new ObjectName("Catalina:type=ThreadPool,name=\"http-nio-8080\"");
        System.out.println("tomcat.process.thread.now:" +  mbsc.getAttribute(thread_nowObjName, "currentThreadCount"));

        // tomcat.process.thread.busy
        ObjectName thread_busyObjName = new ObjectName("Catalina:type=ThreadPool,name=\"http-nio-8080\"");
        System.out.println("tomcat.process.thread.busy:" +  mbsc.getAttribute(thread_busyObjName, "currentThreadsBusy"));

        connector.close();
    }

    public static String formatTimeSpan(long span) {
        long minseconds = span % 1000;

        span = span / 1000;
        long seconds = span % 60;

        span = span / 60;
        long mins = span % 60;

        span = span / 60;
        long hours = span % 24;

        span = span / 24;
        long days = span;
        return (new Formatter()).format("%1$d天 %2$02d:%3$02d:%4$02d.%5$03d", days, hours, mins, seconds, minseconds)
                .toString();
    }

}
