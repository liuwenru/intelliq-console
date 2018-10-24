package RedisApp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Protocol;
import redis.clients.jedis.commands.ProtocolCommand;
import redis.clients.jedis.util.SafeEncoder;

/**
 *  在Redis3.0中实现发送自定义命令操作
 *
 */



public class CustomCommandApp {
    private static Logger logger=LoggerFactory.getLogger(CustomCommandApp.class);

    public static enum EPOINTCOMMAND implements ProtocolCommand {
        EPOINT_CONFIG;
        private final byte[] raw = SafeEncoder.encode(this.name());
        private EPOINTCOMMAND() {
        }
        @Override
        public byte[] getRaw() {
            return this.raw;
        }
    }
    public static void  main(String[] args){
        Jedis jedis=new Jedis("192.168.188.72",6379);
        jedis.auth("Gepoint");
        jedis.getClient().sendCommand(EPOINTCOMMAND.EPOINT_CONFIG, "set","notify-keyspace-events","ll");
        String res=jedis.getClient().getStatusCodeReply();
        logger.debug("resout is :"+res);
    }
}

