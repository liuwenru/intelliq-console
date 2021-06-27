package DruidApps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Apps {
    private static Logger logger = LoggerFactory.getLogger(Apps.class);

    public static void main(String[] args) throws SQLException, InterruptedException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        DataSource dataSource = (DataSource) context.getBean("dataSource");

//        PreparedStatement preparedStatement=connection.prepareStatement("/*!dble:db_type=slave*/ select * from sbtest6 where pad=?");
//        preparedStatement.setString(1,args[0]);
//        ResultSet resultSet= preparedStatement.executeQuery();
//        while (resultSet.next()){
//            logger.debug("  ----      {}  - {} - {} - {}  ---- ",
//                    resultSet.getString(1),
//                    resultSet.getString(2),
//                    resultSet.getString(3),
//                    resultSet.getString(4)
//            );
//        }
//        resultSet.close();

        int a = 5000;
        while (true) {
            Connection connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            a = a + 1;
            statement.execute("insert into b values(" + a + ",'1')");
            Thread.sleep(500);
            logger.debug("insert ...." + a);
            connection.commit();
            connection.close();
        }
//        connection.close();
    }
}