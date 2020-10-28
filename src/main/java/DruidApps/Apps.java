package DruidApps;

import com.sun.org.apache.bcel.internal.generic.ACONST_NULL;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Apps {
    public static void main(String[] args) throws SQLException, InterruptedException {
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("application-context.xml");
        DataSource dataSource=(DataSource) context.getBean("dataSource");


//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Connection connection1= dataSource.getConnection();
//                    Thread.sleep(10000*100);
//                } catch (SQLException | InterruptedException throwables) {
//                    throwables.printStackTrace();
//                }
//            }
//        }).start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    for (;;){
//                        Connection connection1= dataSource.getConnection();
//                        Thread.sleep(500);
//                    }
//                } catch (SQLException | InterruptedException throwables) {
//                    throwables.printStackTrace();
//                }
//            }
//        }).start();
        Connection connection=dataSource.getConnection();


        connection.close();
        System.out.println("Stage 1 ...................");


        //connection.createStatement().executeQuery("select 1");


        System.out.println("Stage 2 ...................");

//        Connection connection=dataSource.getConnection();
//        connection=null;
//        Thread.sleep(4000);
//        connection=dataSource.getConnection();
//        ResultSet set=connection.createStatement().executeQuery("select * from  test");

        System.out.println("Stage 3 ...................");

//        while (set.next()){
//            System.out.println("Stage 4 ...................");
//        }

        System.out.println("Stage 5 ...................");



    }
}
