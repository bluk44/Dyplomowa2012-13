package jai;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.Properties;

import javax.media.jai.JAI;
import javax.media.jai.KernelJAI;
import javax.media.jai.PlanarImage;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class EdgeDetectionTest {
	static {
		Properties p = new Properties(System.getProperties());
		p.put("com.sun.media.jai.disableMediaLib", "true");
		System.setProperties(p);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// zaladowanie obrazka
		PlanarImage loadedImage = JAI.create("fileload", "images/whiteboard/wb03.jpg");
		BufferedImage pre = loadedImage.getAsBufferedImage();

		// zamiana na odcienie szarosci
		BufferedImage gray = getGray(pre);
		
		// filtr gaussa
		
		float[] gauss = {0, 0.01f, 0.02f,0.01f,0,0.01f, 0.02f,0.06f, 0.1f,0.1f, 0.16f,0.06f, 0.1f,0.01f, 0.02f,
						 0.01f,0.06f,0.1f,0.06f,0.01f,0f,0.01f,0.02f,0.01f,0f};
		KernelJAI gaussKernel = new KernelJAI(5, 5, gauss);
		PlanarImage filtered = JAI.create("convolve", gray, gaussKernel);
		
		// wyznaczenie gradientu
		

		float chuj[] = {1,1,1, 1,-8,1, 1,1,1,};
		KernelJAI kernel = new KernelJAI(3, 3, chuj);
		KernelJAI kernel2 = new KernelJAI(3, 3, chuj);

		PlanarImage grad = JAI.create("gradientmagnitude", gray,
				kernel);

		JFrame frame = new JFrame("graphs test");
		ImgComp ic = new ImgComp(grad);
		JScrollPane scrollPane = new JScrollPane(ic);

		frame.getContentPane().add(scrollPane);
		frame.pack();
		frame.setVisible(true);
	}

	private static BufferedImage getGray(BufferedImage src) {
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
				samp /= 3;
				dstR.setSample(j, i, 0, samp);
			}
		}
		return dst;
	}

}
