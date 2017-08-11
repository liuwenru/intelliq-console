package ijarvis.fastdfs;
/*
*  实现FastDFS的下载测试
*
*
* */

import org.apache.log4j.Logger;
import org.csource.common.MyException;
import org.csource.fastdfs.*;

import java.io.FileOutputStream;
import java.io.IOException;

public class FastDFSClientDownload implements DownloadCallback {
    public static Logger logger=Logger.getLogger(FastDFSClientDownload.class);
    @Override
    public int recv(long l, byte[] bytes, int i) {
        return 0;
    }


    public static void main(String[] args) throws IOException, MyException {
        FileDownloader fileDownloader=new FileDownloader("liuwenru.jpg");
        String file_ids="";
        ClientGlobal.init("/Users/ijarvis/IdeaProjects/intelliq-console/src/main/resources/fdfs_client.conf");
        TrackerClient tracker = new TrackerClient();
        TrackerServer trackerServer = tracker.getConnection();
        StorageServer storageServer = null;
        StorageClient1 storageClient = new StorageClient1(trackerServer, storageServer);
        int downloadstatus=storageClient.download_file1("group1/M00/00/00/wKiVsVmL_6KAdYOhAAVL7qJPaKo917.jpg",fileDownloader);
        if (downloadstatus!=0){
            return;
        }
    }
}
class FileDownloader implements DownloadCallback{
    public static Logger logger=Logger.getLogger(FileDownloader.class);
    private String filename;
    private FileOutputStream out = null;
    private long current_bytes = 0;
    public FileDownloader(String filename) {
        this.filename = filename;
    }
    @Override
    public int recv(long file_size, byte[] data, int bytes) {
        logger.debug("callback "+file_size +"***"+data.length+"***"+bytes);
        try{
            if (this.out == null) {
                this.out = new FileOutputStream(this.filename);
            }
            this.out.write(data, 0, bytes);
            if (this.current_bytes == file_size) {
                this.out.close();
                this.out = null;
                this.current_bytes = 0;
            }
            return 0;
        }catch (Exception e){
            logger.error("Error",e);
            return 1;
        }

    }
}
