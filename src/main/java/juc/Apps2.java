package juc;

public class Apps2 {
    public static void main(String[] args) {


        Thread thread = new Thread(new Runnable() {
            public void run() {
                for (; ; ) {
                }
            }
        });

        //启动子线
        thread.setDaemon(true);
        thread.start();

        System.out.print("main thread is over");
    }
}
