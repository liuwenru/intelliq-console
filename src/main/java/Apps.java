import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


public class Apps {
    public static Logger logger=Logger.getLogger(Apps.class);
    public static void  main(String[] args) {
        try{
            JedisPool jedisPool;
            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            String hostip=args[0];
            int port=Integer.parseInt(args[1]);
            String password=args[2];
            jedisPool = new JedisPool(jedisPoolConfig,hostip,port,3000,password,Integer.parseInt(args[3]));
            Jedis jedis = jedisPool.getResource();
            int i=1;
            while (true){
                jedis.set("epoint", String.valueOf(i));
                i++;
                logger.debug("Setting "+i+"     "+ jedis.get("epoint"));
                Thread.sleep(2000);
            }


        }catch (Exception e){
            logger.info("Test FastDFS errot");
            logger.error("Test FastDFS errot",e);
        }


    }
}
