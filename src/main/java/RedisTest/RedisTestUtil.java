package RedisTest;

import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import java.util.UUID;

public class RedisTestUtil {
    private static Logger logger=Logger.getLogger(RedisTestUtil.class);
    private static Jedis jedis=null;
    private static int COUNT=10000;
    public static void init(){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(1000);
        config.setMaxIdle(100);
        config.setTestOnBorrow(true);
        JedisPool pool = new JedisPool(config,"192.168.206.247",6379);
        jedis=pool.getResource();
    }
    public static void doSetPer(){
        if (RedisTestUtil.jedis==null){
            RedisTestUtil.init();
        }
        long start=System.currentTimeMillis();
        for (int i =0;i<COUNT;i++) {
            if ((COUNT%10000)==0){
                logger.debug("Success.........."+(i*1.0/COUNT)*100+" %");
            }
            String key=String.valueOf(i);
            String value=String.valueOf(i);
            jedis.set(key,value.toString());
        }
        long end=System.currentTimeMillis();
        logger.info("-------------------------SET---------------------------------");
        logger.info("set "+ COUNT+" keys"+(end-start) );
        logger.info("-------------------------------------------------------------");
    }
    public static void doGetPer(){
        if (RedisTestUtil.jedis==null){
            RedisTestUtil.init();
        }
        long start=System.currentTimeMillis();
        for (int i =0;i<COUNT;i++) {
            if ((COUNT%1000)==0){
                logger.debug("Success.........."+(i*1.0/COUNT)*100+" %");
            }
            jedis.get(Integer.toString(i));
        }
        long end=System.currentTimeMillis();
        logger.info("------------------------GET------------------------------------");
        logger.info("set "+ COUNT+" keys"+(end-start) );
        logger.info("----------------------------------------------------------------");
    }
}
