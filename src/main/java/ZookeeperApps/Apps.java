package ZookeeperApps;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Apps {
    private static Logger logger= LoggerFactory.getLogger(Apps.class);
    public static void main(String[] args) throws IOException {
        String hosts="192.168.188.111:2181,192.168.188.112:2181,192.168.188.113:2181";
        String zpath = "/";
        List<String> zooChildren = new ArrayList<String>();
        ZooKeeper zk = new ZooKeeper(hosts, 2000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                logger.debug("Watcher........");
            }
        });
        if (zk != null) {
            try {
                zooChildren = zk.getChildren(zpath, false);
                System.out.println("Znodes of '/': ");
                for (String child: zooChildren) {
                    //print the children
                    System.out.println(child);
                }
            } catch (KeeperException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
