package fastdfsTest;

import fastdfs.FastDFSClientUpload;
import org.junit.Ignore;
import org.junit.Test;

/*
*   FastDFS测试类App
*   测试数据包大小:8k 128k 512k 1M
*
* */
public class fastdfsTestApp {
    @Ignore
    @Test
    public void TestFileUpload(){
        //文件上传测试
        FastDFSClientUpload tmpupload=new FastDFSClientUpload();
        FastDFSClientUpload.FastDFSClientUploadTest("fdfs_client.conf","8");
    }
}
