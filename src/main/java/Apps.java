import org.apache.log4j.Logger;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.GetResponse;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeoutException;

public class Apps {
    public static Logger logger=Logger.getLogger(Apps.class);
    public static void  main(String[] args) throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException, UnrecoverableKeyException, KeyManagementException, TimeoutException {
        char[] keyPassphrase = "p@ssw0rd".toCharArray();
        KeyStore ks = KeyStore.getInstance("PKCS12");
        ks.load(new FileInputStream("/Users/ijarvis/IdeaProjects/intelliq-console/src/main/resources/rabbitmqssl/rabbitmq-epointclient.keycert.p12"), keyPassphrase);

        KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
        kmf.init(ks, keyPassphrase);

        char[] trustPassphrase = "p@ssw0rd".toCharArray();
        KeyStore tks = KeyStore.getInstance("JKS");
        tks.load(new FileInputStream("/Users/ijarvis/IdeaProjects/intelliq-console/src/main/resources/rabbitmqssl/rabbitmqstore"), trustPassphrase);

        TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
        tmf.init(tks);

        SSLContext c = SSLContext.getInstance("TLSv1.1");
        c.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.206.72");
        factory.setPort(5672);
        factory.useSslProtocol(c);
        factory.setUsername("epoint");
        factory.setPassword("epoint");
        Connection conn = factory.newConnection();
        Channel channel = conn.createChannel();

        channel.queueDeclare("rabbitmq-java-test", false, true, true, null);
        channel.basicPublish("", "rabbitmq-java-test", null, "Hello, World via SSL".getBytes());

        GetResponse chResponse = channel.basicGet("rabbitmq-java-test", false);
        if (chResponse == null)
        {
            System.out.println("No message retrieved");
        }
        else
        {
            byte[] body = chResponse.getBody();
            System.out.println("Recieved: " + new String(body));
        }

        channel.close();
        conn.close();

    }
}
