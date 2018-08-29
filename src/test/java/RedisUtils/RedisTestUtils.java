package RedisUtils;
;
import org.junit.Ignore;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisTestUtils {
    @Test
    public void  RedisLettuceTest(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 设置最大10个连接
        jedisPoolConfig.setMaxTotal(10);

        JedisPool pool = new JedisPool(jedisPoolConfig, "192.168.188.150",6379,5000,"Gepoint");
        Jedis jedis=pool.getResource();
        jedis.set("epoint","Gepoint");
        jedis.close();

    }


}
