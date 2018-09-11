package RedisClusterApp;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class RedisClusterTest {
    private static JedisCluster jedis;
    private static Set<HostAndPort> hostAndPortsSet;
    private static  JedisPoolConfig jedisPoolConfig;
    static {
        // 添加集群的服务节点Set集合
        hostAndPortsSet = new HashSet<HostAndPort>();
        // 添加节点
        hostAndPortsSet.add(new HostAndPort("192.168.188.77", 6379));
        hostAndPortsSet.add(new HostAndPort("192.168.188.78", 6380));
        hostAndPortsSet.add(new HostAndPort("192.168.188.79", 6380));


        // Jedis连接池配置
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 最大空闲连接数, 默认8个
        jedisPoolConfig.setMaxIdle(10);
        // 最大连接数, 默认8个
        jedisPoolConfig.setMaxTotal(500);
        //最小空闲连接数, 默认0
        jedisPoolConfig.setMinIdle(0);
        // 获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1
        jedisPoolConfig.setMaxWaitMillis(2000); // 设置2秒
        //对拿到的connection进行validateObject校验
        jedisPoolConfig.setTestOnBorrow(true);
        jedis = new JedisCluster(hostAndPortsSet, jedisPoolConfig);

    }
    public static void main(String[] args) throws InterruptedException {
        redisclusterthread[] threads =new redisclusterthread[1000];
        for (int i =0;i< 1000;i++){
            threads[i]=new redisclusterthread(jedis);
        }
        for (int i =0;i< 1000;i++){
            threads[i].start();
        }
        Thread.sleep(10000000);
    }
}

class redisclusterthread extends Thread{
    private JedisCluster jedisCluster=null;

    public redisclusterthread(JedisCluster jedis){
        jedisCluster=jedis;
    }
    @Override
    public void run() {
        System.out.println("---");
        jedisCluster.set(UUID.randomUUID().toString(), UUID.randomUUID().toString());
    }
}
