package juc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class Apps {

    private static Logger logger = LoggerFactory.getLogger(Apps.class);

    public static Object lock = new Object();

    public static class CallerTask1 implements Callable<String> {

        @Override
        public String call() throws Exception {
            //lock.wait();
            Thread.sleep(1 * 1000);
            logger.debug("我是CallerTask1....CallerTask2起来干活.......");
            synchronized (lock) {
                lock.notifyAll();
            }
            logger.debug("我是CallerTask1....我已经干完.......");
            return "我是CallerTask1....我已经干完.......";
        }

    }

    public static class CallerTask2 implements Callable<String> {

        @Override
        public String call() throws Exception {
            logger.debug("我是CallerTask2....我在等待指令.......");
            synchronized (lock) {
                lock.wait();
                logger.debug("---------");
            }

            logger.debug("我是CallerTask2.....收到开始干活...我已经干完.......");
            return "我是CallerTask2....我已经干完.......";
        }

    }

    public static void main(String[] args) throws InterruptedException {
// 创建异步任务
        FutureTask<String> futureTask1 = new FutureTask<>(new CallerTask1());
        FutureTask<String> futureTask2 = new FutureTask<>(new CallerTask2());
        //启动线程
        Thread t1 = new Thread(futureTask1);
        t1.setName("CallerTask1");
        Thread t2 = new Thread(futureTask2);
        t2.setName("CallerTask2");

        t2.start();
        t1.start();

        t2.join();
        t1.join();

//        new Thread(futureTask).start();
//        try {
//            //等待任务执行完毕，并返回结果
//            String result = futureTask.get();
//            System.out.println(result);
//        } catch (ExecutionException | InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
