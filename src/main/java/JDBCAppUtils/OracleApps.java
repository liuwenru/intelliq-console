package JDBCAppUtils;

import java.sql.*;

public class OracleApps {

    public static void main(String[] args) {
        a c=new a();
        try (Connection conn = DriverManager.getConnection(
                "jdbc:oracle:thin:@192.168.212.111:1521:orcl", "admin", "admin123")) {
            if (conn != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
            }
            while (true){
                Statement cmd=conn.createStatement();
                ResultSet resultSet= cmd.executeQuery("UPDATE EPOINTTEST.MYTEST set AGE=2222   WHERE EPOINTTEST.MYTEST.\"NAME\"='12'");


                System.out.println(resultSet.next());
                Thread.sleep(5*1000);
                c.print("liuwenru");
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
class  a{
    public void print(String a){
        System.out.println(a);
    }
}
