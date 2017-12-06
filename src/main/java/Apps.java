import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.math.BigDecimal;
import java.sql.*;

public class Apps extends Thread {
    private static Logger logger=Logger.getLogger(Apps.class);
    private Subscriber subscriber=new Subscriber();
    private static String channel = "mychannel";
    private static Jedis jedis=null;
    public static void  main(String[] args) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException, InterruptedException {
        channel=args[0];
        JedisPoolConfig config=new JedisPoolConfig();
        config.setTestWhileIdle(true);
        JedisPool pool=new JedisPool(config,args[1],6379,3000,"Gepoint");
        jedis=pool.getResource();
        logger.debug(jedis.ping());
        Apps apps=new Apps();
        apps.start();
    }

    @Override
    public void run() {
        jedis.subscribe(subscriber, channel);
    }
}