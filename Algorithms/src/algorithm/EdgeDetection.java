package algorithm;

import java.awt.image.BufferedImage;

public abstract class EdgeDetection {

	/**
	 * wyznacza krawedzie metoda z obliczaniem pierwszej pochodniej
	 * 
	 * @param image
	 * @param maskV
	 *            maska wykrywajaca linie pionowe
	 * @param maskH
	 *            masksa wykrywajaca linie poziome
	 * @param threshold
	 *            minimalny prog ponizej ktorego pixel nie zostanie
	 *            sklasyfikowany jako linia
	 */
	public static int[] gradient(BufferedImage image, float[][] maskV,
			float[][] maskH, int threshold)
			throws UnsupportedOperationException {
		if (image.getType() != image.TYPE_BYTE_GRAY) {
			throw new UnsupportedOperationException(
					"only greyscale is supported for now ...");
		}
		int imgW = image.getWidth(), imgH = image.getHeight();
		int[] before = new int[imgW * imgH];
		image.getRaster().getSamples(0, 0, imgW, imgH, 0, before);

		int[] afterH = Convolution.convolve(before, imgW, imgH, maskH);
		int[] afterV = Convolution.convolve(before, imgW, imgH, maskV);

		// obliczanie dlugosci wektora gradientu

		int[] magnitude = new int[before.length];

		for (int i = 0; i < magnitude.length; i++) {
			int val = Math.abs(afterH[i]) + Math.abs(afterV[i]);
			val = (val > 255) ? 255 : val;
			magnitude[i] = val;
		}

		// progowanie

		for (int i = 0; i < magnitude.length; i++) {
			magnitude[i] = (magnitude[i] < threshold) ? 0 : magnitude[i];
		}

		image.getRaster().setSamples(0, 0, imgW, imgH, 0, magnitude);
		return magnitude;
	}

	public static int[] gradient(BufferedImage image, float[][] mask,
			int threshold) throws UnsupportedOperationException {
		if (image.getType() != image.TYPE_BYTE_GRAY) {
			throw new UnsupportedOperationException(
					"only greyscale is supported for now ...");
		}
		int imgW = image.getWidth(), imgH = image.getHeight();
		int[] before = new int[imgW * imgH];
		image.getRaster().getSamples(0, 0, imgW, imgH, 0, before);

		int[] after = Convolution.convolve(before, imgW, imgH, mask);

		// obliczanie dlugosci wektora gradientu

		int[] magnitude = new int[before.length];

		for (int i = 0; i < magnitude.length; i++) {
			int val = Math.abs(after[i]);
			val = (val > 255) ? 255 : val;
			magnitude[i] = val;
		}

		// progowanie

		for (int i = 0; i < magnitude.length; i++) {
			magnitude[i] = (magnitude[i] < threshold) ? 0 : magnitude[i];
		}

		image.getRaster().setSamples(0, 0, imgW, imgH, 0, magnitude);
		return magnitude;
	}

	public static void laplacian(BufferedImage image, float[][] mask,
			int threshold) throws UnsupportedOperationException {
		if (image.getType() != image.TYPE_BYTE_GRAY) {
			throw new UnsupportedOperationException(
					"only greyscale is supported for now ...");
		}

		int imgW = image.getWidth(), imgH = image.getHeight();
		int[] before = new int[imgW * imgH];
		image.getRaster().getSamples(0, 0, imgW, imgH, 0, before);

		int[] after = Convolution.convolve(before, imgW, imgH, mask);

		for (int i = 0; i < after.length; i++) {
			after[i] = (after[i] >= -threshold && after[i] <= threshold) ? 128
					: 128 + after[i];

		}

		image.getRaster().setSamples(0, 0, imgW, imgH, 0, after);

	}

	public static int[] both(BufferedImage image, int threshold)
			throws UnsupportedOperationException {
		if (image.getType() != image.TYPE_BYTE_GRAY) {
			throw new UnsupportedOperationException(
					"only greyscale is supported for now ...");
		}

		int imgW = image.getWidth(), imgH = image.getHeight();
		int[] before = new int[imgW * imgH];
		int[] after = new int[before.length];
		image.getRaster().getSamples(0, 0, imgW, imgH, 0, before);

		int[] gradMagnitude = gradient(image, Mask.SOBEL_V, Mask.SOBEL_H,
				threshold);
//		after = gradMagnitude;
		int[] lapl = Convolution.convolve(before, imgW, imgH,
				Mask.LAPLACIAN_DIAG);
		for (int i = 0; i < lapl.length; i++) {
			if (gradMagnitude[i] > 0) {
				// blisko krawedzi
				if (lapl[i] >= 0) {
					// ciemna strona
					after[i] = 128;
				} else {
					// jasna strona
					after[i] = 255;
				}
			} else {
				after[i] = 0;
			}
		}

//		markObject(after, imgW, imgH);

//		disableGrey(after, imgW, imgH);
		image.getRaster().setSamples(0, 0, imgW, imgH, 0, after);
		return after;
	}
	static void markObject(int[] samples, int imgW, int imgH){
		boolean inObjectL = false, inObjectR = false;
		boolean[] isObjectL = new boolean[imgW*imgH];
		boolean[] isObjectR = new boolean[imgW*imgH];

		for (int i = 0; i < imgH; i++) {
			int prevR = samples[i * imgW + imgW -1];
			int prevL = samples[i * imgW];
			for (int j = 0; j < imgW; j++) {
				 int indexL = i * imgW + j;
				 int indexR = i * imgW + imgW -1 -j;
				 // od prawej
				 if(samples[indexR] == 255 && prevR == 128){
					 inObjectR = true;
				 } else if(prevR == 255 && samples[indexR] == 128){
					 inObjectR = false;
				 } else if(inObjectR){
					 isObjectR[indexR] = true;
				 }
				 // od lewej
				 if(samples[indexL] == 255 && prevL == 128){
					 inObjectL = true;
				 } else if(prevL == 255 && samples[indexL] == 128){
					 inObjectL = false;
				 } else if(inObjectL){
					 isObjectL[indexL] = true;
				 }
				 prevL = samples[indexL];
				 prevR = samples[indexR];
			}
		}
		for (int i = 0; i < imgH; i++) {
			for (int j = 0; j < imgW; j++) {
				 int index = i * imgW + j;
				 if(isObjectL[index] && samples[index] != 128){
					 samples[index] = 255;
				 } else {
					 samples[index] = 0;
				 }
			}
		}
	}
	
	static void disableGrey(int[] samples, int imgW, int imgH){
		for (int i = 0; i < imgH; i++) {
			for (int j = 0; j < imgW; j++) {
				 int index = i * imgW + j;
				 if(samples[index] == 128 ){
					  samples[index] = 0;
				 } else {
					// samples[index] = 0;
				 }
			}
		}
	}
}
