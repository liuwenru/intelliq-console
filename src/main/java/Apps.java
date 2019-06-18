import FileApps.EncodingDetect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import org.apache.commons.io.FileUtils;
public class Apps {
    private static Logger logger=LoggerFactory.getLogger(Apps.class);
    public  static void main(String[] args) throws InterruptedException, IOException, NoSuchAlgorithmException, SQLException {
//        String filePath="/home/ijarvis/fileutf8.txt";
//        String fileEncode= EncodingDetect.getJavaEncode(filePath);
//        String fileContent=FileUtils.readFileToString(new File(filePath),fileEncode);
//        logger.debug("fileEncode:"+fileEncode);
//        logger.debug("fileContent:"+fileContent);
        String filePath=args[0];
        String localfile=new String(filePath.getBytes("gbk"),"utf-8");
        String fileContent=FileUtils.readFileToString(new File(filePath));
        logger.debug(fileContent);
    }
}
