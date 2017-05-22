import java.sql.SQLException;

/**
 * Created by ijarvis on 2017/5/17.
 */
public class Apps {

    public void printMesg(){
        System.out.println("Success.........");
    }

    public static void  main(String[] args) throws InterruptedException, SQLException, ClassNotFoundException {

        System.out.println("print main process.........");

        MySQLUtils tmp=new MySQLUtils(args[0],args[1],args[2],args[3],args[4]);
        tmp.checkMySQL(args[5]);
    }




}
