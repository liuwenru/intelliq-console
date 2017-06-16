package RedisTest;


import org.apache.log4j.Logger;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;
import sun.security.x509.EDIPartyName;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/*
   集群模式的操作线程类
 */
public class RedisClusterThread extends Thread {
    private static Logger logger=Logger.getLogger(RedisClusterThread.class);
    private static JedisCluster jedisCluster=null;
    private static int COUNT=1000; //每个线程插入的个数
    private static int bytescount=2;//生成的字节大小  36b*bytescount
    private int threadnum=0; //线程编号
    private String modle="set";  //测试的模式
    private  String[] keys=null;
    public RedisClusterThread(int num){
        this.threadnum=num;
        this.keys=new String[COUNT];
        this.modle=System.getenv("testmodle");
    }
    public static void init(){
        //初始化连接池等信息
        Set<HostAndPort> jedisClusterNodes=new HashSet<HostAndPort>();
        jedisClusterNodes.add(new HostAndPort("192.168.206.121",6379));
        jedisClusterNodes.add(new HostAndPort("192.168.206.122",6379));
        jedisClusterNodes.add(new HostAndPort("192.168.206.123",6379));
        jedisClusterNodes.add(new HostAndPort("192.168.206.124",6379));
        jedisClusterNodes.add(new HostAndPort("192.168.206.125",6379));
        jedisClusterNodes.add(new HostAndPort("192.168.206.126",6379));
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(1000);
        config.setMaxIdle(100);
        config.setTestOnBorrow(true);
        jedisCluster = new JedisCluster(jedisClusterNodes,5000,5000,5000,"Gepoint",config);
        if (System.getenv("bytecount")!=null){
            logger.debug("set key and value size:"+System.getenv("bytecount"));
            bytescount=Integer.parseInt(System.getenv("bytecount"));
        }

    }
    @Override
    public void run() {
        if (this.modle.equals("set")){
            this.doSetPer();
        }else {
            this.doGetPer();
        }
    }

    public void doSetPer(){
        //测试Set方法
        if (RedisClusterThread.jedisCluster==null){
            //防止没有初始化
            logger.error("不应该跑这个代码");
            RedisClusterThread.init();
        }
        long start=System.currentTimeMillis();
        int startkey=this.threadnum*1000;
        int endkey=this.threadnum*1000+COUNT;
        logger.debug("线程"+threadnum+"-----"+"插入值范围"+startkey+"---"+endkey);
        for (int i =startkey;i<endkey;i++){
//            String key=String.valueOf(i);
//            String value=String.valueOf(i);
//            jedisCluster.set(key,value.toString());
            String uuid= UUID.randomUUID().toString();
            StringBuffer key=new StringBuffer(uuid);
            for (int j=0;j<bytescount;j++){
                key=key.append(uuid);
            }
            jedisCluster.set(key.toString(),key.toString());
            this.keys[i%COUNT]=key.toString();
        }
        long end=System.currentTimeMillis();
        logger.info("-----------------------CLUSTER----SET-------------------------------");
        logger.info("set "+ COUNT+" keys"+(end-start)  +"毫秒");
        logger.info("-----------------------CLUSTER----SET-------------------------------");
    }
    public void doGetPer(){
        //测试Get方法
        if (RedisClusterThread.jedisCluster==null){
            //防止没有初始化
            logger.error("不应该跑这个代码");
            RedisClusterThread.init();
        }
        this.doSetPer();
        long start=System.currentTimeMillis();
        for(int i=0;i<this.keys.length;i++){
            jedisCluster.get(keys[i]);
        }
        long end=System.currentTimeMillis();
        logger.info("-------------------CLUSTER----GET-----------------------------------");
        logger.info("get "+COUNT+" keys"+(end-start)+"毫秒");
        logger.info("-------------------CLUSTER----GET-----------------------------------");
    }
}
