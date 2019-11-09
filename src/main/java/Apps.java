import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Apps {
    private static Logger logger= LoggerFactory.getLogger(Apps.class);
    public static void main(String[] args){
        logger.debug("这是中文");
        System.out.print("这是中文");
    }
}
