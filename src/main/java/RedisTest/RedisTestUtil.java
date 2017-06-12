package RedisTest;

import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.UUID;

public class RedisTestUtil {
    private static Logger logger=Logger.getLogger(RedisTestUtil.class);
    public static void doPerTest(){

        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(1000);
        config.setMaxIdle(100);
        config.setTestOnBorrow(true);
        JedisPool pool = new JedisPool(config,"192.168.206.247",6379);
        Jedis jedis=pool.getResource();
        long start=System.currentTimeMillis();
        int COUNT=100000;
        for (int i =0;i<COUNT;i++) {
            if ((COUNT%10000)==0){
                logger.debug("Success.........."+(i*1.0/COUNT)*100+" %");
            }
            String uuid = UUID.randomUUID().toString();
            String key=uuid;
            StringBuffer value=new StringBuffer(uuid);
            value.append(uuid);
            value.append(uuid);
            value.append(uuid);
            jedis.set(key,value.toString());
        }
        long end=System.currentTimeMillis();
        logger.debug("----------------------------------------------------------");
        logger.debug("set "+ COUNT+" keys"+(end-start) );
        logger.debug("----------------------------------------------------------");
    }
}
