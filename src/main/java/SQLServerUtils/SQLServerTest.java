package SQLServerUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLServerTest {
    public static  void main(String[] args) throws ClassNotFoundException, SQLException, InterruptedException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://192.168.212.243\\SQL2008;databaseName=test";
        String user = "sa";
        String password = "Gepoint";
        Connection con = DriverManager.getConnection(url, user, password);
        while (true) {
            System.out.println("连接成功！");
            String sql = "select  1";
            Statement com = con.createStatement();
            com.executeQuery(sql);
            con.prepareCall("aaa");
            System.out.println("11111");
            Thread.sleep(2000);
        }
    }
}
