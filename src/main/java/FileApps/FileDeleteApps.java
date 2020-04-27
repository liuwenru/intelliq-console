package FileApps;

import java.io.File;

public class FileDeleteApps {
    public static void main(String[] args) {
        File file=new File(args[0]);
        Boolean boll=file.delete();
        System.out.println(boll);
    }
}
