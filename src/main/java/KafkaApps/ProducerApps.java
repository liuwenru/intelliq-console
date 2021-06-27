package KafkaApps;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class ProducerApps {
    private static String TOPIC = "ijarvis-topic-test";


    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.188.150:9092");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(props);
        int messageNo = 1;
        final int COUNT = 10;

        while (messageNo < COUNT) {
            String key = String.valueOf(messageNo);
            String data = String.format("hello KafkaProducer message %s", key);

            try {
                kafkaProducer.send(new ProducerRecord<String, String>(TOPIC, data), new Callback() {
                    @Override
                    public void onCompletion(RecordMetadata metadata, Exception exception) {
                        // 回调方法，不管消息发送成功还是不成功都会回调该方法
                        System.out.println("Message Send Success");
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }

            messageNo++;
        }

        kafkaProducer.close();
    }
}