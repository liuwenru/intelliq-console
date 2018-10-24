import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Apps {
    private static Logger logger=LoggerFactory.getLogger(Apps.class);
    public  static void main(String[] args) throws InterruptedException, IOException {
        String docker=System.getenv("CURRENT_ALPINE_GLIBC_BASE_IMAGE_VER");
    }
}
