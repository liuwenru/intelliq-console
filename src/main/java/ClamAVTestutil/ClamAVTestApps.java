package ClamAVTestutil;

import fi.solita.clamav.ClamAVClient;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ClamAVTestApps {
    public static void main(String[] args) throws Exception {
        ClamAVClient cl = new ClamAVClient("192.168.202.235", 3310);
        while (true){
            FileInputStream fileInputStream=new FileInputStream("/home/parallels/workspace/Javaworkspace/intelliq-console/src/main/resources/EICAR");
            byte[] reply;
            try {
                reply = cl.scan(fileInputStream);
            } catch (Exception e) {
                throw new RuntimeException("Could not scan the input", e);
            }
            if (!ClamAVClient.isCleanReply(reply)) {
                throw new Exception("aaargh. Something was found");
            }
        }

    }
}
