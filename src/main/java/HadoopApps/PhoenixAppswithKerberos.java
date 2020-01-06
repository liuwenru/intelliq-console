package HadoopApps;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.security.UserGroupInformation;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.UUID;

public class PhoenixAppswithKerberos {
    private static final String HBASE_MASTER_PRINCIPAL = "hbase/_HOST@epoint";
    private static final String HBASE_RS_PRINCIPAL = "hbase/_HOST@epoint";
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        System.setProperty("java.security.krb5.conf","/home/ijarvis/workspace/javaWorkSpace/intelliq-console/src/main/resources/krb5.conf");
        Configuration config= HBaseConfiguration.create();
        config.set("hbase.zookeeper.quorum","epnode1.epoint,epnode2.epoint,epnode3.epoint");
        config.set("zookeeper.znode.parent", "/hbase-secure");
        config.set("hbase.master.kerberos.principal", HBASE_MASTER_PRINCIPAL);
        config.set("hbase.regionserver.kerberos.principal",HBASE_RS_PRINCIPAL);
        config.set("phoenix.queryserver.kerberos.principal", HBASE_MASTER_PRINCIPAL);
        config.set("hbase.security.authentication", "kerberos");
        config.set("hadoop.security.authentication", "Kerberos");
        config.set("hadoop.rpc.protection", "privacy");
        UserGroupInformation.setConfiguration(config);
        UserGroupInformation.loginUserFromKeytab("hdfs@epoint", "/home/ijarvis/workspace/javaWorkSpace/intelliq-console/src/main/resources/client_hdfs.keytab");
        Class.forName("org.apache.phoenix.jdbc.PhoenixDriver");
        Properties properties=new Properties();
        properties.setProperty("hbase.zookeeper.quorum", "epnode1.epoint,epnode2.epoint,epnode3.epoint");
        properties.setProperty("hbase.master.kerberos.principal", HBASE_MASTER_PRINCIPAL);
        properties.setProperty("hbase.regionserver.kerberos.principal", HBASE_MASTER_PRINCIPAL);
        properties.setProperty("phoenix.queryserver.kerberos.principal", HBASE_MASTER_PRINCIPAL);
        properties.setProperty("hbase.security.authentication", "kerberos");
        properties.setProperty("hadoop.security.authentication", "kerberos");
        properties.setProperty("zookeeper.znode.parent", "/hbase-secure");
        Connection conn = DriverManager.getConnection("jdbc:phoenix:epnode1.epoint:2181:/hbase-secure:hdfs@epoint:/home/ijarvis/workspace/javaWorkSpace/intelliq-console/src/main/resources/client_hdfs.keytab",properties);
        //Connection conn = DriverManager.getConnection("jdbc:phoenix:epnode1.epoint:2181:/hbase-secure:hdfs@epoint:/home/ijarvis/workspace/javaWorkSpace/intelliq-console/src/main/resources/client_hdfs.keytab");
        Statement stmt = conn.createStatement();
        //stmt.executeUpdate("create table testepoint (mykey integer not null primary key, mycolumn varchar)");
        for (int i =0;i<500000;i++){
            stmt.executeUpdate("upsert into testepoint values ("+i+",'"+ UUID.randomUUID().toString() +"')");
        }
        //stmt.executeUpdate("upsert into testepoint values (2,'World!')");
        conn.commit();
        PreparedStatement statement = conn.prepareStatement("select * from testepoint");
        ResultSet rset = statement.executeQuery();
        while (rset.next()) {
            System.out.println(rset.getString(1));
        }
    }
}
