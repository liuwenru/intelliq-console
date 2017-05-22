import java.sql.*;

/**
 * Created by ijarvis on 2017/5/22.
 */
public class MySQLUtils {
    private static Connection sqlconn=null;
    public MySQLUtils(String host,String port,String dbname ,String user ,String pass) throws ClassNotFoundException, SQLException {
        String jdbcurl="jdbc:mysql://"+host+":"+port+"/"+dbname+"?user="+user+"&password="+pass+"&useUnicode=true&characterEncoding=UTF8";
        Class.forName("com.mysql.jdbc.Driver");
        if (MySQLUtils.sqlconn ==null ){
            MySQLUtils.sqlconn= DriverManager.getConnection(jdbcurl);
        }
    }
    public  int checkMySQL(String tablename) throws SQLException {
        Statement stmt = MySQLUtils.sqlconn.createStatement();
        ResultSet rest=stmt.executeQuery("select * from "+tablename );
        while (rest.next()){
            String out=String.format(tablename+"{cloum %s}--"+"%s",rest.getString(1),rest.getString(2));
            System.out.println(out);
        }
        return 1;
    }
}
