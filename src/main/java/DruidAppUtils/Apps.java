package DruidAppUtils;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.*;

public class Apps {
    private static Logger logger= LoggerFactory.getLogger(Apps.class);
    public static void main(String[] args) throws SQLException, InterruptedException {
        ApplicationContext context = new ClassPathXmlApplicationContext(args[0]);
        DataSource dataSource = context.getBean("dataSource", DruidDataSource.class);
        Connection connection=dataSource.getConnection();
        Statement command=connection.createStatement();
        ResultSet resultSet=command.executeQuery("select * from frame_user");
        while (resultSet.next()){
            System.out.println(resultSet.getString(1)+"===="+resultSet.getString(2));
        }
    }
}