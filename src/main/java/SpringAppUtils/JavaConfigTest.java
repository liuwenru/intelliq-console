package SpringAppUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JavaConfigTest {
    @Autowired(required = true)
    private static Apps apps;


    public static void main(String[] args) {
        JavaConfigTest javaConfigTest=new JavaConfigTest();
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(Apps.class,JavaConfigTest.class);
        ctx.refresh();
        Apps a=(Apps)ctx.getBean("getapps");
        a.fprint();
    }
}
