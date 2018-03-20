package fastdfs;


//上传文件

import org.apache.log4j.Logger;
import org.csource.common.MyException;
import org.csource.fastdfs.*;

import java.io.IOException;
import java.util.UUID;

public class uploadthenfileApps {
    private static final Logger logger=Logger.getLogger(uploadthenfileApps.class);
    public static void  main(String[] args) throws InterruptedException, IOException, MyException {

        String conf_filename=args[0];
        ClientGlobal.init(conf_filename);
        for (int i =0;i<10;i++){
            FileDownloader fileDownloader=new FileDownloader(UUID.randomUUID().toString());
            TrackerClient tracker = new TrackerClient();
            TrackerServer trackerServer = tracker.getConnection();
            StorageServer storageServer = null;
            StorageClient1 storageClient = new StorageClient1(trackerServer, storageServer);
            //byte[] filearrys = Files.readAllBytes(new File(args[1]).toPath());
            String[] fileid=storageClient.upload_file(args[1],args[1].split("\\.")[1],null);
            logger.debug("Group Name:"+fileid[0]+"   File Path:"+fileid[1]);
            Thread.sleep(Integer.parseInt(args[2]));

            TrackerClient trackernew = new TrackerClient();
            TrackerServer trackerServernew = trackernew.getConnection();
            StorageServer storageServernew = null;
            StorageClient1 storageClientnew = new StorageClient1(trackerServernew, storageServernew);
            storageClientnew.download_file1(fileid[0]+"/"+fileid[1],fileDownloader);
        }

    }
}


