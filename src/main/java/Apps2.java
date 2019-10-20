public class Apps2 {
    public  static  void main(String[] args) throws InterruptedException {
        System.out.println("Epoint......");
        //  Thread.sleep(1000*1000);
        T1[] arry=new T1[10];
        for(int i=0;i<10;i++){
            arry[i]=new T1();
            arry[i].start();
        }
    }
    public  static  void Dosomething() throws InterruptedException {
        Thread.sleep(100000*1000);
        System.out.println("aaaa");
    }
}


class T1 extends Thread{
    @Override
    public void run() {
        super.run();
        try {
            Apps2.Dosomething();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}