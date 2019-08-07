package RedisUtils;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

public class JedisClusterApps {
    public static void main(String[] args){
        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
        jedisClusterNodes.add(new HostAndPort("192.168.189.54",7000));
        jedisClusterNodes.add(new HostAndPort("192.168.189.55",7000));
        jedisClusterNodes.add(new HostAndPort("192.168.189.56",7000));
        JedisCluster jc = new JedisCluster(jedisClusterNodes,5000,5000,5000,"Gepoint",new GenericObjectPoolConfig());
        //JedisCluster jc = new JedisCluster(jedisClusterNodes);
        int  keycert=0;
        while (true){
            try{
                keycert=keycert+1;
                jc.set("epoint"+keycert,"aaaaa");
            }catch (Exception e){
                e.getMessage();
            }

        }

    }
}
