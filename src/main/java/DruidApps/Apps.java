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


        //connection.close();
        System.out.println("Stage 1 ...................");
        String SQLQUERY="insert into t1 values(?)";
        PreparedStatement preparedStatement= connection.prepareStatement(SQLQUERY);
        for(int i=0;i<5;i++){
            preparedStatement.setString(1,i+"");
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();

//        Statement cmd=connection.createStatement();
//        for(int i=0;i<5;i++){
//            cmd.addBatch("insert into t1 values("+i+")");
//        }
//        cmd.executeBatch();
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
