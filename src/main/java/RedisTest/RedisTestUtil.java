package RedisTest;

import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;

import java.util.UUID;

public class RedisTestUtil {
    private static Logger logger=Logger.getLogger(RedisTestUtil.class);
    public static void doPerTest(){
        Jedis jedis = new Jedis("192.168.206.247",6379);
        long start=System.currentTimeMillis();
        int COUNT=100000;
        for (int i =0;i<COUNT;i++) {
            if ((COUNT%10000)==0){
                logger.debug("Success.........."+(i*1.0/COUNT)*100+" %");
            }
            String uuid = UUID.randomUUID().toString();
            jedis.set(uuid, uuid);
        }
        long end=System.currentTimeMillis();
        logger.debug("----------------------------------------------------------");
        logger.debug("set "+ COUNT+" keys"+(end-start) );
        logger.debug("----------------------------------------------------------");
    }
}
