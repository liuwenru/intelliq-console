import org.apache.log4j.Logger;
import java.io.*;


public class Apps {
    public static Logger logger = Logger.getLogger(Apps.class);

    public static void main(String[] args) throws IOException {
        /*
            阿里云的Linux服务器进程关键字         aliyun-service
            阿里云的Windows服务器进程关键字         aliyunservice
            华为云的Linux服务器进程关键字         uvp-monitor
            华为云的Windows服务器进程关键字         uvpmonitor
        */
        String Aliyun="aliyun-service";
        String Huaweiyun="uvp-monitor";
        System.out.println("is Aliyun ?  "+getIaasInfo(Aliyun));
        System.out.println("is Huaweiyun ? "+ getIaasInfo(Huaweiyun));

    }
    public static boolean getIaasInfo(String keyworlds) throws IOException {
        //获取IaaS层平台信息方法
        String CMD="";
        String osName = System.getProperty("os.name");
        System.out.println("The OS Version is "+osName);
        if (osName.toLowerCase().indexOf("linux")<0){
            //操作系统是Windows
            CMD="tasklist";
        }else {
            //操作系统是Linux， Max OS不考虑
            CMD="ps -ef";
        }
        Process process = Runtime.getRuntime().exec(CMD);
        BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = "";
        while ((line = input.readLine()) != null) {
            if (line.toLowerCase().indexOf(keyworlds)>=0){
                System.out.println(line);
                return true;
            }
        }
        input.close();
        return false;
    }
}
