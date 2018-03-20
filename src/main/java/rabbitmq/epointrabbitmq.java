package rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

public class epointrabbitmq {
    private static  final Logger logger= Logger.getLogger(epointrabbitmq.class);
    public static void  main(String[] args) throws NoSuchAlgorithmException, KeyManagementException, URISyntaxException, IOException, TimeoutException {
        logger.debug("测试rabbitMQ");
        for (int i=0 ;i< 100000 ;i++){
            ConnectionFactory factory = new ConnectionFactory();
            factory.setUri(args[0]);
            Connection conn = factory.newConnection();
            Channel channel = conn.createChannel();
            channel.queueDeclare("epointqueuetest", true, false, false, null);
            channel.basicPublish("","epointqueuetest", MessageProperties.PERSISTENT_TEXT_PLAIN, "epoint test".getBytes());
            channel.close();
            conn.close();
            logger.debug(i);
        }
    }
}
