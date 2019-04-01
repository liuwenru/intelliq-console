package RedisConnection;

import redis.clients.jedis.Jedis;

public class Apps {

  public static void main(String[] args){
    Jedis jedis=new Jedis("192.168.188.150",6379);
    jedis.auth("Gepoint");

    String value=jedis.get("name");

    System.out.println(value);



  }
}
