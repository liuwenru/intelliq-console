package MemcachedApp;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClient;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.auth.AuthInfo;
import net.rubyeye.xmemcached.command.BinaryCommandFactory;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.utils.AddrUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;

public class Apps {
    public static void main(String[] args) throws IOException, InterruptedException, MemcachedException, TimeoutException {
        MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses("192.168.188.150:11211"));
        builder.addAuthInfo(AddrUtil.getOneAddress("192.168.188.150:11211"),AuthInfo.plain("epoint", "Gepoint"));
        builder.setCommandFactory(new BinaryCommandFactory());
        MemcachedClient client=builder.build();
        client.set("epointkey",1000,"epointijarvis");
        String v = client.get("epointkey");
        System.out.println(v);

    }

}
