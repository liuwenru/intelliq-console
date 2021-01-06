import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import com.sun.jna.NativeLong;

public class Apps {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String name=URLEncoder.encode("需求确认单-任务登记V1.0（电子版）.doc", "UTF-8");
       System.out.println(name);
    }
}
