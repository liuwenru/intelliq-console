import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URLEncoder;
import com.sun.jna.NativeLong;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Apps {
    public static void main(String[] args) throws Exception {

        System.setSecurityManager(null);
        try {
            Class clazz = Class.forName("Apps");
            Object refTest = clazz.newInstance();

            Method method = clazz.getDeclaredMethod("refMethod");

            long now = System.currentTimeMillis();
            for (int i=0;i<5000000;i++){
                method.invoke(refTest);
            }
            System.out.println("get耗时"+(System.currentTimeMillis() - now) + "ms");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void refMethod() {
        int i=0;
        i++;
    }
}
