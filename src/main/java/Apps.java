import redis.clients.jedis.Jedis;

import java.io.*;

/**
 * Created by ijarvis on 2017/5/17.
 */
public class Apps {


    public static void  main(String[] args) throws IOException {
        try {
            Jedis jedis = new Jedis(args[0],Integer.parseInt(args[1]));
            if (args.length==3 && !args[2].equals("")){
                jedis.auth(args[2]);
            }
            jedis.set("epoint","success");
            System.out.println("set key success。。。。。。。。。。。。。");
            jedis.close();
        }catch (Exception e) {
            throw e;
        }

    }


}
