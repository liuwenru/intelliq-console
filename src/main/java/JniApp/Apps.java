package JniApp;

public class Apps {

  static {
    System.load("/home/ijarvis/workspace/cworkspace/mycapps/libtest.so");
  }
  public native  int printmessage();
  public static void main(String[] args){
    Apps apps=new Apps();
    apps.printmessage();
  }
}
