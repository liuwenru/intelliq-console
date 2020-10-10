package RabbitMQApps;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class Apps {
    private static String QUEUE_NAME="Queue1";
    private final static String EXCHANGE_NAME="amq.fanout";
    public static void main(String[] args) throws NoSuchAlgorithmException, KeyManagementException, URISyntaxException, IOException, TimeoutException {
        // arg0  amqp://epoint:epoint@192.168.188.150
        // arg1  QUEUE_NAME
        new String();
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri(args[0]);
        QUEUE_NAME=args[1];
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        Map<String, Object> agruments = new HashMap<String, Object>();
        //agruments.put("x-dead-letter-exchange", "dead_letter_exchange");
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        for(int i =0;i<10000;i++){
            String message = "Hello World!";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        }
        channel.close();
        connection.close();
    }
}
