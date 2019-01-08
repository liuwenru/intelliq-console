package RedisApp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisApps {
    private static Logger logger= LoggerFactory.getLogger(RedisApps.class);
    public static  void  main(String[] args) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 设置最大10个连接
        jedisPoolConfig.setMaxTotal(10);
        JedisPool pool = new JedisPool(jedisPoolConfig, "192.168.188.150", 6379, 30000, "Gepoint");
        Jedis jedis=pool.getResource();
        jedis.close();
        jedis.set("name","222222");
        jedis.close();
        jedis.set("name","111111");
    }
}
