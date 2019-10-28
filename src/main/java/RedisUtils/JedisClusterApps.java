package RedisUtils;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class JedisClusterApps {
    public static void main(String[] args) throws IOException {
        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
        jedisClusterNodes.add(new HostAndPort("192.168.189.54",7000));
        jedisClusterNodes.add(new HostAndPort("192.168.189.54",7001));
        jedisClusterNodes.add(new HostAndPort("192.168.189.55",7000));
        jedisClusterNodes.add(new HostAndPort("192.168.189.55",7001));
        jedisClusterNodes.add(new HostAndPort("192.168.189.56",7000));
        jedisClusterNodes.add(new HostAndPort("192.168.189.56",7001));
        JedisCluster jc = new JedisCluster(jedisClusterNodes,5000,5000,5000,"Gepoint",new GenericObjectPoolConfig());
        //JedisCluster jc = new JedisCluster(jedisClusterNodes);
        jc.set("ijarvis","epoint");
        jc.close();
    }
}
