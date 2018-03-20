package fastdfs;
/*
*  实现FastDFS的下载测试
*
*
* */

import org.apache.log4j.Logger;
import org.csource.common.MyException;
import org.csource.fastdfs.*;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

public class FastDFSClientDownload implements DownloadCallback {
    public static Logger logger=Logger.getLogger(FastDFSClientDownload.class);
    @Override
    public int recv(long l, byte[] bytes, int i) {
        return 0;
    }

    public static void main(String[] args) throws IOException, MyException {
        FileDownloader fileDownloader=new FileDownloader(UUID.randomUUID().toString());
        String file_ids="";
        ClientGlobal.init(args[0]);
        TrackerClient tracker = new TrackerClient();
        TrackerServer trackerServer = tracker.getConnection();
        StorageServer storageServer = null;
        StorageClient1 storageClient = new StorageClient1(trackerServer, storageServer);
        logger.debug("");
        while (true){
            int downloadstatus=storageClient.download_file1(args[1],fileDownloader);
            if (downloadstatus!=0){
                return;
            }
        }
    }
}

class FileDownloader implements DownloadCallback{
    public static Logger logger=Logger.getLogger(FileDownloader.class);
    private String filename;
    private FileOutputStream out = null;
    private BufferedOutputStream bufferedOutputStream=null;
    private long current_bytes = 0;
    public FileDownloader(String filename) {
        this.filename = filename;
    }
    @Override
    public int recv(long file_size, byte[] data, int bytes) {
        //logger.debug("callback "+file_size +"***"+data.length+"***"+bytes);
        try{
            //logger.debug("正在进行下载！");
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
