package RedisApp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

public class RedisApps {
    private static Logger logger= LoggerFactory.getLogger(RedisApps.class);
    public static void  main(String[] args){

        Jedis jedis=new Jedis("192.168.188.75",6379);
        jedis.auth("Gepoint");
        int count=1;
        String value="a";
        for (int i =0 ;i<204800;i++){
            value=value+"a";
        }
        while (true){
            String key="key"+count;
            logger.debug(key);
            jedis.set(key,value);
            count++;

        }

    }
}
