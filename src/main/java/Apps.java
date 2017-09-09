import ijarvis.fastdfs.FastDFSClientUpload;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.zip.GZIPInputStream;

/**
 * Created by ijarvis on 2017/5/17.
 实现FastDFS下载测试
 useage:

 java -jar xxxx.jar Test Test_Model client_config_file_path per_send_count[kb]
 Test_Model: 1-Test Upload   0-Test Download
 such as java -jar xxxx.jar 1 fdfs_client.conf 1024 this will upload 1024kb=1M file

 */
public class Apps {
    public static Logger logger=Logger.getLogger(Apps.class);
    public static void  main(String[] args) {
        try{
            if (args[0].equals("1")){
                // Test Upload........
                Socket client = new Socket("192.168.149.150", 22);
                client.getInputStream();
                logger.info("Test Upload ");
                //FastDFSClientUpload.FastDFSClientUploadTest(args[1],args[2]);
            }else {
                InputStream urlStream = new GZIPInputStream(null);
                BufferedReader reader = new BufferedReader(new InputStreamReader(urlStream,"gb2312"));
                String line = "";
                while((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
                logger.info("Test Download ");


            }

        }catch (Exception e){
            logger.info("Test FastDFS errot");
            logger.error("Test FastDFS errot",e);
        }


    }
}
