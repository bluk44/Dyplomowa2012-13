package algorithm;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

import components.AdaptiveTresholdingData;

public abstract class Treshold {

	public static void global(int value, BufferedImage image)
			throws UnsupportedOperationException {
		if (image.getType() != BufferedImage.TYPE_BYTE_GRAY)
			throw new UnsupportedOperationException(
					"only grayscale images are allowed");
		Histogram h = new Histogram(image);
		h.setTreshold(0, value);
		h.draw(0);
		split(0, 0, image.getWidth(), image.getHeight(), value, image);

	}

	public static void globalAdaptive(float initValue, float diff,
			BufferedImage image) throws UnsupportedOperationException {
		if (image.getType() != BufferedImage.TYPE_BYTE_GRAY)
			throw new UnsupportedOperationException(
					"only grayscale images are allowed");
		localAdaptive(initValue, diff, image, 1,1);
	}

	public static void localAdaptive(float initValue, float diff,
			BufferedImage image, int xSections, int ySections)
			throws UnsupportedOperationException {
		if (image.getType() != BufferedImage.TYPE_BYTE_GRAY)
			throw new UnsupportedOperationException(
					"only grayscale images are allowed");

		int w = image.getWidth(), h = image.getHeight();
		int rX = w % xSections, rY = h % ySections, secWidth = w / xSections, secHeight = h
				/ ySections;
		int sX = xSections - rX, sY = ySections - rY;

		for (int i = 0; i < ySections; i++) {
			for (int j = 0; j < xSections; j++) {
				int kX = (j >= sX) ? 1 : 0, kY = (i >= sY) ? 1 : 0;
				int tX = ((j - sX) > 0) ? (j - sX) : 0, tY = ((i - sY) > 0) ? (i - sY)
						: 0;
				int sW = secWidth + kX, sH = secHeight + kY, sCoordX = j
						* secWidth + tX, sCoordY = i * secHeight + tY;

				Histogram his = new Histogram(image, sCoordX, sCoordY, sW, sH);
				int[] val = his.getValues(0);

				float avg0 = 0, sum0 = 0, avg1 = 0, sum1 = 0, tr1 = initValue, tr2 = initValue;
				do {
					tr1 = tr2;
					avg0 = sum0 = avg1 = sum1 = 0;
					for (int ii = 0; ii < val.length; ii++) {
						if (ii < tr1) {
							avg0 += val[ii] * ii;
							sum0 += val[ii];
						} else {
							avg1 += val[ii] * ii;
							sum1 += val[ii];
						}
					}
					avg0 /= sum0;
					avg1 /= sum1;

					tr2 = (avg0 + avg1) / 2;
				} while (Math.abs(tr2 - tr1) > diff);
				split(sCoordX, sCoordY, sW, sH, (int) tr2, image);
				his.setTreshold(0, (int) tr2);
			}
		}
	}
	
	public static void localAdaptive(float initValue, float diff,
			BufferedImage image, int xSections, int ySections, AdaptiveTresholdingData opData)
			throws UnsupportedOperationException {
		if (image.getType() != BufferedImage.TYPE_BYTE_GRAY)
			throw new UnsupportedOperationException(
					"only grayscale images are allowed");
		opData.setImage(image);
		int[] xSec = new int[xSections], ySec  = new int[ySections];
		
		int w = image.getWidth(), h = image.getHeight();
		int rX = w % xSections, rY = h % ySections, secWidth = w / xSections, secHeight = h
				/ ySections;
		int sX = xSections - rX, sY = ySections - rY;

		for (int i = 0; i < ySections; i++) {
			for (int j = 0; j < xSections; j++) {
				int kX = (j >= sX) ? 1 : 0, kY = (i >= sY) ? 1 : 0;
				int tX = ((j - sX) > 0) ? (j - sX) : 0, tY = ((i - sY) > 0) ? (i - sY)
						: 0;
				int sW = secWidth + kX, sH = secHeight + kY, sCoordX = j
						* secWidth + tX, sCoordY = i * secHeight + tY;

				Histogram his = new Histogram(image, sCoordX, sCoordY, sW, sH);
				int[] val = his.getValues(0);

				float avg0 = 0, sum0 = 0, avg1 = 0, sum1 = 0, tr1 = initValue, tr2 = initValue;
				do {
					tr1 = tr2;
					avg0 = sum0 = avg1 = sum1 = 0;
					for (int ii = 0; ii < val.length; ii++) {
						if (ii < tr1) {
							avg0 += val[ii] * ii;
							sum0 += val[ii];
						} else {
							avg1 += val[ii] * ii;
							sum1 += val[ii];
						}
					}
					avg0 /= sum0;
					avg1 /= sum1;

					tr2 = (avg0 + avg1) / 2;
				} while (Math.abs(tr2 - tr1) > diff);
				split(sCoordX, sCoordY, sW, sH, (int) tr2, image);
				xSec[j] = sCoordX;
				ySec[i] = sCoordY;
				his.setTreshold(0, (int) tr2);
				opData.addHistogram(his, j, i);
			}
		}
		opData.setxSections(xSec);
		opData.setySections(ySec);
	}
	
	protected static void split(int x, int y, int w, int h, int t,
			BufferedImage image) {
		WritableRaster r = image.getRaster();
		int[] samples = new int[h * w];
		r.getSamples(x, y, w, h, 0, samples);
		for (int i = 0; i < samples.length; i++) {
			if (samples[i] < t)
				samples[i] = 0;
			else
				samples[i] = 255;
		}
		r.setSamples(x, y, w, h, 0, samples);

	}
}
