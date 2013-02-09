package algorithm;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

public abstract class Treshold {

	public static void global(int value, BufferedImage image)
			throws UnsupportedOperationException {
		if (image.getType() != BufferedImage.TYPE_BYTE_GRAY)
			throw new UnsupportedOperationException(
					"only grayscale images are allowed");
		WritableRaster r = image.getRaster();
		int[] samples = new int[r.getHeight() * r.getWidth()];
		r.getSamples(0, 0, r.getWidth(), r.getHeight(), 0, samples);
		for (int i = 0; i < samples.length; i++) {
			if (samples[i] < value)
				samples[i] = 0;
			else
				samples[i] = 255;
		}
		r.setSamples(0, 0, r.getWidth(), r.getHeight(), 0, samples);

	}

	public static void globalAdaptive(int initValue, int diff, BufferedImage image)
			throws UnsupportedOperationException {
		if (image.getType() != BufferedImage.TYPE_BYTE_GRAY)
			throw new UnsupportedOperationException(
					"only grayscale images are allowed");
		
		WritableRaster r = image.getRaster();
		int[] samples = new int[r.getHeight() * r.getWidth()];
		r.getSamples(0, 0, r.getWidth(), r.getHeight(), 0, samples);
		
		float avg0 = 0, sum0 = 0, avg1 = 0, sum1 = 0, tr1=initValue, tr2=initValue;
		do{
			tr1 = tr2;
			for (int i = 0; i < samples.length; i++) {
				if (i< tr1){
					avg0 += samples[i]*i;
					sum0 += samples[i];
				}
				else{
				    avg1 += samples[i]*i;
				    sum1 += samples[i];
				}
			}
			avg0 /= sum0;
			avg1 /= sum1;
			
			tr2 = (avg0+avg1)/2;
			
		}while(Math.abs(tr2 - tr1) > 1);
		System.out.println(tr2);
	}
	
	
}
