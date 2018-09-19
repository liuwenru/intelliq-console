package PDFBoxUtils;

import org.apache.axis.encoding.Base64;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 *
 *  PDFBOX 库会默认只扫描 /usr/share/fonts的目录去找字体文件，但是比较幺蛾子的那个文件的加载并非是按照字体文件名来加载的
 *
 *                      /usr/share/fonts/mtextra.ttf 默认走的是这个字体文件
 *
 *
 */

public class PDFBOXApp {
    public static void  main(String[] args) throws IOException {
        System.out.println("111");
        //PDFont formFont = PDType0Font.load(doc, new FileInputStream(args[0]), false);
        File pdfbase=new File("/home/parallels/workspace/Javaworkspace/intelliq-console/src/main/resources/pdffilebase64_2.txt");
        FileInputStream fileInputStream=new FileInputStream(pdfbase);
        BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));
        String pdfbase64=br.readLine();
        byte[] b = Base64.decode(pdfbase64);
        ByteArrayInputStream byteInputStream = new ByteArrayInputStream(b);
        PDDocument pdDocument = PDDocument.load(byteInputStream);
        //PDFont formFont = PDType0Font.load(pdDocument, new FileInputStream(args[0]), false);
        PDFRenderer renderer = new PDFRenderer(pdDocument);
        BufferedImage image = renderer.renderImage(0);
        File outputfile = new File("image_2.jpg");
        ImageIO.write(image, "JPG", outputfile);


    }
}
