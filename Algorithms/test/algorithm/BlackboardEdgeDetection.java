package algorithm;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import components.ImageComponent;

public class BlackboardEdgeDetection {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			BufferedImage img = ImageIO.read(new File(
					"images/blackboard/bb03.JPG"));
			// BufferedImage img = ImageIO.read(new
			// File("images/whiteboard/wb_small.bmp"));
			BufferedImage grey = Util.grayScale(img);
			BufferedImage inv = Util.inverse(grey);
			Blur.gauss55(inv);
			Blur.gauss55(inv);
			Blur.gauss55(inv);

			EdgeDetection.both(inv, 50);
//			ImageIO.write(grey, "bmp", new File("images/blackboard/after.jpg"));

			ImageComponent ic = new ImageComponent(inv);
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
