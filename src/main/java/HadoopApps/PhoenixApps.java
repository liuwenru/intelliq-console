package HadoopApps;

import java.sql.*;

public class PhoenixApps {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.apache.phoenix.jdbc.PhoenixDriver");
        Connection conn = DriverManager.getConnection("jdbc:phoenix:epnode1.epoint:2181:/hbase-unsecure","admin","Gepoint");
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("create table testepoint (mykey integer not null primary key, mycolumn varchar)");
        stmt.executeUpdate("upsert into testepoint values (1,'Hello')");
        stmt.executeUpdate("upsert into testepoint values (2,'World!')");
        conn.commit();
        PreparedStatement statement = conn.prepareStatement("select * from testepoint");
        ResultSet rset = statement.executeQuery();
        while (rset.next()) {
            System.out.println(rset.getString(2));
        }
    }
}
