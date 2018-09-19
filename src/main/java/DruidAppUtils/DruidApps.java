package DruidAppUtils;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class DruidApps {
    private static Logger logger= LoggerFactory.getLogger(DruidApps.class);
    public static void main(String[] args) throws SQLException, InterruptedException {
        while(true) {
            ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
            DataSource dataSource = context.getBean("dataSource", DruidDataSource.class);
            Connection connection = dataSource.getConnection();
            PreparedStatement comm = connection.prepareStatement("select * from frame_attachinfo");
            comm.executeQuery();
            //command.executeQuery("select 1");
            logger.debug("Success");
            connection.close();
            Thread.sleep(4000);
        }
    }
}
