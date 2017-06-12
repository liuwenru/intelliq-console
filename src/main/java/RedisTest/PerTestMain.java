package RedisTest;

import org.apache.log4j.Logger;

public class PerTestMain {
    private static Logger logger=Logger.getLogger(RedisTestUtil.class);
    public static void main(String[] args){

        if (args.length!=2){
            logger.error("please give paraments [get|set] 【cluster|signal】");
            return;
        }
        if (args[0].equals("set")){
            if (args[1].equals("cluster")){
                RedisClusterTestUtil.doPerTest();
            } else{
                RedisTestUtil.doPerTest();
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
