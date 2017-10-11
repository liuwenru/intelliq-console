import org.apache.log4j.Logger;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.GetResponse;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.*;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.security.*;
import java.security.cert.CertificateException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

public class Apps {
    public static Logger logger=Logger.getLogger(Apps.class);
    public static void  main(String[] args) throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException, UnrecoverableKeyException, KeyManagementException, TimeoutException, ParseException {

//        String properties = System.getProperty(args[0]);
//        System.out.println("----------get properties"+args[0]+"------------");
//        System.out.println(properties);
//        String env = System.getenv(args[0]);
//        System.out.println("----------------------");
//        System.out.println(env);



        //String docker1 = System.getProperty("CURRENT_ALPINE_GLIBC_BASE_IMAGE_VER").toLowerCase();
        //String docker2 = System.getenv("CURRENT_ALPINE_GLIBC_BASE_IMAGE_VER").toLowerCase();
        //System.out.println(docker1);
        //System.out.println(docker2);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String hostip=java.net.InetAddress.getByName("wwww.baidu.com").getHostAddress();
        System.out.println(hostip);




        //        String data = "access_token=2315e98381567f4466e5a83077e68ff2";
//        URL url = new URL("http://testhosts:18088/epoint-web/rest/organization/getAllUser");
//        URLConnection conn = url.openConnection();
//        conn.setDoOutput(true);
//        OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
//
//        //write parameters
//        writer.write(data);
//        writer.flush();
//
//        // Get the response
//        StringBuffer answer = new StringBuffer();
//        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//        String line;
//        while ((line = reader.readLine()) != null) {
//            answer.append(line);
//        }
//        writer.close();
//        reader.close();
//
//        //Output the response
//        System.out.println(new String(answer.toString().getBytes(),"utf-8"));
//        System.out.println("========================");
//        System.out.println(answer.toString());

    }
}
