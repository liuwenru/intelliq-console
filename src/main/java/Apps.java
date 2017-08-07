

import org.apache.log4j.Logger;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;

import java.io.*;

/**
 * Created by ijarvis on 2017/5/17.
 */
public class Apps {
    public static Logger logger=Logger.getLogger(Apps.class);
    public static void  main(String[] args) throws IOException, MyException {
        //"/Users/ijarvis/IdeaProjects/intelliq-console/src/main/resources/fdfs_client.conf"
        String conf_filename=args[0];
        String local_filename="/Users/ijarvis/IdeaProjects/intelliq-console/src/main/resources/images/aliyun120.jpg";
        ClientGlobal.init(conf_filename);
        TrackerClient tracker = new TrackerClient();
        TrackerServer trackerServer = tracker.getConnection();
        StorageServer storageServer = null;
        StorageClient storageClient = new StorageClient(trackerServer, storageServer);
        byte probyte=0x61;
        byte[] bytes=new byte[Integer.parseInt(args[1])*1024];
        for (int i=0;i< Integer.parseInt(args[1])*1024;i++){
            bytes[i]=probyte;
        }
        int j=0;
        while (true){

            long start=System.currentTimeMillis();
            String[] fileid=storageClient.upload_file(bytes,"txt",null);
            long end=System.currentTimeMillis();
            logger.debug("upload" + (Integer.parseInt(args[1])*1024)/((end-start)/1.0)/1000+"Mb/s");
            j++;
        }
        //System.out.println(fileIds.length);
        //System.out.println("组名：" + fileIds[0]);
        //System.out.println("路径: " + fileIds[1]);
    }
}
