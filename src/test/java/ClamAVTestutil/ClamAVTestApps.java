package ClamAVTestutil;

import fi.solita.clamav.ClamAVClient;
import org.junit.Test;

import java.io.FileInputStream;


public class ClamAVTestApps {
    @Test
    public static void Testclamav() throws Exception {
        ClamAVClient cl = new ClamAVClient("192.168.50.72", 3310);

        FileInputStream fileInputStream=new FileInputStream("log4j.xml");
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




