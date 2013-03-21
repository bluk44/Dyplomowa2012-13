package algorithm;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import components.ImageComponent;

public class BlurTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			BufferedImage img = ImageIO.read(new File("images/conv_test/domek_lap.bmp"));
			BufferedImage img2 = BasicOperations.copy(img);
			Blur.gauss55(img);
			for(int i = 0; i < 100; i ++){
				Blur.gauss55(img);
			}

			Blur.gauss33(img2);
			
			ImageComponent ic = new ImageComponent(img);
			ImageComponent ic2 = new ImageComponent(img2);
			
			JFrame frame2 = new JFrame("effect of gaussian blur 33 ");
			frame2.getContentPane().add(ic2);
			frame2.pack();
			frame2.setVisible(true);
			
			JFrame frame = new JFrame("effect of gaussian blur 55 ");
			frame.getContentPane().add(ic);
			frame.pack();
			frame.setVisible(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
