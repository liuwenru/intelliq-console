package RedisApp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Protocol;
import redis.clients.jedis.commands.ProtocolCommand;
import redis.clients.jedis.util.SafeEncoder;

import java.util.List;

/**
 *  在Redis3.0中实现发送自定义命令操作
 *
 */

public class CustomCommandApp {
    private static Logger logger=LoggerFactory.getLogger(CustomCommandApp.class);
    static enum EPOINTCOMMAND implements ProtocolCommand {
        EPOINT_CONFIG("EPOINT_CONFIG")  ;
        private final byte[] raw;
        EPOINTCOMMAND(String alt) {
            raw = SafeEncoder.encode(alt);
        }
        @Override
        public byte[] getRaw() {
            return raw;
        }
    }
    public static void  main(String[] args){
//        Jedis jedis=new Jedis("192.168.188.150",6379);
//        jedis.auth("Gepoint");
//        Client client=jedis.getClient();
//        client.sendCommand(EPOINTCOMMAND.EPOINT_CONFIG,new byte[][]{Protocol.Keyword.GET.raw, "port".getBytes()});
//        List<String> strlist= client.getMultiBulkReply();
//        System.out.println(strlist);
        String a="123";
        String b="123";
        System.out.println(a.getBytes());
        System.out.println(b.getBytes());
    }
}

