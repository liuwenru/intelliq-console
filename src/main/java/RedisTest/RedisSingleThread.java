package RedisTest;

import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.UUID;

/**
 * Created by ijarvis on 2017/6/15.
 */
public class RedisSingleThread extends Thread {
    private static Logger logger=Logger.getLogger(RedisSingleThread.class);
    private static JedisPool pool=null;
    private static int COUNT=1000; //每个线程插入的个数
    private static int bytescount=2;//生成的字节大小  36b*bytescount
    private int threadnum=0;//线程编号
    private String[] keys=null;
    private String modle="set";  //测试的模式
    public static void init(){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(1000);
        config.setMaxIdle(100);
        config.setTestOnBorrow(true);
        pool = new JedisPool(config,"192.168.206.247",6379);
        if (System.getenv("bytecount")!=null){
            logger.debug("set key and value size:"+System.getenv("bytecount"));
            bytescount=Integer.parseInt(System.getenv("bytecount"));
        }
    }
    public RedisSingleThread(int threadnum){
        this.threadnum=threadnum;
        this.keys=new String[COUNT];
        this.modle=System.getenv("testmodle");
    }
    @Override
    public void run() {
        if (this.modle.equals("set")){
            this.doSetPer();
        }else {
            this.doGetPer();
        }
    }
    public  void doSetPer(){
        Jedis jedis=pool.getResource();
        if (RedisSingleThread.pool==null){
            //防止没有初始化
            logger.error("不应该跑这个代码");
            RedisSingleThread.init();
        }
        long start=System.currentTimeMillis();
        int startkey=this.threadnum*1000;
        int endkey=this.threadnum*1000+COUNT;
        logger.debug("单实例Redis--多线程"+threadnum+"-----"+"插入值范围"+startkey+"---"+endkey);
        for (int i =startkey;i<endkey;i++) {
            //String key=String.valueOf(i);
            //String value=String.valueOf(i);
            //jedis.set(key,value.toString());
            String uuid= UUID.randomUUID().toString();
            StringBuffer key=new StringBuffer(uuid);
            for (int j=0;j<bytescount;j++){
                key=key.append(uuid);
            }
            jedis.set(key.toString(),key.toString());
            keys[i%COUNT]=key.toString();
        }
        long end=System.currentTimeMillis();
        logger.info("------------------Single-------SET---------------------------------");
        logger.info("set "+ COUNT+" keys"+(end-start) +"毫秒");
        logger.info("------------------Single-------SET---------------------------------");
    }

    public void doGetPer(){
        //测试Get方法
        Jedis jedis=pool.getResource();
        if (RedisSingleThread.pool==null){
            //防止没有初始化
            logger.error("不应该跑这个代码");
            RedisSingleThread.init();
        }
        this.doSetPer();
        long start=System.currentTimeMillis();
        for(int i=0;i<this.keys.length;i++){
            jedis.get(keys[i]);
        }
        long end=System.currentTimeMillis();
        logger.info("-------------------Single----GET-----------------------------------");
        logger.info("get "+COUNT+" keys"+(end-start)+"毫秒");
        logger.info("-------------------Single----GET-----------------------------------");
    }
}
