package algorithm;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

public abstract class Treshold {

	public static void global(int value, BufferedImage image)
			throws UnsupportedOperationException {
		if (image.getType() != BufferedImage.TYPE_BYTE_GRAY)
			throw new UnsupportedOperationException(
					"only grayscale images are allowed");
		split(0, 0, image.getWidth(), image.getHeight(), value, image);

	}

	public static void globalAdaptive(float initValue, float diff, BufferedImage image)
			throws UnsupportedOperationException {
		if (image.getType() != BufferedImage.TYPE_BYTE_GRAY)
			throw new UnsupportedOperationException(
					"only grayscale images are allowed");
		// liczenie histogramu
		Histogram h = new Histogram(image);
		int[] val = h.getValues(0);
		
		float avg0 = 0, sum0 = 0, avg1 = 0, sum1 = 0, tr1=initValue, tr2=initValue;
		do{
			tr1 = tr2;
			avg0 = sum0 = avg1 = sum1 = 0;
			for (int i = 0; i < val.length; i++) {
				if (i < tr1){
					avg0 += val[i]*i;
					sum0 += val[i];
				}
				else{
				    avg1 += val[i]*i;
				    sum1 += val[i];
				}
			}
			avg0 /= sum0;
			avg1 /= sum1;
			
			tr2 = (avg0+avg1)/2;
			System.out.println(tr2);
		}while(Math.abs(tr2 - tr1) > diff);
		split(0, 0, image.getWidth(), image.getHeight(), (int)tr2, image);
		h.setTreshold(0, (int)tr2);
		h.draw(0);
	}
	
	protected static void split(int x, int y, int w, int h, int t, BufferedImage image){
		WritableRaster r = image.getRaster();
		int[] samples = new int[h*w];
		r.getSamples(0, 0, w, h, 0, samples);
		for (int i = 0; i < samples.length; i++) {
			if (samples[i] < t)
				samples[i] = 0;
			else
				samples[i] = 255;
		}
		r.setSamples(0, 0, w, h, 0, samples);

	}
}
