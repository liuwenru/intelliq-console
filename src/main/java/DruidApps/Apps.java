package DruidApps;

import com.sun.org.apache.bcel.internal.generic.ACONST_NULL;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.*;

public class Apps {
    public static void main(String[] args) throws SQLException, InterruptedException {
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("application-context.xml");
        DataSource dataSource=(DataSource) context.getBean("dataSource");
        Connection connection=dataSource.getConnection();
        Statement statement=connection.createStatement();
        ResultSet resultSet= statement.executeQuery("select 1 from employees where employees.emp_no=11");

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }).start();
        //Statement statement1=connection.createStatement();
        //ResultSet resultSet1= statement1.executeQuery("select 1 from employees where employees.emp_no=11");
        Thread.sleep(1000*4);
        connection.close();

    }
}
