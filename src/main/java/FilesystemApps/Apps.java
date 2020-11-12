package FilesystemApps;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.springframework.context.annotation.Bean;

public class Apps {
    public static void main(String[] args) throws IOException {

		long length=1024*1024*1024L*Integer.parseInt(System.getProperty("testfilesize"));
		//int blen=Integer.parseInt(args[0]);
		int blen=Integer.parseInt(System.getProperty("bufferlen"));
		byte tmp[]=new byte[blen];
		System.out.println(System.getProperty("payload").getBytes()[0]);
		for(int i=0;i<blen;i++){
			tmp[i]=System.getProperty("bufferlen").getBytes()[0];
		}
		// mode 1
		int mode=Integer.parseInt(args[0]);
		if (mode==0){
			RandomAccessFile randomAccessFile=new RandomAccessFile(System.getProperty("filename"),"rw");
			long start=System.currentTimeMillis();
			Fallocate.forDescriptor(randomAccessFile.getFD(), length).keepSize().execute();
			long end=System.currentTimeMillis();
			System.out.println("fallocate time:"+(end-start));
			long inputlen=0;
			while (inputlen<length){
				randomAccessFile.write(tmp);
				inputlen=inputlen+blen;
			}
			randomAccessFile.close();
		}else if(mode==1){
			RandomAccessFile randomAccessFile=new RandomAccessFile(System.getProperty("filename"),"rw");
			long start=System.currentTimeMillis();
			randomAccessFile.setLength(length);
			long end=System.currentTimeMillis();
			System.out.println("setLength time:"+(end-start));
			long inputlen=0;
			while (inputlen<length){
				randomAccessFile.write(tmp);
				inputlen=inputlen+blen;
			}
			randomAccessFile.close();
		}else if(mode==2){
			RandomAccessFile randomAccessFile=new RandomAccessFile(System.getProperty("filename"),"rw");
			System.out.println(randomAccessFile.length());
			long inputlen=0;
			while (inputlen<length){
				randomAccessFile.write(tmp);
				inputlen=inputlen+blen;
			}
			randomAccessFile.close();
		}else if(mode==3){
			File file = new File(System.getProperty("filename"));
			OutputStream outputStream = new FileOutputStream(file);
			long inputlen=0;
			while (inputlen<length){
				outputStream.write(tmp);
				inputlen=inputlen+blen;
			}
			outputStream.close();
			
		}
		
		
    }
}
