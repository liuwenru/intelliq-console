/**
 * 实现FastDFS 性能测试
 **/

package fastdfs;
import org.csource.fastdfs.*;

/**
 * @author ijarvis
 */
public class TestFDSApps {
  public static org.apache.log4j.Logger logger= org.apache.log4j.Logger.getLogger(TestFDSApps.class);
  public static java.util.concurrent.ConcurrentLinkedQueue file_ids; //存储上传成功的file_ids
  public static int total_download_count = 0;
  public static int success_download_count = 0;
  public static int fail_download_count = 0;
  public static int total_upload_count = 0;
  public static int success_upload_count = 0;
  public static int upload_thread_count = 0;
  public static String ConfigFilePath="";
  public static int uploadsize=1;//单个上传文件的大小 单位kb
  public static byte[] file_buff;
  private TestFDSApps() {
  }
  public static void main(String args[]) {
    file_buff = new byte[uploadsize * 1024];
    java.util.Arrays.fill(file_buff, (byte) 65);
    if (args.length < 3) {
      logger.error("Error: Must have 1 parameter: config filename");
      logger.info("Error , Exit");
      return;
    }
    ConfigFilePath=args[0].toLowerCase();
    uploadsize=Integer.parseInt(args[1]);
    String TestModel=args[2];
    logger.info("java.version=" + System.getProperty("java.version"));
    try {
      ClientGlobal.init(ConfigFilePath);
      logger.info("network_timeout=" + ClientGlobal.g_network_timeout + "ms");
      logger.info("charset=" + ClientGlobal.g_charset);
      file_ids = new java.util.concurrent.ConcurrentLinkedQueue();
      if (TestModel.equals("upload")){
          for (int i = 0; i < 10; i++) {
              (new UploadThread(i)).start();
          }
      }else {
          logger.debug("Begain Test File Download........");
          logger.info("Prepare file  ids.........");
          for (int i = 0; i < 10; i++) {
              (new UploadThread(i)).start();
          }
          //线程启动后通过 success_upload_count 检测文件是否上传成功
          while (true){
              if (success_upload_count>=10*50000){
                break;
              }
              Thread.sleep(1000);
          }
          for (int i = 0; i < 20; i++) {
              (new DownloadThread(i)).start();
          }
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  /**
   * 文件下载后的回调方法
   */
  public static class DownloadFileDiscard implements DownloadCallback {
    public DownloadFileDiscard() {
    }

    public int recv(long file_size, byte[] data, int bytes) {
      return 0;
    }
  }

  /**
   * file uploader
   *
   * @author Happy Fish / YuQing
   * @version Version 1.0
   */
  public static class Uploader {
    public TrackerClient tracker;
    public TrackerServer trackerServer;

    public Uploader() throws Exception {
      this.tracker = new TrackerClient();
      this.trackerServer = tracker.getConnection();
    }
    public int uploadFile() throws Exception {
      StorageServer storageServer = null;
      StorageClient1 client = new StorageClient1(trackerServer, storageServer);
      String file_id;
      try {
        file_id = client.upload_file1(file_buff, "txt", null);
        if (file_id == null) {
          logger.error("upload file fail, error code: " + client.getErrorCode());
          return -1;
        }
        TestFDSApps.file_ids.offer(file_id);
        return 0;
      } catch (Exception ex) {
        logger.error("upload file fail, error mesg: " + ex.getMessage());
        return -1;
      }
    }
  }

  /**
   * file downloader
   *
   * @author Happy Fish / YuQing
   * @version Version 1.0
   */
  public static class Downloader {
    public TrackerClient tracker;
    public TrackerServer trackerServer;
    public DownloadFileDiscard callback;

    public Downloader() throws Exception {
      this.tracker = new TrackerClient();
      this.trackerServer = tracker.getConnection();
      this.callback = new DownloadFileDiscard();
    }

    public int downloadFile(String file_id) throws Exception {
      int errno;
      StorageServer storageServer = null;
      StorageClient1 client = new StorageClient1(trackerServer, storageServer);

      try {
        errno = client.download_file1(file_id, this.callback);
        if (errno != 0) {
          logger.error("Download file fail, file_id: " + file_id + ", error no: " + errno);
        }
        return errno;
      } catch (Exception ex) {
        logger.error("Download file fail, error mesg: " + ex.getMessage());
        return -1;
      }
    }
  }

  /**
   * upload file thread
   * 上传文件线程，每一个线程都独自占用一个track client和storage client,较为符合现实使用场景情况
   *  每一个线程上传50000个uploadsize kb大小的文件
   */
  public static class UploadThread extends Thread {
    private int thread_index;

    public UploadThread(int index) {
      this.thread_index = index;
    }

    public void run() {
      try {
        TestFDSApps.upload_thread_count++;
        Uploader uploader = new Uploader();

        logger.info("upload thread " + this.thread_index + " start");

        for (int i = 0; i < 50000; i++) {
          TestFDSApps.total_upload_count++;
          if (uploader.uploadFile() == 0) {
            TestFDSApps.success_upload_count++;
          }
        }
      } catch (Exception ex) {
        ex.printStackTrace();
      } finally {
        TestFDSApps.upload_thread_count--;
      }

      System.out.println("upload thread " + this.thread_index
        + " exit, total_upload_count: " + TestFDSApps.total_upload_count
        + ", success_upload_count: " + TestFDSApps.success_upload_count
        + ", total_download_count: " + TestFDSApps.total_download_count
        + ", success_download_count: " + TestFDSApps.success_download_count);
    }
  }

  /**
   * download file thread
   * 下载文件线程，每一个线程下载一个文件
   * 与上传线程一样每一个上传线程都独自占用一个track client和storage client,较为符合现实使用场景情况
   */
  public static class DownloadThread extends Thread {
    private static Integer counter_lock = new Integer(0);
    private int thread_index;

    public DownloadThread(int index) {
      this.thread_index = index;
    }

    public void run() {
      try {
        String file_id;
        Downloader downloader = new Downloader();
        logger.info("download thread " + this.thread_index + " start");
        file_id = "";
        while (TestFDSApps.upload_thread_count != 0 || file_id != null) {
          file_id = (String) TestFDSApps.file_ids.poll();
          if (file_id == null) {
            Thread.sleep(10);
            continue;
          }
          if (downloader.downloadFile(file_id) == 0) {
            synchronized (this.counter_lock) {
              TestFDSApps.success_download_count++;
              if (TestFDSApps.success_download_count==TestFDSApps.success_upload_count){
                  logger.info("all Download ......Exit    thread    "+this.thread_index);
              }
            }
          } else {
            TestFDSApps.fail_download_count++;
          }
        }
      } catch (Exception ex) {
        ex.printStackTrace();
      }

      System.out.println("download thread " + this.thread_index
        + " exit, total_download_count: " + TestFDSApps.total_download_count
        + ", success_download_count: " + TestFDSApps.success_download_count
        + ", fail_download_count: " + TestFDSApps.fail_download_count);
    }
  }
}
