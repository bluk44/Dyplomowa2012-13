package algorithm;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class LocalAdaptiveTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BufferedImage image;
		try {
			image = ImageIO.read(new File(
					"images/17171010.bmp"));
			Treshold.localAdaptive(0, 0, image, 10, 10);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
