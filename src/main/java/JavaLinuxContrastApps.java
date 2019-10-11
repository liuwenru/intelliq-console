import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class JavaLinuxContrastApps {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println(System.getProperty("java.endorsed.dirs"));
        Thread.sleep(20*1000);
        System.out.println("-----------------");
        String HOST="mail.epoint.com.cn";
        int PORT=6080;
        Socket[] sockets=new Socket[20];
        for (int i=0;i<20;i++){
            sockets[i]=new Socket(HOST,PORT);
            OutputStream outputStreams=sockets[i].getOutputStream();
            outputStreams.write(Integer.parseInt("1111"));
        }
        Thread.sleep(100*1000);
    }
}
