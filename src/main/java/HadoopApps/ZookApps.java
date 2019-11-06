package HadoopApps;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.List;

public class ZookApps {
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        ZooKeeper zk=new ZooKeeper("epnode1.epoint", 5000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {

            }
        });
        List<String> items=zk.getChildren("/hbase-secure/rs",true);
        for(String iem:items){
            System.out.println(iem);
        }
    }
}
