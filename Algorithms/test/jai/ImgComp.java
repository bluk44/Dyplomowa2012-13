package jai;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;

import javax.media.jai.PlanarImage;
import javax.swing.JLabel;

public class ImgComp extends JLabel {

	BufferedImage image;
	
	public ImgComp(){};
	
	public ImgComp(PlanarImage image){
		this.image = image.getAsBufferedImage();
		this.setPreferredSize(new Dimension(this.image.getWidth(), this.image.getHeight()));
	}
	
	public ImgComp(BufferedImage image){
		this.image = image;
		this.setPreferredSize(new Dimension(this.image.getWidth(), this.image.getHeight()));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, null);
	}


	public BufferedImage getImage() {
		return image;
	}


	public void setImage(PlanarImage image) {
		this.image = image.getAsBufferedImage();
		this.setPreferredSize(new Dimension(this.image.getWidth(), this.image.getHeight()));
		repaint();
	}	
	public void setImage(BufferedImage  image) {
		this.image = image;
		this.setPreferredSize(new Dimension(this.image.getWidth(), this.image.getHeight()));
		repaint();
	}
}
