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
    private static ApplicationContext context ;
    private static DataSource dataSource ;

    public static void main(String[] args) throws Exception {
        context=new ClassPathXmlApplicationContext("file:///home/ijarvis/workspace/javaWorkSpace/intelliq-console/src/main/resources/application-context.xml");
        dataSource=context.getBean("dataSource", DruidDataSource.class);

//        int size=50;
//        Threadepoint[] threadepoints=new Threadepoint[size];
//        for(int i=0;i<size;i++){
//            threadepoints[i]=new Threadepoint(dataSource);
//            threadepoints[i].start();
//        }
//        while(true){
//
//        }
        Connection connection= dataSource.getConnection();
        Statement command=connection.createStatement();
        ResultSet resultSet= command.executeQuery("select * from SJ_Salary");
        while (resultSet.next()){
            System.out.println(resultSet.getString(1));
        }
    }

}
