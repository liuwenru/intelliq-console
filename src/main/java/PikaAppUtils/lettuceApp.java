package PikaAppUtils;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisStringCommands;
import io.lettuce.core.pubsub.RedisPubSubListener;
import io.lettuce.core.pubsub.api.sync.RedisPubSubCommands;

public class lettuceApp {
    public  static void  main(String[] args){
        RedisClient client = RedisClient.create("redis://epoint:epoint@192.168.206.123:9221");
    }
}
