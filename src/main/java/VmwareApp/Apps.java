package VmwareApp;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

public class Apps {
  String name;
  private static final Logger logger=LoggerFactory.getLogger(Apps.class);
  public static void main(String[] args) throws Exception {
    String WSDLURL="http://wsif.vcomcn.co/SmsService.asmx?WSDL";
    String USERNAME="shsjyksy";
    String PASSWD="SDumYSmp";
    String OP="SendSms";
    String PHONENUMBER="15250356585";
    String Messgae="hellow";

    JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
    Client client= dcf.createClient(new URL(WSDLURL));
    Object[] paras=new Object[]{USERNAME,PASSWD,PHONENUMBER,Messgae,"",""};
    Object[] res=  client.invoke(OP,paras);
    System.out.println((String) res[0]);

  }


}
