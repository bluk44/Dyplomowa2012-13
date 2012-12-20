package algorithm;

import java.awt.image.BufferedImage;

public class BasicOperations {
	
	static int[] LUT;
	
	static int DEF_SAMPLE_SIZE = 256;
	/**
	 * negatyw obrazka w czarno - bialego
	 * 
	 * @param original
	 * @return
	 */
	public static BufferedImage negative(BufferedImage original) {
		allocateLUT(DEF_SAMPLE_SIZE);
		for(int i=0;i<LUT.length;i++){
			LUT[i] = 255 - i;
		}

		return transform(original);
	}

	public static BufferedImage treshold(BufferedImage original, int value) {
		allocateLUT(DEF_SAMPLE_SIZE);
		for(int i=0;i<LUT.length;i++){
			if(i < value){
				LUT[i] = 0;
			} else {
				LUT[i] = 255;
			}
		}
		
		return transform(original);
	}

	public static BufferedImage changeBrightness(BufferedImage original,
			int value) {
		allocateLUT(DEF_SAMPLE_SIZE);	
		if (value > 0) {
			for(int i=0;i<LUT.length;i++){
				LUT[i] = (i + value < 255) ? i + value : 255;
			}
		} else {
			for(int i=0;i<LUT.length;i++){
				LUT[i] = (i + value > 0) ? i + value : 0;
			}			
		} 
		return transform(original);
	}

	public static BufferedImage changeContrast(BufferedImage original, int value){
		allocateLUT(DEF_SAMPLE_SIZE);
		int m = (DEF_SAMPLE_SIZE -1)/ 2, x;
		
		for(int i = 0; i < LUT.length; i++){
			x = value*i -value*m + m;
			if(x < 0){
				LUT[i] = 0;
			} else if(x > DEF_SAMPLE_SIZE -1){
				LUT[i] = DEF_SAMPLE_SIZE -1;
			} else {
				LUT[i] = x;
			}
		}
		return transform(original);
	}
	
	public static BufferedImage copy(BufferedImage original) {
		BufferedImage copy = new BufferedImage(original.getWidth(),
				original.getHeight(), original.getType());
		original.copyData(copy.getRaster());

		return copy;
	}
	
	private static void allocateLUT(int sampleSize){
		LUT = new int[sampleSize];
	}
	
	private static BufferedImage transform(BufferedImage original){
		BufferedImage copy = copy(original);
		int[] iArray = new int[original.getWidth() * original.getHeight()];
		copy.getRaster().getSamples(0, 0, copy.getWidth(), copy.getHeight(), 0,
				iArray);
		for(int i=0; i< iArray.length; i++){
			iArray[i] = LUT[iArray[i]];
		}
		copy.getRaster().setSamples(0, 0, copy.getWidth(), copy.getHeight(), 0,
				iArray);

		return copy;
	}
}
