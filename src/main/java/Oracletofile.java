import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class Oracletofile {

    public static  void main(String args[]) throws ClassNotFoundException, SQLException, IOException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        String url = "jdbc:oracle:" + "thin:@192.168.186.36:1521:orcl";
        String user = "epoint";
        String password = "11111";
        Connection con = DriverManager.getConnection(url, user, password);
        System.out.println("连接成功！");
        String sql = "select * from CAR_HAILING2";
        PreparedStatement pre = con.prepareStatement(sql);
        ResultSet result = pre.executeQuery();
        File file = new File("data.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        int i=0;
        while (result.next()){
            i=i+1;
            bufferedWriter.write(result.getString(1)+","+result.getString(2)+","+result.getString(3));
            bufferedWriter.newLine();// 表示换行
            if (i>=10000000){
                break;
            }
        }
        bufferedWriter.flush();// 清空缓冲区
        bufferedWriter.close();// 关闭输出流
    }
}
