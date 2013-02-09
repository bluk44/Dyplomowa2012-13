package components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class ImageComponent extends JComponent {
	
	protected BufferedImage image;
	
	public ImageComponent(){
		
	}
	
	public ImageComponent(File imageFile) throws IOException{
		loadImage(imageFile);
	}
	
	public ImageComponent(BufferedImage image){
		setImage(image);
	}
	
	public void loadImage(File imageFile) throws IOException{
		image = ImageIO.read(imageFile);
		fitToImage();
	}
	
	public void setImage(BufferedImage image){
		this.image = image;
		fitToImage();
	}
	
	public BufferedImage getImage(){
		return image;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(image, 0, 0, null);
	}
	
	private void fitToImage(){
		if(image == null){
			return;
		}
		setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
		repaint();
	}
}
