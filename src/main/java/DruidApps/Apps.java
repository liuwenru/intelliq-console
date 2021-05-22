package DruidApps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.*;

public class Apps {
    private  static Logger logger= LoggerFactory.getLogger(Apps.class);
    public static void main(String[] args) throws SQLException, InterruptedException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        DataSource dataSource = (DataSource) context.getBean("dataSource");


        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement("select * from sbtest6 where pad=?");
        preparedStatement.setString(1,args[0]);
        ResultSet resultSet= preparedStatement.executeQuery();
        while (resultSet.next()){
            logger.debug("  ----      %s  - %s - %s - %s  ---- ",
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
        }
        resultSet.close();
        connection.close();
    }
}
