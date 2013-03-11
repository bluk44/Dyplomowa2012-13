package algorithm;

import jai.ImgComp;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class ConvTesting {
	
	static {
		Properties p = new Properties(System.getProperties());
		p.put("com.sun.media.jai.disableMediaLib", "true");
		System.setProperties(p);
	}
	public static void main(String[] args){
		try{
//		BufferedImage image = ImageIO.read(new File("images/kolo.bmp"));
//		BufferedImage img = Conv.laplacian(image);
//		JFrame frame = new JFrame("laplacian test");
//		ImgComp ic = new ImgComp(img);
//		JScrollPane scrollPane = new JScrollPane(ic);
//
//		frame.getContentPane().add(scrollPane);
//		frame.pack();
//		frame.setVisible(true);
		BufferedImage testImg = ImageIO.read(new File("images/conv_test/domek.bmp"));
//		BufferedImage testImg = ImageIO.read(new File("images/kolo.bmp"));
			
		BufferedImage greyImg = Util.grayScale(testImg);
		float[] mask = {0.111f,0.111f,0.111f,0.111f,0.111f,0.111f,0.111f,0.111f,0.111f};
		Util.convolve(greyImg, mask, 3, 3, 0);
		Util.convolve(greyImg, Util.SOBEL, 3, 3, 0);
		JFrame frame = new JFrame("convolve test");
		ImgComp ic = new ImgComp(greyImg);
		JScrollPane scrollPane = new JScrollPane(ic);

		frame.getContentPane().add(scrollPane);
		frame.pack();
		frame.setVisible(true);
		} catch(Exception ex){
			
		}
	}
}
