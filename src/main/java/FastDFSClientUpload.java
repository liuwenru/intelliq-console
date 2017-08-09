/*
    实现FastDFS上传测试
    useage:
        java -jar xxxx.jar client_config_file_path per_send_count[kb]
        such as java -jar xxxx.jar fdfs_client.conf 1024 this will upload 1024kb=1M file
*/


import org.apache.log4j.Logger;
import org.csource.fastdfs.*;

public class FastDFSClientUpload {
    public static Logger logger=Logger.getLogger(FastDFSClientUpload.class);
    public static void FastDFSClientUploadTest(String configfilepath,String count){
        try {
            String conf_filename=configfilepath;
            ClientGlobal.init(conf_filename);
            TrackerClient tracker = new TrackerClient();
            TrackerServer trackerServer = tracker.getConnection();
            StorageServer storageServer = null;
            StorageClient storageClient = new StorageClient(trackerServer, storageServer);
            byte probyte=0x61;
            byte[] bytes=new byte[Integer.parseInt(count)*1024];
            for (int i=0;i< Integer.parseInt(count)*1024;i++){
                bytes[i]=probyte;
            }
            int j=0;
            while (true){
                long start=System.currentTimeMillis();
                String[] fileid=storageClient.upload_file(bytes,"txt",null);
                long end=System.currentTimeMillis();
                logger.debug("upload" + (Integer.parseInt(count)*1024)/((end-start)/1.0)/1000+"Mb/s");
                j++;
            }
            //System.out.println(fileIds.length);
            //System.out.println("组名：" + fileIds[0]);
            //System.out.println("路径: " + fileIds[1]);

        }catch (Exception e){
            logger.error("Upload file Error.............",e);

        }
    }



}
