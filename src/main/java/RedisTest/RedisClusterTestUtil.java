package RedisTest;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
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
    public static void doPerTest(){
        Set<HostAndPort> jedisClusterNodes=new HashSet<HostAndPort>();
//        jedisClusterNodes.add(new HostAndPort("192.168.206.121",6379));
//        jedisClusterNodes.add(new HostAndPort("192.168.206.122",6379));
//        jedisClusterNodes.add(new HostAndPort("192.168.206.123",6379));
//        jedisClusterNodes.add(new HostAndPort("192.168.206.124",6379));
//        jedisClusterNodes.add(new HostAndPort("192.168.206.125",6379));
//        jedisClusterNodes.add(new HostAndPort("192.168.206.126",6379));
        jedisClusterNodes.add(new HostAndPort("192.168.206.134",6379));
        jedisClusterNodes.add(new HostAndPort("192.168.206.136",6379));
        jedisClusterNodes.add(new HostAndPort("192.168.206.137",6379));
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(1000);
        config.setMaxIdle(100);
        config.setTestOnBorrow(true);
        JedisCluster jedisCluster = new JedisCluster(jedisClusterNodes, 5000,5000,5000,"Gepoint", config);
        long start=System.currentTimeMillis();
        int COUNT=100000;
        for (int i =0;i<COUNT;i++){
            if ((COUNT%10000)==0){
                logger.debug("Success.........."+(i*1.0/COUNT)*100+" %");
            }
            String uuid=UUID.randomUUID().toString();
            jedisCluster.set(uuid,uuid);
        }
        long end=System.currentTimeMillis();
        logger.debug("----------------------------------------------------------");
        logger.debug("set "+ COUNT+" keys"+(end-start) );
        logger.debug("----------------------------------------------------------");
    }

}
