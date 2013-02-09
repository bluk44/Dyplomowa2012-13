package jai;

import java.awt.image.Kernel;

import javax.media.jai.JAI;
import javax.media.jai.KernelJAI;
import javax.media.jai.RenderedOp;

public class ConvolveTest {
	static int KERNEL_WIDTH = 3, KERNEL_HEIGHT = 3;
	/**
	 * @param args
	 */
	public static void main(String[] args){
		float[] lowPass = {0.111f, 0.111f, 0.111f, 0.111f, 0.111f, 0.111f, 0.111f, 0.111f, 0.111f};
		
		Kernel k = new Kernel(KERNEL_WIDTH, KERNEL_HEIGHT, lowPass);
		KernelJAI kernel = new KernelJAI(k);
		
		RenderedOp img = JAI.create("fileload", "images/kolo.bmp");
		RenderedOp conv = JAI.create("convolve", img, kernel);
		
		conv.getAsBufferedImage();
		
	}

}
