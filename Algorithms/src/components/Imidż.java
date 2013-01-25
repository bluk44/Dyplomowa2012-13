package components;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

public class Imidż extends JButton {
	
	/**
	 * @param args
	 */
	BufferedImage image = null;
	{
		try {
			image = ImageIO.read(new File("images/kolo.bmp"));
		} catch (IOException e) {
			System.out.println("file not found");
		} 
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, null);
	}


	public BufferedImage getImage() {
		return image;
	}


	public void setImage(BufferedImage image) {
		this.image = image;
		repaint();
	}
}
