
//import org.soulwing.crypt4j.Crypt;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.Crypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Apps {
    private static Logger logger=LoggerFactory.getLogger(Apps.class);
    public  static void main(String[] args) throws InterruptedException, IOException, NoSuchAlgorithmException, SQLException {
        Connection conn = null;
        String className = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@221.226.184.178:7521:orcl";
        String username = "NJJBXQ_DJGBZ";
        String password = "11111";

        try {
            //加载驱动
            Class.forName(className);
            System.out.println("加载驱动成功！");
        }
        catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            System.out.println("加载驱动失败！");
        }

        try {
            //连接
            conn = DriverManager.getConnection(url,username,password);
            System.out.println(conn);
            System.out.println("数据库连接成功");
        }
        catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            System.out.println("数据库连接失败");
        }


        ResultSet rest= conn.createStatement().executeQuery("select 1 from dual");
        while (rest.next()){
            System.out.println(rest.getString(1));
        }
    }
}
