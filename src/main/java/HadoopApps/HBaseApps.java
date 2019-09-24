package HadoopApps;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.security.UserGroupInformation;

import java.io.IOException;

public class HBaseApps {
    public static void main(String[] args) throws IOException {
//        System.setProperty("java.security.krb5.conf","/home/ijarvis/workspace/javaWorkSpace/intelliq-console/src/main/resources/krb5.conf");
//        Configuration conf = HBaseConfiguration.create();
//        conf.set("hadoop.security.authentication" , "Kerberos" );
//        conf.set("keytab.file" , "C:/Users/Downloads/hbase.keytab" );
//        conf.set("kerberos.principal" , "hbase/1722.myip.domain@HADOOP.COM" );
//        conf.set("hbase.master.kerberos.principal",HBASE_MASTER_PRINCIPAL);
//        conf.set("hbase.regionserver.kerberos.principal",HBASE_RS_PRINCIPAL);
//        conf.set("hbase.zookeeper.quorum","xxx.xxx.xxx.xxx");
//        conf.set("hbase.zookeeper.property.clientPort","2181");
//        conf.set("hbase.security.authentication","kerberos");
//        UserGroupInformation.setConfiguration(conf);
//        UserGroupInformation.loginUserFromKeytab("hbase/1722.myip.domain@HADOOP.COM", "C:/Users/Downloads/hbase.keytab" );
    }
}
