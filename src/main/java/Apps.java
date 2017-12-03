import org.apache.log4j.Logger;

import java.sql.*;

public class Apps {
    private static Logger logger=Logger.getLogger(Apps.class);
    public static void  main(String[] args) throws SQLException {
        String JDBCURL=args[0];
        String SQL=args[1];
        logger.debug("JDBCURL :"+JDBCURL);
        logger.debug("SQL :"+ SQL);
        Connection conn = DriverManager.getConnection(JDBCURL);
        Statement command=conn.createStatement();
        ResultSet dataset= command.executeQuery(SQL);
        while (dataset.next()){
            logger.debug(dataset.getString(1));
        }
        conn.close();
    }
}
