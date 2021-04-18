package basisApps;

public class ChilderClass extends ParentClass {
    @Override
    public void m1() {
        System.out.println("ChilderClass!!!!!!!!!!!!!!");
    }

    public static void main(String[] args) {
        ParentClass parentClass=new ChilderClass();
        parentClass.m1();
    }
}
