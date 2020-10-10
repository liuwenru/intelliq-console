import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import com.alibaba.druid.sql.dialect.mysql.ast.MysqlForeignKey;
public class Apps {
    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println(URLEncoder.encode("OA                            数据库    ","UTF-8"));
    }
}
