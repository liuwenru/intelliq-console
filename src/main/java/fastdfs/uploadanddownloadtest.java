package fastdfs;

import org.apache.log4j.Logger;
import org.csource.common.MyException;
import org.csource.fastdfs.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class uploadanddownloadtest {
    private static final Logger logger=Logger.getLogger(uploadanddownloadtest.class);
    public static ArrayList<String> filedownloadurl=new ArrayList<>();
    public static String conf_filename="";
    public static String uploadfilename="";
    public static void  main(String[] args) throws InterruptedException, IOException, MyException {

        conf_filename=args[0];
        uploadfilename=args[1];
        for (int i =0;i<20;i++){
            new uploadthread().start();
        }
        Thread.sleep(50000);

    }
}

class uploadthread extends Thread{
    private static final Logger logger=Logger.getLogger(uploadthread.class);
    @Override
    public void run() {
        try {
            ClientGlobal.init(uploadanddownloadtest.conf_filename);
            FileDownloader fileDownloader=new FileDownloader(UUID.randomUUID().toString());
            TrackerClient tracker = new TrackerClient();
            TrackerServer trackerServer = tracker.getConnection();
            StorageServer storageServer = null;
            StorageClient1 storageClient = new StorageClient1(trackerServer, storageServer);
            //byte[] filearrys = Files.readAllBytes(new File(args[1]).toPath());
            String[] fileid=storageClient.upload_file(uploadanddownloadtest.uploadfilename,uploadanddownloadtest.uploadfilename.split("\\.")[1],null);
            logger.debug("Group Name:"+fileid[0]+"   File Path:"+fileid[1]);
            uploadanddownloadtest.filedownloadurl.add(fileid[0]+"/"+fileid[1]);
            new downloadthread(fileid[0]+"/"+fileid[1]).start();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }



    }
}
class downloadthread extends Thread{
    private static final Logger logger=Logger.getLogger(downloadthread.class);
    private String downloadurl;
    public downloadthread(String downloadurl){
        this.downloadurl=downloadurl;
    }
    @Override
    public void run() {

        try {
            ClientGlobal.init(uploadanddownloadtest.conf_filename);
            FileDownloader fileDownloader=new FileDownloader(UUID.randomUUID().toString());
            TrackerClient trackernew = new TrackerClient();
            TrackerServer trackerServernew = null;
            trackerServernew = trackernew.getConnection();
            StorageServer storageServernew = null;
            StorageClient1 storageClientnew = new StorageClient1(trackerServernew, storageServernew);
            storageClientnew.download_file1(downloadurl,fileDownloader);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }


    }
}
