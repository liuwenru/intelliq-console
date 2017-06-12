package RedisTest;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import org.apache.log4j.Logger;

/**
 * Created by 11255 on 2017/6/12.
 */
public class RedisClusterTestUtil {
    private static Logger logger=Logger.getLogger(RedisClusterTestUtil.class);
    private static JedisCluster jedisCluster=null;
    private static int COUNT=10000;
    static {
        logger.debug("#######################################################");
    }
    public static void init(){
        Set<HostAndPort> jedisClusterNodes=new HashSet<HostAndPort>();
        jedisClusterNodes.add(new HostAndPort("192.168.206.134",6379));
        jedisClusterNodes.add(new HostAndPort("192.168.206.136",6379));
        jedisClusterNodes.add(new HostAndPort("192.168.206.137",6379));
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(1000);
        config.setMaxIdle(100);
        config.setTestOnBorrow(true);
        jedisCluster = new JedisCluster(jedisClusterNodes,config);
    }
    public static void doSetPer(){
        if (RedisClusterTestUtil.jedisCluster==null){
            RedisClusterTestUtil.init();
        }
        long start=System.currentTimeMillis();
        for (int i =0;i<COUNT;i++){
            if ((COUNT%10000)==0){
                logger.debug("Success.........."+(i*1.0/COUNT)*100+" %");
            }
            String key=String.valueOf(i);
            String value=String.valueOf(i);
            jedisCluster.set(key,value.toString());
        }
        long end=System.currentTimeMillis();
        logger.info("----------------------------------------------------------");
        logger.info("set "+ COUNT+" keys"+(end-start) );
        logger.info("----------------------------------------------------------");
    }

    public static void doGetPer(){
        if (RedisClusterTestUtil.jedisCluster==null){
            RedisClusterTestUtil.init();
        }
        long start=System.currentTimeMillis();
        for (int i =0;i<COUNT;i++){
            if ((COUNT%1000)==0){
                logger.debug("Success.........."+(i*1.0/COUNT)*100+" %");
            }
           jedisCluster.get(Integer.toString(i));
        }
        long end=System.currentTimeMillis();
        logger.info("----------------------------------------------------------");
        logger.info("set "+ COUNT+" keys"+(end-start) );
        logger.info("----------------------------------------------------------");
    }

}
