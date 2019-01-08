package MySQLUtils;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.sql.*;
import java.util.UUID;

import static MySQLUtils.dataRandTest.randomDate;

public class MySQLTestApps {
//    private static Logger logger = LoggerFactory.getLogger(MySQLTestApps.class);

    public static void main(String[] args) throws ClassNotFoundException, SQLException, InterruptedException {
        Class.forName("com.mysql.jdbc.Driver");
        int productnumber=Integer.parseInt(args[2]);
        dbaleProducer[] dbarry=new dbaleProducer[productnumber];
        for (int i=0;i<productnumber;i++){
            dbarry[i]=new dbaleProducer(args);
            dbarry[i].start();
        }
        for (int i=0;i<productnumber;i++){
            dbarry[i]=new dbaleProducer(args);
            dbarry[i].join();
        }

        System.out.println("End.........");

    }


}

class  dbaleProducer extends  Thread{
    public String[] getDruiration() {
        return druiration;
    }

    public void setDruiration(String[] druiration) {
        this.druiration = druiration;
    }

    public String[] druiration=new String[2];

    public dbaleProducer(String[] args){
        this.setDruiration(args);
    }
    @Override
    public void run() {
        try {
            System.out.println("Satrting");
            insertdata(druiration);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static  void insertdata(String[] args) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.186.231:8066/testdb?user=root&password=123456");
        String SQL = "select * from epointbigtable limit 5000";
        Statement com = conn.createStatement();
        ResultSet resultSet = com.executeQuery(SQL);
        String INSERTSQL="INSERT INTO `epointbigtable`(RowGuid,SYNC_SIGN,SYNC_ERROR_DESC,OperateType,SYNC_Date,xgbz,mxxmlb,tfbz,yzxmlxdm,bxlx,mxfylb,zjlx,jzlxdm,klx,mj,zxksbm,jzksdm,yljgdm,zjhm,mxxmgg,MXXMWJJBM,cfh,MXXMYBBM,fph,scph,sfmxlsh,zxdm,kh,mxxmdw,sfczyxm,JZLSH,yzxmlxmc,kfysxm,xm,sflsh,sfczygh,jclsh,kfysgh,zxksmc,jzksmc,mxxmbm,MXXMMC,mxxmje,yxq,tbrq,mxxmsl,mxxmdj,stfsj) VALUES (?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(INSERTSQL);
        while (resultSet.next()){
            for (int j=0;j<100000;j++){
                java.util.Random r=new java.util.Random();
                ps.setString(1, UUID.randomUUID().toString());
                ps.setString(2,resultSet.getString(2));
                ps.setString(3, resultSet.getString(3));
                ps.setString(4,resultSet.getString(4));
                ps.setString(5, resultSet.getString(5));
                ps.setString(6,resultSet.getString(6));
                ps.setString(7, resultSet.getString(7));
                ps.setString(8,resultSet.getString(8));
                ps.setString(9, resultSet.getString(9));
                ps.setString(10,resultSet.getString(10));
                ps.setString(11, resultSet.getString(11));
                ps.setString(12,resultSet.getString(12));
                ps.setString(13, resultSet.getString(13));
                ps.setString(14,resultSet.getString(14));
                ps.setString(15,resultSet.getString(15));
                ps.setString(16, resultSet.getString(16));
                ps.setString(17,resultSet.getString(17));
                ps.setString(18, resultSet.getString(18));
                ps.setString(19,resultSet.getString(19));
                ps.setString(20, resultSet.getString(20));
                ps.setString(21,resultSet.getString(21));
                ps.setString(22, resultSet.getString(22));
                ps.setString(23,resultSet.getString(23));
                ps.setString(24, resultSet.getString(24));
                ps.setString(25,resultSet.getString(25));
                ps.setString(26, resultSet.getString(26));
                ps.setString(27,resultSet.getString(27));
                ps.setString(28, resultSet.getString(28));
                ps.setString(29,resultSet.getString(29));
                ps.setString(30, resultSet.getString(30));
                ps.setString(31,resultSet.getString(31));
                ps.setString(32, resultSet.getString(32));
                ps.setString(33,resultSet.getString(33));
                ps.setString(34, resultSet.getString(34));
                ps.setString(35,resultSet.getString(35));
                ps.setString(36, resultSet.getString(36));
                ps.setString(37,resultSet.getString(37));
                ps.setString(38, resultSet.getString(38));
                ps.setString(39,resultSet.getString(39));
                ps.setString(40,resultSet.getString(40));
                ps.setString(41,resultSet.getString(41));
                ps.setString(42,resultSet.getString(42));
                ps.setString(43,resultSet.getString(43));
                ps.setString(44,resultSet.getString(44));
                ps.setString(45,randomDate(args[0], args[1]));// tbrq字段
                ps.setString(46,resultSet.getString(46));
                ps.setString(47,resultSet.getString(47));
                ps.setString(48,resultSet.getString(48));
                ps.addBatch();
            };
            ps.executeBatch();
            System.out.println("插入100000 SSUCCESS！");
        }
    }
}