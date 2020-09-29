package SpringAppUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Apps{
    public void fprint(){
        System.out.print("1111111111111");
    }
    @Bean
    public Apps getapps(){
        return new Apps();
    }

}
