import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Apps {
    //private static Logger logger=LoggerFactory.getLogger(Apps.class);
    public  static void main(String[] args) throws IOException {
        File file=new File("test.doc");
        FileOutputStream outputStream=new FileOutputStream(file);
        outputStream.write("Epoint......".getBytes());
    }
}
