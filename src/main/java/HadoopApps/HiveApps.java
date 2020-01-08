package HadoopApps;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.security.UserGroupInformation;

import java.sql.*;

public class HiveApps {
    private static String driverName = "org.apache.hive.jdbc.HiveDriver";
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Configuration conf=new Configuration();
        //conf.set("fs.defaultFS","hdfs://192.168.186.124:8020");
        //conf.set("dfs.namenode.kerberos.principal.pattern", "*/*@epoint");
        Class.forName(driverName);
        //Connection con = DriverManager.getConnection("jdbc:hive2://epnode2:10000/default;principal=hive/epnode2.epoint@epoint", "hive", "");
        Connection con = DriverManager.getConnection("jdbc:hive2://epnode2.epoint:2181,epnode3.epoint:2181,epnode1.epoint:2181/;serviceDiscoveryMode=zooKeeper;zooKeeperNamespace=hiveserver2", "hive", "");
        Statement stmt = con.createStatement();
        ResultSet resultSet= stmt.executeQuery("select epointfunc('aAABBBBBBCCCCCCBBBBa')");
        //ResultSet resultSet= stmt.executeQuery("show tables");
        while (resultSet.next()){
            System.out.println(resultSet.getString(1));
        }

    }



}
