import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.ReentrantLock;

public class Apps {
    private static Logger logger= LoggerFactory.getLogger(Apps.class);
    public static void main(String[] args){
        if(true){
            try {
                System.out.println("1");
                return;
            }catch (Exception e){
                System.out.println("3");
            }
        }
        try {

        }finally {
            System.out.println("4");
        }


    }
}
