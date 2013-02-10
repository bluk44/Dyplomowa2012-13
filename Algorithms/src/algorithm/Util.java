package algorithm;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

public abstract class Util {
	
	 public static BufferedImage grayScale(BufferedImage src){
		BufferedImage dst = new BufferedImage(src.getWidth(), src.getHeight(),
				BufferedImage.TYPE_BYTE_GRAY);
		WritableRaster srcR = src.getRaster(), dstR = dst.getRaster();
		int h = src.getHeight(), w = src.getWidth();
		for(int i=0;i<h;i++){
			for(int j=0;j<w;j++){
				int samp = 0;
				for(int k=0;k<srcR.getNumBands();k++){
					samp += srcR.getSample(j, i, k);
				}
				samp /= srcR.getNumBands();
				dstR.setSample(j, i, 0, samp);
			}
		}
		return dst;
	}
}
