package algorithm;

import java.awt.image.BufferedImage;

public abstract class Convolution {
	
	public static void convolve(BufferedImage image, float[][] mask, int b) {
		
		int imgW = image.getWidth(), imgH = image.getHeight();
		int[] before = new int[imgW*imgH];
		image.getRaster().getSamples(0, 0, imgW, imgH, b, before);
		int[] after = convolve(before, imgW, imgH, mask);
		
		image.getRaster().setSamples(0, 0, imgW, imgH, b, after);
	}
	
	/**
	 * funkcja ogolnego przeznaczenia
	 * 
	 * @param samples
	 * @param sampW
	 * @param sampH
	 * @param mask
	 * @return
	 */
	public static int[] convolve(int[] samples, int sampW, int sampH, float[][] mask) {

		int rh = (mask.length - 1) / 2;
		int rw = (mask[0].length - 1) / 2;
		int h = sampH - 2 * rh, w = sampW - 2 * rw;
		int[] nsamp = new int[sampW * sampH];

		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				int sy = rh * sampW + i * sampW + rw, sx = j, dsy = sampW, dsx = 1;
				int sum = 0;
				for (int k = -rh; k <= rh; k++) {
					for (int l = -rw; l <= rw; l++) {
						sum += samples[sy + k * dsy + sx + l * dsx]
								* mask[rh+k][rw+l];
					}
					nsamp[sy + sx] = sum;
				}
			}
		}
		return nsamp;

	}
}
