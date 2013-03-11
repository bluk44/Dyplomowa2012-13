package jai;

import java.awt.image.BufferedImage;
import java.awt.image.Kernel;
import java.util.Properties;

import javax.media.jai.JAI;
import javax.media.jai.KernelJAI;
import javax.media.jai.RenderedOp;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class ConvolveTest {
	static {
		Properties p = new Properties(System.getProperties());
		p.put("com.sun.media.jai.disableMediaLib", "true");
		System.setProperties(p);
	}
	static int KERNEL_WIDTH = 3, KERNEL_HEIGHT = 3;
	/**
	 * @param args
	 */
	public static void main(String[] args){
		float[] lowPass = {0.111f, 0.111f, 0.111f, 0.111f, 0.111f, 0.111f, 0.111f, 0.111f, 0.111f};
		float[] lap = { 0, -1, 0, -1, 4, -1, 0, -1, 0 };
		Kernel k = new Kernel(3, 3, lap);
		KernelJAI kernel = new KernelJAI(k);
		
		RenderedOp img = JAI.create("fileload", "images/kolo.bmp");
		RenderedOp conv = JAI.create("convolve", img, kernel);
		
		BufferedImage after = conv.getAsBufferedImage();
		
		JFrame frame = new JFrame("convolve test");
		ImgComp ic = new ImgComp(after);
		JScrollPane scrollPane = new JScrollPane(ic);

		frame.getContentPane().add(scrollPane);
		frame.pack();
		frame.setVisible(true);

	}

}
