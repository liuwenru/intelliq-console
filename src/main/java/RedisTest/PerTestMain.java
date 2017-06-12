package RedisTest;

import org.apache.log4j.Logger;

public class PerTestMain {
    private static Logger logger=Logger.getLogger(RedisTestUtil.class);
    public static void main(String[] args){

        if (args.length!=1){
            logger.error("please give paraments 【cluster|signal】");
            return;
        }
        if (args[0].equals("cluster")){
            RedisClusterTestUtil.doPerTest();
        }else {
            RedisTestUtil.doPerTest();
        }

    }
}
