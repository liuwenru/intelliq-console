package dbleAppUtils;

import DruidAppUtils.DruidApps;
import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Apps {
    private static Logger logger= LoggerFactory.getLogger(DruidApps.class);
    public static  void main(String[] args) throws SQLException {
        ApplicationContext context = new ClassPathXmlApplicationContext("file:///root/workspace/javaworkspace/intelliq-console/src/main/resources/application-context.xml");
        DataSource dataSource = context.getBean("dataSource", DruidDataSource.class);
        Connection connection=dataSource.getConnection();
        Statement command=connection.createStatement();

        for (int i =0;i<10000;i++){
            command.execute("insert into travelrecord(id,name) values ("+i+",'ijarvis"+i+"')");

        }
        ResultSet dataset=command.executeQuery("select * from travelrecord");
        while (dataset.next()){
            System.out.println(dataset.getString(1)+"    "  + dataset.getString(2) );
            logger.debug(dataset.getString(1)+"    "  + dataset.getString(2) );
        }
    }
}
