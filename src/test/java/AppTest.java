import org.junit.Test;

import java.sql.SQLException;

/**
 * Created by ijarvis on 2017/5/17.
 */
public class AppTest {
    @Test
    public void testPrintMessage() throws SQLException, ClassNotFoundException {
        MySQLUtils tmp=new MySQLUtils("192.168.149.160","3306","orange","root","liuwenru");
        tmp.checkMySQL("dashboard_user");
    }
}
