package File2Base64;

import org.apache.commons.codec.binary.Base64;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class Apps {
    public static void main(String[] args) throws IOException {
        ByteArrayOutputStream os1 = new ByteArrayOutputStream();
        InputStream file1 = new FileInputStream("/home/parallels/aaaa.txt");
        byte[] byteBuf = new byte[3*1000];
        byte[] base64ByteBuf;
        int count1; //每次从文件中读取到的有效字节数
        while((count1=file1.read(byteBuf)) != -1)
        {
            if(count1!=byteBuf.length) //如果有效字节数不为3*1000，则说明文件已经读到尾了，不够填充满byteBuf了
            {
                byte[] copy = Arrays.copyOf(byteBuf, count1); //从byteBuf中截取包含有效字节数的字节段
                base64ByteBuf = Base64.encodeBase64(copy); //对有效字节段进行编码
            }
            else
            {
                base64ByteBuf = Base64.encodeBase64(byteBuf);
            }
            //System.out.print(base64ByteBuf.toString());
            os1.write(base64ByteBuf, 0, base64ByteBuf.length);
            os1.flush();
            System.out.print(os1.toString());
        }
        file1.close();
        //System.out.println(os1.toString());
    }



}
