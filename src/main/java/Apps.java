import fastdfs.FastDFSClientUpload;
import org.apache.log4j.Logger;

public class Apps {
    private static  final Logger logger= Logger.getLogger(Apps.class);
    public static void  main(String[] args) throws InterruptedException {
        logger.debug("测试文件上传操作");
        //FastDFSClientUpload uploader=new FastDFSClientUpload();
        //FastDFSClientUpload.FastDFSClientUploadTest(args[0],args[1]);
        int threadcount=Integer.parseInt(args[0]);
        uploadthread[] threads=new uploadthread[threadcount];
        for (int i =0;i< threadcount;i++){
            threads[i]=new uploadthread(args[1],args[2]);
        }
        for (int i =0;i< threadcount;i++){
            threads[i].run();
        }
    }
}
class  uploadthread implements Runnable{
    private String configurefile="";
    private String count="";
    public uploadthread(String configurefile,String count){
        this.configurefile=configurefile;
        this.count=count;
    }
    @Override
    public void run() {
        FastDFSClientUpload uploader=new FastDFSClientUpload();
        FastDFSClientUpload.FastDFSClientUploadTest(this.configurefile,this.count);
    }
}