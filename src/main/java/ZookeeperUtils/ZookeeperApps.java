package ZookeeperUtils;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ZookeeperApps {
    private static Logger logger= LoggerFactory.getLogger(ZookeeperApps.class);
    public static void  main(String[] args) throws IOException, KeeperException, InterruptedException {
        // 配置使用Zookpeer的用户名和密码访问
        ZooKeeper zk = new ZooKeeper("192.168.204.10:2181", 300000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
            }
        });
        zk.addAuthInfo("digest", "epoint:Gepoint".getBytes());
        //zk.setData("/","epointdata111111111111111".getBytes(),-1);
        logger.debug("--------------");
        logger.debug("RESOUT is :"+new String(zk.getData("/zookeeper/com.epoint.epointqlk.attach.api.IQlkAttach", false, null)));
    }
}
