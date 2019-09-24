package DruidAppUtils;

import java.sql.*;

public class MySQLApps {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useSSL=true&verifyServerCertificate=false&allowMultiQueries=true","root","Gepoint");
        Statement command=connection.createStatement();
        command.executeQuery("create table test.a_DESH7T1909172158_eold like test.a;insert into test.a_DESH7T1909172158_eold select * from test.a where a=1;delete from test.a where a=1;");
        //System.out.println(rest);
    }
}
