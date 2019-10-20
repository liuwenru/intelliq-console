package RedisUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

public class SubThread extends Thread {
    private JedisCluster jedisCluster;
    private Subscriber subscriber = new Subscriber();
    private String channel = "mychannel";

    public SubThread(JedisCluster jedisCluster) {
        super("SubThread");
        this.jedisCluster = jedisCluster;
    }

    @Override
    public void run() {
        System.out.println(String.format("subscribe redis, channel %s, thread will be blocked", channel));
        Jedis jedis = null;
        try {
            //jedis = jedisPool.getResource();
            jedisCluster.subscribe(subscriber, channel);
        } catch (Exception e) {
            System.out.println(String.format("subsrcibe channel error, %s", e));
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}