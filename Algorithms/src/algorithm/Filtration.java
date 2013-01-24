package algorithm;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Filtration {
	private static final int[] convMask = { 1, 1, 1, 1, 1, 1, 1, 1, 1 };
	// m - szerokosc maski, n - wysokosc maski
	// rn - promien maski w pionie, rm - promien maski w poziomie

	private static final int n = 5, m = 5, rn = (n - 1) / 2, rm = (m - 1) / 2;

	private static int[] getRasterZeros(BufferedImage image) {

		int[] arr = new int[(image.getWidth() + 2 * rm)
				* (image.getHeight() + 2 * rn)];
		int w = image.getWidth(), h = image.getHeight();
		WritableRaster r = image.getRaster();
		int offset = w + 2 * rm;
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				arr[offset * rn + offset * i + rm + j] = r.getSample(j, i, 0);
			}
		}
		return arr;
	}

	protected static BufferedImage conv(BufferedImage image) {
		System.out.println();
		int[] raster = getRasterZeros(image);
		int w = image.getWidth() + 2 * rm, h = image.getHeight() + 2 * rn;
		int nBands = image.getRaster().getNumBands();
		for (int b = 0; b < nBands; b++) {
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {

				}
				System.out.println();
			}
		}
		return null;
	}
	
	//private int countPixel(int x, int)
	public static void main(String[] args) {
		try {
			BufferedImage img = ImageIO.read(new File("images/simple2.bmp"));
			conv(img);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
