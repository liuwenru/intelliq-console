package DruidAppUtils;

import java.sql.*;

public class MySQLApps {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/tuiliu?useSSL=true&verifyServerCertificate=false","root","Gepoint");
        Statement command=connection.createStatement();
        ResultSet resultSet=command.executeQuery("select * from frame_user");
        while (resultSet.next()){
            System.out.println(resultSet.getString(1)+"===="+resultSet.getString(2));
        }
    }
}
