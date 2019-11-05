package HadoopApps;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.security.UserGroupInformation;

import java.io.IOException;
import java.sql.*;

public class HiveApps {
    private static String driverName = "org.apache.hive.jdbc.HiveDriver";
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        Configuration conf=new Configuration();
        System.setProperty("java.security.krb5.conf","/home/ijarvis/workspace/javaWorkSpace/intelliq-console/src/main/resources/krb5.conf");
        conf.set("hadoop.security.authentication", "Kerberos");
        //conf.set("fs.defaultFS","hdfs://192.168.186.124:8020");
        //conf.set("dfs.namenode.kerberos.principal.pattern", "*/*@epoint");
        UserGroupInformation.setConfiguration(conf);
        UserGroupInformation.loginUserFromKeytab("hdfs@epoint", "/home/ijarvis/workspace/javaWorkSpace/intelliq-console/src/main/resources/client_hdfs.keytab");
        Class.forName(driverName);
        //Connection con = DriverManager.getConnection("jdbc:hive2://epnode2:10000/default;principal=hive/epnode2.epoint@epoint", "hive", "");
        Connection con = DriverManager.getConnection("jdbc:hive2://epnode1.epoint:2181,epnode2.epoint:2181,epnode3.epoint:2181/;serviceDiscoveryMode=zooKeeper;zooKeeperNamespace=hiveserver2", "hive", "");
        Statement stmt = con.createStatement();
//        stmt.execute("insert into  userinfo values(1)");
//        stmt.execute("insert into  userinfo values(2)");
//        stmt.execute("insert into  userinfo values(3)");
        ResultSet resultSet= stmt.executeQuery("select * from userinfo");
        //ResultSet resultSet= stmt.executeQuery("show tables");
        while (resultSet.next()){
            System.out.println(resultSet.getString(1));
        }
    }
}
