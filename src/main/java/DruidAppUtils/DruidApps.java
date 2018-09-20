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
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        DataSource dataSource = context.getBean("dataSource", DruidDataSource.class);
        int i=0;
        while(true) {
            logger.debug("----------------开始获取连接---------------");
            Connection connection = dataSource.getConnection();
            PreparedStatement comm = connection.prepareStatement("select * from frame_attachinfo");
            comm.executeQuery();
            logger.debug("Success");
            if((i%2)==0){
                connection.close();
                logger.debug("----------------关闭获取到连接---------------");
            }
            i=i+1;
            Thread.sleep(4000);
        }
    }
}