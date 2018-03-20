import fastdfs.FastDFSClientUpload;

/**
 * Created by ijarvis on 2017/5/17.
 */
public class AppTest {
    public static void main(String[] args){
        FastDFSClientUpload tmpupload=new FastDFSClientUpload();
        FastDFSClientUpload.FastDFSClientUploadTest("fdfs_client.conf","102400");

    }

}
