package RabbitMQAppsUtils;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class SendApps {
    public static void main(String[] args) throws NoSuchAlgorithmException, KeyManagementException, URISyntaxException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqp://epoint:epoint@192.168.188.102:5672/%2f");
        try {
            List<Channel> channellist = new ArrayList<>();
            for (int i = 0; i<4000; i++){
                Connection connection = factory.newConnection();
                //channellist.add(connection.createChannel());
                for (int j=0;j<2;j++){
                    channellist.add(connection.createChannel());
                }
            }
            System.out.println("构造channel完毕"+channellist.size());
            Thread.sleep(500000);
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
