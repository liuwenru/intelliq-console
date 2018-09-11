package RedisClusterApp;

import redis.clients.jedis.Jedis;

public class RedisSingle {

    public static void main(String args[]){
        Jedis jedis=new Jedis();
        jedis.slowlogGet();

    }
}
