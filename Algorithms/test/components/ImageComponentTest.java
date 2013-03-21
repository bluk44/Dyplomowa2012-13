package components;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import algorithm.Util;

public class ImageComponentTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		try {
//			BufferedImage img;
//			img = ImageIO.read(new File("images/tiny/almost_white.bmp"));
//			ImageComponent ic = new ImageComponent(img);
//			ic.print();
//			int[] samples = new int[img.getWidth() * img.getHeight()];
//			img.getRaster().getSamples(0, 0, img.getWidth(), img.getHeight(), 0, samples);
//		//	int[] nsamp = Convolution.convlovle(samples, img.getWidth(), img.getHeight(), Util.SOBEL);
//			//ic.print();
//			int imgW = img.getWidth(), imgH = img.getHeight();
//
//			for (int i = 0; i < imgH; i++) {
//				for (int j = 0; j < imgW; j++) {
//					System.out.format("%04d ",nsamp[i*imgW+j]);
//				}
//				System.out.println();
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

}
