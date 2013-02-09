package algorithm;

import jai.ImgComp;

import java.awt.image.BufferedImage;

import javax.media.jai.JAI;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class TresholdTesting {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// zaladowanie obrazka
		BufferedImage loadedImage = JAI.create("fileload",
				"images/whiteboard/wb06.jpg").getAsBufferedImage();
		
		BufferedImage gray = Util.grayScale(loadedImage);
		Treshold.global(128, gray);
		JFrame frame = new JFrame("treshold test");
		ImgComp ic = new ImgComp(gray);
		JScrollPane scrollPane = new JScrollPane(ic);
		
		frame.getContentPane().add(scrollPane);
		frame.pack();
		frame.setVisible(true);
	}

}
