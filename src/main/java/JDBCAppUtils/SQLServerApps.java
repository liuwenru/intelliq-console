package JDBCAppUtils;

import java.sql.*;

public class SQLServerApps {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String hostname="192.168.212.243";
        String connectionUrl = "jdbc:sqlserver://"+hostname+";" +
                        "databaseName=EpointSJ;" +
                        "encrypt=true;trustServerCertificate=true";
        String dbname="sa";
        String dbpass="Gepoint";
        Connection connection= DriverManager.getConnection(connectionUrl,dbname,dbpass);
        Statement command=connection.createStatement();
        ResultSet resultSet= command.executeQuery("select * from SJ_Salary");
        while (resultSet.next()){
            System.out.println(resultSet.getString(1));
        }
        //System.out.println(rest);
    }
}
