package HadoopApps;

import com.alibaba.druid.sql.dialect.hive.parser.HiveCreateTableParser;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.security.UserGroupInformation;

import java.io.IOException;

public class HBaseApps {
    public static void main(String[] args) throws IOException {
        Configuration config=HBaseConfiguration.create();
        config.set("hbase.zookeeper.quorum","epnode1.epoint,epnode2.epoint,epnode3.epoint");
        //config.set("hbase.zookeeper.property.clientPort", "2181");
        config.set("zookeeper.znode.parent", "/hbase-unsecure");
        Connection conn = ConnectionFactory.createConnection(config);
        Table table=conn.getTable(TableName.valueOf("testtable"));
        Put put=new Put(Bytes.toBytes("row1"));
        put.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("qual1"),Bytes.toBytes("val1-ijarvis-epoint========="));
        //put.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("qual2"),Bytes.toBytes("val2-ijarvis"));
        table.put(put);
    }
}
