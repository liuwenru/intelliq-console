package RedisTest;

import org.apache.log4j.Logger;

import java.util.ArrayList;

/**
 * Created by ijarvis on 2017/6/15.
 */
public class MultThreadTest {
    private static Logger logger=Logger.getLogger(MultThreadTest.class);
    private static int threadcount=4;
    public static void main(String[] args) throws InterruptedException {

        if (args.length!=2){
            logger.error("please give paraments [get|set] 【cluster|signal】");
            return;
        }
        //初始化连接的东西
        RedisClusterThread.init();
        RedisSingleThread.init();
        ArrayList<RedisClusterThread> clusterPerTestlist=new ArrayList<>();
        for (int i=0;i<MultThreadTest.threadcount;i++){
            clusterPerTestlist.add(new RedisClusterThread(i));
        }

        ArrayList<RedisSingleThread> singlePerTestlist=new ArrayList<>();
        for (int i=0;i<MultThreadTest.threadcount;i++){
            singlePerTestlist.add(new RedisSingleThread(i));
        }
        long start=System.currentTimeMillis();
        if (args[0].equals("set")){
            if (args[1].equals("cluster")){
                logger.info("测试多线程集群模式Set");
                for (int i=0;i<MultThreadTest.threadcount;i++){
                    clusterPerTestlist.get(i).start();
                    //clusterPerTestlist.get(i).join();
                }
                for (int i=0;i<MultThreadTest.threadcount;i++){
                    clusterPerTestlist.get(i).join();
                }
                long end=System.currentTimeMillis();
                logger.info("*******"+(end-start)+"*******");
            } else{
                logger.info("测试多线程单实例模式Set");
                for (int i=0;i<MultThreadTest.threadcount;i++){
                    singlePerTestlist.get(i).start();
                    //singlePerTestlist.get(i).join();
                }
                for (int i=0;i<MultThreadTest.threadcount;i++){
                    singlePerTestlist.get(i).join();
                }
                long end=System.currentTimeMillis();
                logger.info("*******"+(end-start)+"*******");
            }
        }else {
            if (args[1].equals("cluster")){
                RedisClusterTestUtil.doGetPer();
            }else {
                RedisTestUtil.doGetPer();
            }
        }

    }
}
