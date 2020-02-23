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
        System.setProperty("java.security.krb5.conf",args[0]);
        //System.setProperty("java.security.auth.login.config","src/main/resources/jaas.conf");
        Configuration config= HBaseConfiguration.create();
        config.set("hbase.zookeeper.quorum",args[1]);
        config.set("zookeeper.znode.parent", args[2]);
        config.set("hadoop.security.authentication", "Kerberos");
        config.set("hbase.security.authentication", "kerberos");
        config.set("hbase.master.kerberos.principal", args[3]);
        config.set("hbase.regionserver.kerberos.principal",args[3]);
        UserGroupInformation.setConfiguration(config);
        UserGroupInformation.loginUserFromKeytab(args[4], args[5]);
        Connection conn = ConnectionFactory.createConnection(config);
        Table table=conn.getTable(TableName.valueOf("testtable"));
        for(int i=0;i<=9;i++){
            for(int j=0;j<=9;j++){
                for(int k=0;k<=9;k++){

                }
            }
        }
        Put put=new Put(Bytes.toBytes("row3"));
        put.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("qual1"),Bytes.toBytes("val1-ijarvis-epoint-22222newvalue"));
        //put.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("qual2"),Bytes.toBytes("val2-ijarvis"));
        table.put(put);
    }
}
