package algorithm;

import jai.ImgComp;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.media.jai.JAI;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import components.AdaptiveTresholdingData;

public class LocalAdaptiveTestFinal {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			BufferedImage loadedImage;
			loadedImage = ImageIO.read(new File("images/blackboard/bb01.JPG"));
			BufferedImage gray = Util.grayScale(loadedImage);
			BufferedImage gray2 = Util.grayScale(loadedImage);
			int xSec = 5, ySec = 5;
			AdaptiveTresholdingData opData = new AdaptiveTresholdingData(xSec, ySec);
			Treshold.localAdaptive(128, 1, gray, xSec, ySec, opData);
			//Treshold.globalAdaptive(128, 1, gray2);
			JFrame frame = new JFrame("local treshold test"), frame2 = new JFrame("before");
			ImgComp ic = new ImgComp(loadedImage);
			
			JScrollPane scrollPane = new JScrollPane(opData), scrollPane2 = new JScrollPane(ic);
			
			frame.getContentPane().add(scrollPane);
			frame2.getContentPane().add(scrollPane2);
			frame.pack();
			frame2.pack();
			frame.setVisible(true);
			frame2.setVisible(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
