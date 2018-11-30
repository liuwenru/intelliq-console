
//import org.soulwing.crypt4j.Crypt;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.Crypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Apps {
    private static Logger logger=LoggerFactory.getLogger(Apps.class);
    /**
     *     利用java原生的类实现SHA256加密     
     *
     * @param str
     *            加密后的报文     
     * @return     
     */
    public static String getSHA256(String str) {
        MessageDigest messageDigest;
        String encodestr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes("UTF-8"));
            encodestr = byte2Hex(messageDigest.digest());
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodestr;
    }

    /**
     *  将byte转为16进制     
     *
     * @param bytes
     *                
     * @return     
     */
    private static String byte2Hex(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
                // 1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }

    /***
     * 利用Apache的工具类实现SHA-256加密
     *
     * @param str
     *            加密后的报文
     * @return
     */
    public static String getSHA256Str(String str) {
        MessageDigest messageDigest;
        String encdeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(str.getBytes("UTF-8"));
            encdeStr = Hex.encodeHexString(hash);
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encdeStr;
    }

    public static String passwd_saltify(String salter) {
        String salt = "";
        long seed = 0;
        for (int j = 0; j < salter.length(); j++) {
            System.out.println(salter.charAt(0));
            seed += salter.charAt(0);
        }
        System.out.println(seed);
        String saltstr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789./";
        String q = "";
        for (int i = 16; i > 0; i--) {
            System.out.println(seed + "=============" + seed % 64);
            q += saltstr.charAt((int)seed % 64);
            seed = (((seed ^ 0x967e3c5a) << 3) ^ (~(seed >> 2)& 0xffffffff + (i-1)));
            seed = getUnsignedIntt(seed);
        }
        q += "$";
        return "$5$rounds=9999$" + q;
    }

    public static long getUnsignedIntt(long data) { // 将int数据转换为0~4294967295
        // (0xFFFFFFFF即DWORD)。
        return data & 0xFFFFFFFF;
    }
    public  static void main(String[] args) throws InterruptedException, IOException, NoSuchAlgorithmException {
        System.out.println(passwd_saltify("epoint"));
        //String value= Crypt.crypt("epoint","$5$rounds=9999$eXQoxW0rV5PZ196C$");
        //System.out.println(value);
    }
}
