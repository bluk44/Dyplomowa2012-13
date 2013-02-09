package algorithm;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Filtration {
	protected static final double[][] convMask = { { 0.111d, 0.111d, 0.111d }, { 0.111d, 0.111d, 0.111d },
			{ 0.111d, 0.111d, 0.111d } };
	protected static final double maskSum;
	static {
		int sum = 0;
		for (int i = 0; i < convMask.length; i++) {
			for (int j = 0; j < convMask[0].length; j++) {
				++sum;
			}
		}
		maskSum = sum;
	}

	protected static final double MAX_PIXEL_VAL = 255;
	// m - szerokosc maski, n - wysokosc maski
	// rn - promien maski w pionie, rm - promien maski w poziomie

	private static final int n = 3, m = 3, rn = (n - 1) / 2, rm = (m - 1) / 2;

	protected static void conv(BufferedImage image) {
		int nBands = image.getRaster().getNumBands();
		int h = image.getHeight(), w = image.getWidth();
		for (int b = 0; b < nBands; b++) {
			double[][] raster = getRasterZeros(image, b);
			double[] newSamples = new double[image.getWidth() * image.getHeight()];

			// dla kazdego pixela policz sume
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					newSamples[i * w + j] = countPixel(rn + i, rm + j, raster); 
				}
			}

			image.getRaster().setSamples(0, 0, w, h, b, newSamples);
		}

	}

	/**
	 * kopiuje raster do tablicy z zerami na brzegach
	 * 
	 * @param image
	 * @param b
	 * @return
	 */
	private static double[][] getRasterZeros(BufferedImage image, int b) {

		double[][] arr = new double[image.getHeight() + 2 * rn][image.getWidth() + 2
				* rm];
		int w = image.getWidth(), h = image.getHeight();
		WritableRaster r = image.getRaster();
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				arr[rn + i][rm + j] = r.getSampleDouble(j, i, b);
			}
		}
		return arr;
	}

	private static double countPixel(int y, int x, double[][] raster) {
		double sum = 0;
			for (int h = y - rn, i = 0; h <= y + rn; h++, i++) {
			for (int w = x - rm, j = 0; w <= x + rm; w++, j++) {
				sum += convMask[i][j] * raster[h][w];
			}
		}
		return sum;
	}

	public static void main(String[] args) {
		
		try {
			BufferedImage img = ImageIO.read(new File("images/kolo.bmp"));
			System.out.println(img.getRGB(10, 10));
//			conv(img);
//
//			int w = img.getWidth(), h = img.getHeight(), nBands = img
//					.getRaster().getNumBands();
//			for (int b = 0; b < nBands; b++) {
//				int[] samples = img.getRaster().getSamples(0, 0, w, h, b,
//						(int[]) null);
//				for (int i = 0; i < h; i++) {
//					for (int j = 0; j < w; j++) {
//						if (samples[i * w + j] == 0) {
//							System.out.print(" 0  ");
//						} else {
//							System.out.print(samples[i * w + j] + " ");
//						}
//					}
//					System.out.println();
//				}
//				System.out.println();
//			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
