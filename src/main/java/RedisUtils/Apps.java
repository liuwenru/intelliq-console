package RedisUtils;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.*;

import java.util.HashSet;
import java.util.Set;

public class Apps extends  Thread {
    private static Logger logger= LoggerFactory.getLogger(Apps.class);
    public  static void main(String[] args)  throws InterruptedException {
        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
//        jedisClusterNodes.add(new HostAndPort("192.168.188.106",7001));
//        jedisClusterNodes.add(new HostAndPort("192.168.188.107",7001));
//        jedisClusterNodes.add(new HostAndPort("192.168.188.108",7001));
//        JedisCluster jc = new JedisCluster(jedisClusterNodes);

        jedisClusterNodes.add(new HostAndPort("192.168.188.109",7001));
        //jedisClusterNodes.add(new HostAndPort("192.168.188.107",7001));
        //jedisClusterNodes.add(new HostAndPort("192.168.188.108",7001));
        JedisCluster jc = new JedisCluster(jedisClusterNodes,5000,5000,5000,new GenericObjectPoolConfig());

        SubThread subThread=new SubThread(jc);
        subThread.start();

//        for(int i =0;i<7;i++){
//            jc.set(""+i,"11111");
//            logger.debug("-------");
//            Thread.sleep(1000);
//            logger.debug(jc.get("epoint"));
//        }
    }



}
