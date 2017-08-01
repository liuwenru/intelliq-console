import org.apache.log4j.Logger;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.junit.Ignore;
import org.junit.Test;

import java.io.*;
import java.net.InetSocketAddress;

/**
 * Created by ijarvis on 2017/5/17.
 */
public class AppTest {
    public static Logger logger=Logger.getLogger(AppTest.class);
    String conf_filename="/Users/ijarvis/IdeaProjects/intelliq-console/src/main/resources/fdfs_client.conf";
    String local_filename="/Users/ijarvis/IdeaProjects/intelliq-console/src/main/resources/images/images1.jpg";
    @Ignore
    @Test
    public void TestUploadFiles() throws IOException, MyException {
        ClientGlobal.init(conf_filename);
        TrackerClient tracker = new TrackerClient();
        TrackerServer trackerServer = tracker.getConnection();
        StorageServer storageServer = null;
        StorageClient storageClient = new StorageClient(trackerServer, storageServer);
        String fileIds[] = storageClient.upload_file(local_filename, "png", null);
        logger.debug(fileIds.length);
        logger.debug("组名：" + fileIds[0]);
        logger.debug("路径: " + fileIds[0]+"/"+fileIds[1]);
    }
    @Ignore
    @Test
    public void TestUploadMethod2() throws IOException, MyException {
        ClientGlobal.init(conf_filename);
        TrackerClient tracker = new TrackerClient();
        TrackerServer trackerServer = tracker.getConnection();
        StorageServer storageServer = null;
        StorageClient1 storageClient1 = new StorageClient1(trackerServer, storageServer);
        String fileIds = storageClient1.upload_file1(local_filename, "png", null);
        logger.debug("文件ID:"+fileIds);
        byte[] result = storageClient1.download_file1(fileIds);
        File file=new File("/Users/ijarvis/Downloads/aa.png");
        OutputStream fileoutput=new FileOutputStream(file);
        fileoutput.write(result);
        fileoutput.close();
    }
    @Test
    public void TestTracker() throws IOException, MyException {
        //测试一下基本的获取一些关于存储的信息相关
        InetSocketAddress[] inetSocketAddresses=new InetSocketAddress[1];
        inetSocketAddresses[0]=new InetSocketAddress("192.168.210.2",22122);
        //inetSocketAddresses[1]=new InetSocketAddress("192.168.210.3",22122);
        TrackerGroup tg=new TrackerGroup(inetSocketAddresses);
        TrackerClient tc=new TrackerClient(tg);
        TrackerServer ts=tc.getConnection();
        String groupname=null;
        StorageServer[] storageServers=tc.getStoreStorages(ts,null);
        if (storageServers == null) {
            logger.debug("get store storage servers fail, error code: " + tc.getErrorCode());
        } else {
            logger.debug("store storage servers count: " + storageServers.length);
            for (int k = 0; k < storageServers.length; k++) {
                logger.debug((k + 1) + ". " + storageServers[k].getInetSocketAddress().getAddress().getHostAddress() + ":" + storageServers[k].getInetSocketAddress().getPort());
            }
            logger.debug("");
        }
        logger.debug("connection Success...........");
    }
}
