package WrapperServiceUtils;

public class Apps {
    public static void main(String[] args) throws InterruptedException {
        int i=0;
        while (true){
            i++;
            if (i %50==0){
                System.out.println("java.lang.OutOfMemoryError");
            }
            System.out.print(i);
            Thread.sleep(1*1000);
        }
    }
}
