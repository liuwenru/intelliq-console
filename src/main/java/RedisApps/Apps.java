package RedisApps;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;

public class Apps {
    public static void main(String[] args) throws InterruptedException, MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException {

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(10000);
        JedisPool pool = new JedisPool(jedisPoolConfig, "192.168.188.150", 6379, 2000, "Infra5_Gep0int");

//        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
//        ObjectName helloName = new ObjectName("jmxBean:name=jedisPool");
//        StandardMBean mbean = new StandardMBean(pool, JedisPoolMBean.class);
//        server.registerMBean(mbean, helloName);

        int i = 0;
        while (true) {
            System.out.println("--------------");
            i++;
            Jedis jedis = pool.getResource();
            if (i % 3 == 0) {
                jedis.set("name", "1");
                Thread.sleep(2000);
                continue;
            }
            jedis.close();
            Thread.sleep(2000);
        }
    }
}
