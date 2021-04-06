import java.io.File;
import java.nio.file.FileAlreadyExistsException;

public class Apps {
    public static void main(String[] args) throws Exception {

        try {
            throw new FileAlreadyExistsException("11");
        }catch (Exception e){
            e.printStackTrace();


        }
        System.out.print("1111");
    }
}