package MySQLUtils;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.sql.*;

public class MySQLTestApps {
//    private static Logger logger = LoggerFactory.getLogger(MySQLTestApps.class);

    public static void main(String[] args) throws ClassNotFoundException, SQLException, InterruptedException {
        Class.forName("com.mysql.jdbc.Driver");
        while (true) {
            Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.188.149/jumpserver?user=root&password=Gepoint");
            String SQL = "select * from users_user";
            Statement com = conn.createStatement();
            ResultSet resultSet = com.executeQuery(SQL);
            while (resultSet.next()) {
//                logger.debug(resultSet.getString(1));
            }
            //Thread.sleep(4000);
        }
    }
}