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
    }
}
