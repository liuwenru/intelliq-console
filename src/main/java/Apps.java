

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
        //String fileIds[] = storageClient.upload_file(local_filename, "png", null);
        //byte[] ArrayLetters = {0x61, 0x62, 0x63, 0x64, 0x65, 0x66, 0x67, 0x68, 0x69, 0x6A, 0x6B, 0x6C, 0x6D, 0x6E, 0x6F, 0x70, 0x71, 0x72, 0x73, 0x74, 0x75, 0x76, 0x77, 0x78, 0x79, 0x7A};
        byte probyte=0x61;
        byte[] bytes=new byte[Integer.parseInt(args[1])*1024];
        for (int i=0;i< Integer.parseInt(args[1])*1024;i++){
            bytes[i]=probyte;
        }
        int j=0;
        while (true){
            long start=System.currentTimeMillis();
            String[] fileid=storageClient.upload_file(bytes,"txt",null);
            logger.debug("finish "+(j++)+" push files "+fileid[0]+"/"+fileid[1]+" use :"+(System.currentTimeMillis()-start));
        }

        //System.out.println(fileIds.length);
        //System.out.println("组名：" + fileIds[0]);
        //System.out.println("路径: " + fileIds[1]);
    }
}
