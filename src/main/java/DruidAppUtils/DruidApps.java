package DruidAppUtils;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.Jedis;

import javax.sql.DataSource;
import java.sql.*;

public class DruidApps {
    private static Logger logger= LoggerFactory.getLogger(DruidApps.class);
    public static void main(String[] args) throws SQLException, InterruptedException {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        DataSource dataSource = context.getBean("dataSource", DruidDataSource.class);
        Connection connection=dataSource.getConnection();
        Statement command=connection.createStatement();
        //Thread.sleep(60*1000);
        Jedis jedis=new Jedis("192.168.188.75",7001);
        jedis.auth("Gepoint");
        long start= System.currentTimeMillis();
        while (true){
            jedis.set("epointkey","1111111");
            long end=System.currentTimeMillis();
            if ((end-start)/1000.0> 60){
                break;
            }
        }
        ResultSet dataset=command.executeQuery("select * from titles limit 10");
        while (dataset.next()){
            logger.debug(dataset.getString(2));
        }
    }
}