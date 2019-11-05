package JDBCAppUtils;

import java.sql.*;

public class MySQLAppsUtils {
    public static void  main(String[] args){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            //conn = DriverManager.getConnection("jdbc:mysql://192.168.188.181/test?user=root&password=Infra5@Gep0int");
            conn = DriverManager.getConnection("jdbc:mysql://192.168.188.154/test123?user=root&password=Gepoint@2019");
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM t");
            while (rs.next()){
                System.out.println(rs.getString(1));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }

}
