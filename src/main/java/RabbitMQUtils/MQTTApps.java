package RabbitMQUtils;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MQTTApps {
    public static void main(String[] args) {
        String topic = "apptest";
        String content = "Hello ith";
        int qos = 2;
        String broker = "tcp://192.168.188.109:1883";
        String clientId = "sample";
        MemoryPersistence persistence = new MemoryPersistence();
        try {
            while(true){
                MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
                MqttConnectOptions connOpts = new MqttConnectOptions();
                connOpts.setUserName("epoint");
                connOpts.setPassword("epoint".toCharArray());
                connOpts.setCleanSession(true);
                System.out.println("Connecting to broker");
                sampleClient.connect(connOpts);
                System.out.println("connected");
                System.out.println("Publishing meessage: " + content);
                MqttMessage message = new MqttMessage(content.getBytes());
                message.setQos(qos);
                sampleClient.publish(topic, message);
                System.out.println("Message published");
                sampleClient.disconnect();
                System.out.println("Disconnected");
                Thread.sleep(2*1000);
            }
            //System.exit(0);
        } catch (MqttException | InterruptedException e){
            System.out.println("reason " + e);
            System.out.println("msg " + e.getMessage());
            System.out.println("loc " + e.getLocalizedMessage());
            System.out.println("cause " + e.getCause());
            System.out.println("exxcep " + e);
        }

    }
}
