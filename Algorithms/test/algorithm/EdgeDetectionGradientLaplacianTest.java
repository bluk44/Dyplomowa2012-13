package algorithm;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import components.ImageComponent;

public class EdgeDetectionGradientLaplacianTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			BufferedImage img = ImageIO.read(new File("images/whiteboard/wb01.jpg"));
//			BufferedImage img = ImageIO.read(new File("images/whiteboard/wb_small.bmp"));
			BufferedImage grey = Util.grayScale(img);
			Blur.gauss55(grey);
//			Blur.gauss55(grey);
 //    		Blur.gauss55(grey);

			EdgeDetection.both(grey, 20);
			ImageIO.write(grey, "bmp", new File("images/whiteboard/after.jpg"));

			ImageComponent ic = new ImageComponent(grey);
			JFrame frame = new JFrame("image");
			JScrollPane sp = new JScrollPane(ic);
			frame.getContentPane().add(sp);
			frame.pack();
			frame.setVisible(true);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
