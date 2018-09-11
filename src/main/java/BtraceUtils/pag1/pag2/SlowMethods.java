package BtraceUtils.pag1.pag2;

import BtraceUtils.pag1.BtraceTestApps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public  class SlowMethods {
    private static Logger logger= LoggerFactory.getLogger(BtraceTestApps.class);
    public static void method1() throws InterruptedException {
        logger.debug("i am method 1 in SlowMethods");
        Thread.sleep(15000);
    }
    public static void method2() throws InterruptedException {
        logger.debug("i am method 2 in SlowMethods");
        Thread.sleep(2000);
    }
    public static void method3() throws InterruptedException {
        logger.debug("i am method 3 in SlowMethods");
        Thread.sleep(1000);
    }
}
