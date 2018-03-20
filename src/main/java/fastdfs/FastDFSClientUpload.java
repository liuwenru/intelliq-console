package fastdfs;/*
    实现FastDFS上传测试
    useage:
        java -jar xxxx.jar client_config_file_path per_send_count[kb]
        such as java -jar xxxx.jar fdfs_client.conf 1024 this will upload 1024kb=1M file
*/


import org.apache.log4j.Logger;
import org.csource.fastdfs.*;

public class FastDFSClientUpload {
    public static Logger logger=Logger.getLogger(FastDFSClientUpload.class);
//    public static String CONF_FILENAME="/Users/ijarvis/IdeaProjects/intelliq-console/src/main/resources/fdfs_client.conf";
//    public static TrackerClient tracker=new TrackerClient();
//    public static TrackerServer trackerServer;
//    public static StorageServer storageServer = null;
//    public static StorageClient storageClient = new StorageClient(trackerServer, storageServer);
//    static {
//        try {
//            logger.debug("正在初始化上传模块删除属性...............................");
//            ClientGlobal.init(CONF_FILENAME);
//            trackerServer = tracker.getConnection();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (MyException e) {
//            e.printStackTrace();
//        }
//    }




    public static void FastDFSClientUploadTest(String configfilepath,String count){
        try {
            logger.debug("AAAAAAAA");
            String conf_filename=configfilepath;
            ClientGlobal.init(conf_filename);
            TrackerClient tracker = new TrackerClient();
            TrackerServer trackerServer = tracker.getConnection();
            StorageServer storageServer = null;
            StorageClient storageClient = new StorageClient(trackerServer, storageServer);
            byte probyte=0x61;
            int bytecount=Integer.parseInt(count)*1024;
            byte[] bytes=new byte[bytecount];
            for (int i=0;i< bytecount;i++){
                bytes[i]=probyte;
            }
            int j=0;
            while (true){
                long start=System.currentTimeMillis();
                String[] fileid=storageClient.upload_file(bytes,"txt",null);
                long end=System.currentTimeMillis();
                logger.debug("upload" + (Integer.parseInt(count)*1024)/((end-start)/1.0)/1000+"MB/s");
                j++;
                logger.debug("Group Name:"+fileid[0]+"   File Path:"+fileid[1]);
            }
        }catch (Exception e){
            logger.error("Upload file Error.............",e);

        }
    }
}
