package HadoopApps;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.security.UserGroupInformation;

import java.io.IOException;

public class HbaseAppswithKerberos {
    private static final String HBASE_MASTER_PRINCIPAL = "hbase/_HOST@epoint";
    private static final String HBASE_RS_PRINCIPAL = "hbase/_HOST@epoint";
    public static void main(String[] args) throws IOException {
        System.setProperty("java.security.krb5.conf","/home/ijarvis/workspace/javaWorkSpace/intelliq-console/src/main/resources/krb5.conf");
        System.setProperty("java.security.auth.login.config","/home/ijarvis/workspace/javaWorkSpace/intelliq-console/src/main/resources/jaas.conf");
        Configuration config= HBaseConfiguration.create();
        config.set("hbase.zookeeper.quorum","epnode1.epoint,epnode2.epoint,epnode3.epoint");
        config.set("zookeeper.znode.parent", "/hbase-secure");
        config.set("hadoop.security.authentication", "Kerberos");
        config.set("hbase.security.authentication", "kerberos");
        config.set("hbase.master.kerberos.principal", HBASE_MASTER_PRINCIPAL);
        config.set("hbase.regionserver.kerberos.principal",HBASE_RS_PRINCIPAL);
        UserGroupInformation.setConfiguration(config);
        UserGroupInformation.loginUserFromKeytab("hdfs@epoint", "/home/ijarvis/workspace/javaWorkSpace/intelliq-console/src/main/resources/client_hdfs.keytab");
        Connection conn = ConnectionFactory.createConnection(config);
        Table table=conn.getTable(TableName.valueOf("testtable"));
        Put put=new Put(Bytes.toBytes("row3"));
        put.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("qual1"),Bytes.toBytes("val1-ijarvis-epoint-newvalue"));
        //put.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("qual2"),Bytes.toBytes("val2-ijarvis"));
        table.put(put);
    }
}
