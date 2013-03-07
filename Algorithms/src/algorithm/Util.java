package algorithm;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

public abstract class Util {

	static final int[] LMASK = { 0, -1, 0, -1, 4, -1, 0, -1, 0 };

	public static BufferedImage grayScale(BufferedImage src) {
		BufferedImage dst = new BufferedImage(src.getWidth(), src.getHeight(),
				BufferedImage.TYPE_BYTE_GRAY);
		WritableRaster srcR = src.getRaster(), dstR = dst.getRaster();
		int h = src.getHeight(), w = src.getWidth();
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				int samp = 0;
				for (int k = 0; k < srcR.getNumBands(); k++) {
					samp += srcR.getSample(j, i, k);
				}
				samp /= srcR.getNumBands();
				dstR.setSample(j, i, 0, samp);
			}
		}
		return dst;
	}

	public static void convolve(BufferedImage image, float[] mask, int mask_h,
			int mask_w, int b) {

		int rh = (mask_h - 1) / 2;
		int rw = (mask_w - 1) / 2;
		int h = image.getHeight() - 2 * rh, w = image.getWidth() - 2 * rw;
		int imgH = image.getHeight(), imgW = image.getWidth();
		int[] samples = new int[imgW * imgH], nsamp = new int[imgW * imgH];
		image.getRaster().getSamples(0, 0, imgW, imgH, b, samples);

		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				int sy = rh * imgW + i * imgW + rw, sx = j, dsy = imgW, dsx = 1;
				int sum = 0;
				for (int k = -rh; k <= rh; k++) {
					for (int l = -rw; l <= rw; l++) {
						sum += samples[sy + k * dsy + sx + l * dsx]
								* mask[(rh + k) * mask_w + rw + l];
					}
					nsamp[sy + sx] = sum;
				}
			}
		}
		image.getRaster().setSamples(0, 0, imgW, imgH, b, nsamp);
	}

	public static void laplacian(BufferedImage image) {
		int rh = 1;
		int rw = 1;
		int h = image.getHeight() - 2 * rh, w = image.getWidth() - 2 * rw;
		int imgH = image.getHeight(), imgW = image.getWidth();
		int[] samples = new int[imgW * imgH], nsamp = new int[imgW * imgH];
		image.getRaster().getSamples(0, 0, imgW, imgH, 0, samples);

		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				int sy = rh * imgW + i * imgW + rw, sx = j, dsy = imgW, dsx = 1;
				int sum = 0;
				for (int k = -rh; k <= rh; k++) {
					for (int l = -rw; l <= rw; l++) {
						sum += samples[sy + k * dsy + sx + l * dsx]
								* LMASK[(rh + k) * 3 + rw + l];
					}
					nsamp[sy + sx] = sum;
				}
			}
		}
		for (int i = 0; i < nsamp.length; i++) {
			System.out.print(nsamp[i] + ",");
		}
	}
}
