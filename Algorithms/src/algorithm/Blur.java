package algorithm;

import java.awt.image.BufferedImage;

public abstract class Blur {
	
	public static void gauss33(BufferedImage image){
		
		int[] after = conv(image, Mask.GAUSS_3_3);
		int sum = sumMask(Mask.GAUSS_3_3);
		for (int i = 0; i < after.length; i++) {
			after[i] /= sum;
		}
		
		image.getRaster().setSamples(0, 0, image.getWidth(), image.getHeight(), 0, after);
		
	}
	
	public static void gauss55(BufferedImage image){
		
		int[] after = conv(image, Mask.GAUSS_5_5);
		int sum = sumMask(Mask.GAUSS_5_5);
		for (int i = 0; i < after.length; i++) {
			after[i] /= sum;
		}

		image.getRaster().setSamples(0, 0, image.getWidth(), image.getHeight(), 0, after);
		
	}
	
	private static int[] conv(BufferedImage image, float[][] mask){
		int imgW = image.getWidth(), imgH = image.getHeight();
		int[] before = new int[imgW*imgH];
		image.getRaster().getSamples(0, 0, imgW, imgH, 0, before);
		
		return Convolution.convolve(before, imgW, imgH, mask);
	}
	
	private static int sumMask(float[][] mask){
		int sum = 0;
		for (int i = 0; i < mask.length; i++) {
			for (int j = 0; j < mask[0].length; j++) {
				sum += mask[i][j];
			}
		}
		
		return sum;
	}
}
