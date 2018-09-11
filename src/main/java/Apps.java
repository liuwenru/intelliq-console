import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Apps {

    public  static void main(String[] args) throws InterruptedException, IOException {
        InputStream in = null;
        Process pro = Runtime.getRuntime().exec(new String[]{"sh", "/home/parallels/aaa.sh"});
        pro.waitFor();
        in = pro.getInputStream();
        BufferedReader read = new BufferedReader(new InputStreamReader(in));
        String result = read.readLine();
        System.out.println("INFO:"+result);
    }
}
