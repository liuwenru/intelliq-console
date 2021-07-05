import org.apache.commons.lang.RandomStringUtils;

public class Apps {
    public static void main(String[] args) throws Exception {
//        java.nio.channels.Selector.open().select();
        String random=RandomStringUtils.random(11,"1234567890");
        System.out.println(random);
    }
}