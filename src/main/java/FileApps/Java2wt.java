package FileApps;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Java2wt {
    public static void main(String[] args) throws IOException, FontFormatException {
        Font font=new Font("宋体",1,24);
    	//Font font=Font.createFont(Font.TRUETYPE_FONT,new File("/opt/apache-tomcat-8.5.43/jre/jre/lib/fonts/fallback/simsun.ttc"));
    	int with=300;
    	int hight=300;
    	BufferedImage image=new BufferedImage(with,hight,BufferedImage.TYPE_INT_ARGB);
    	Graphics2D g2d=image.createGraphics();
    	image=g2d.getDeviceConfiguration().createCompatibleImage(with,hight,3);
    	g2d.dispose();
    	g2d=image.createGraphics();
    	g2d.setColor(new Color(10));
    	g2d.setStroke(new BasicStroke(1.0F));
    	FontRenderContext context=g2d.getFontRenderContext();
    	Rectangle2D bounds=font.getStringBounds(args[0],context);
    	double x=(with-bounds.getWidth())/2.0;
    	double y=(hight-bounds.getHeight())/2.0;
    	double ascent=-bounds.getCenterY();
    	double baseY=y+ascent;
    	g2d.rotate(Math.toRadians(-45.0D),with/2,hight/2);
    	g2d.drawString(args[0],(int)x,(int)baseY);
    	g2d.dispose();
    	ImageIO.write(image,"png",new File(args[1]));
    }
}
