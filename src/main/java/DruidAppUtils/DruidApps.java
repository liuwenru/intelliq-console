package DruidAppUtils;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
public class DruidApps {
    private static Logger logger= LoggerFactory.getLogger(DruidApps.class);
    public static void main(String[] args) throws SQLException {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        DataSource dataSource = context.getBean("dataSource", DruidDataSource.class);
        Connection connection=dataSource.getConnection();
        Statement command= connection.createStatement();
        command.executeQuery("select 1");
        logger.debug("Success");
    }
}
