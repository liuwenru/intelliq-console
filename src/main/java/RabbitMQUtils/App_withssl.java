package RabbitMQUtils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.GetResponse;
import org.slf4j.Logger;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeoutException;

public class App_withssl {

    public static void  main(String[] args) throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException, UnrecoverableKeyException, KeyManagementException, TimeoutException {
        char[] keyPassphrase = "p@ssw0rd".toCharArray();
        KeyStore ks = KeyStore.getInstance("PKCS12");
        ks.load(new FileInputStream("src/main/resources/epoint_rabbitmqssl/rabbitmq-epointclient.keycert.p12"), keyPassphrase);

        KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
        kmf.init(ks, keyPassphrase);

        char[] trustPassphrase = "p@ssw0rd".toCharArray();
        KeyStore tks = KeyStore.getInstance("JKS");
        tks.load(new FileInputStream("src/main/resources/epoint_rabbitmqssl/rabbitmq.store"), trustPassphrase);

        TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
        tmf.init(tks);

        SSLContext c = SSLContext.getInstance("TLSv1.2");
        c.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.188.119");
        factory.setPort(5671);
        factory.useSslProtocol(c);
        factory.setUsername("epoint");
        factory.setPassword("epoint");
        Connection conn = factory.newConnection();
        Channel channel = conn.createChannel();

        channel.queueDeclare("rabbitmq-java-test", false, true, true, null);
        channel.basicPublish("", "rabbitmq-java-test", null, "Hello, World via SSL".getBytes());

        GetResponse chResponse = channel.basicGet("rabbitmq-java-test", false);
        if (chResponse == null){
            System.out.println("No message retrieved");
        }
        else{
            byte[] body = chResponse.getBody();
            System.out.println("Recieved: " + new String(body));
        }

        channel.close();
        conn.close();

    }
}
