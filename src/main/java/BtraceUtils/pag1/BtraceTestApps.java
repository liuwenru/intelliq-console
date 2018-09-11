package BtraceUtils.pag1;

import BtraceUtils.pag1.pag2.SlowMethods;
import DruidAppUtils.DruidApps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BtraceTestApps {
    private static Logger logger= LoggerFactory.getLogger(BtraceTestApps.class);
    public static void main(String[] args) throws InterruptedException {
        while (true){
            method1();
            method2();
            method3();
            SlowMethods.method1();
        }
    }
    public static void method1() throws InterruptedException {
        logger.debug("i am method 1");
        Thread.sleep(5000);
    }
    public static void method2() throws InterruptedException {
        logger.debug("i am method 2");
        Thread.sleep(2000);
    }
    public static void method3() throws InterruptedException {
        logger.debug("i am method 3");
        Thread.sleep(1000);
    }
}



