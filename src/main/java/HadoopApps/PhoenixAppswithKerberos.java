package HadoopApps;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.security.UserGroupInformation;

import java.io.IOException;
import java.sql.*;

public class PhoenixAppswithKerberos {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        System.setProperty("java.security.krb5.conf","/home/ijarvis/workspace/javaWorkSpace/intelliq-console/src/main/resources/krb5.conf");
//        Configuration config= HBaseConfiguration.create();
//        config.set("hbase.zookeeper.quorum","epnode1.epoint,epnode2.epoint,epnode3.epoint");
//        config.set("zookeeper.znode.parent", "/hbase-secure");
//        config.set("hadoop.security.authentication", "Kerberos");
//        config.set("hbase.security.authentication", "kerberos");
//        config.set("hadoop.security.authentication", "Kerberos");
//        UserGroupInformation.setConfiguration(config);
//        UserGroupInformation.loginUserFromKeytab("hbase-epointhadoop@epoint", "/home/ijarvis/workspace/javaWorkSpace/intelliq-console/src/main/resources/hbase.keytab");
        Class.forName("org.apache.phoenix.jdbc.PhoenixDriver");
        Connection conn = DriverManager.getConnection("jdbc:phoenix:epnode1.epoint:2181:/hbase-secure:hbase-epointhadoop@epoint:/home/ijarvis/workspace/javaWorkSpace/intelliq-console/src/main/resources/hbase.keytab");
        Statement stmt = conn.createStatement();
//        stmt.executeUpdate("create table testepoint (mykey integer not null primary key, mycolumn varchar)");
//        stmt.executeUpdate("upsert into testepoint values (1,'Hello')");
//        stmt.executeUpdate("upsert into testepoint values (2,'World!')");
//        conn.commit();
        PreparedStatement statement = conn.prepareStatement("select * from testepoint");
        ResultSet rset = statement.executeQuery();
        while (rset.next()) {
            System.out.println(rset.getString(2));
        }
    }
}