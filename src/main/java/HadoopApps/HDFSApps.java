package HadoopApps;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.security.UserGroupInformation;

import java.io.IOException;
import java.io.InputStream;


public class HDFSApps {
    public static void main(String[] args) throws IOException, InterruptedException {
        //System.setProperty("java.security.krb5.kdc","192.168.186.126:88");
        //System.setProperty("java.security.krb5.realm","epoint");
        System.setProperty("java.security.krb5.conf","/home/ijarvis/workspace/javaWorkSpace/intelliq-console/src/main/resources/krb5.conf");
        Configuration conf=new Configuration();
        String uri="hdfs://192.168.186.124/user/app.log";
        conf.set("hadoop.security.authentication", "Kerberos");
        conf.set("fs.defaultFS","hdfs://192.168.186.124:8020");
        conf.set("dfs.namenode.kerberos.principal.pattern", "*/*@epoint");
        UserGroupInformation.setConfiguration(conf);
        UserGroupInformation.loginUserFromKeytab("hdfs@epoint", "/home/ijarvis/workspace/javaWorkSpace/intelliq-console/src/main/resources/client_hdfs.keytab");
        FileSystem fs=FileSystem.get(conf);
        fs.copyFromLocalFile(new Path("/home/ijarvis/workspace/javaWorkSpace/intelliq-console/intelliq-console.log"),new Path("/user/app.log"));
        InputStream in=null;
        in=fs.open(new Path(uri));
        IOUtils.copyBytes(in,System.out,4096,false);
    }
}


