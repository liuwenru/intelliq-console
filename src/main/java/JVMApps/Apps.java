package JVMApps;

import java.util.ArrayList;

public class Apps {
    public  static ArrayList<String> STATIC=new ArrayList<String>();
    public static String CONTeNT="";
    public static void GenerSTR(){
        for (int i =0;i<1024000;i++){
            CONTeNT=CONTeNT+"a";
        }
    }
    public static  void  main(String[] args){
        GenerSTR();
        for (int i=0;i<Integer.parseInt(args[0]);i++){
            if (i%10000==0){
                System.out.println("Count: "+i);
            }
            STATIC.add(CONTeNT);
        }
    }
}
