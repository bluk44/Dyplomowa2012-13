package component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

public class ThumbnailComponent extends JButton {

	private Image image;
	private int imageX, imageY;
	public Dimension imageArea;
	private int imageAreaX, imageAreaY;
	
	private Image origin = new BufferedImage(300, 150, BufferedImage.TYPE_INT_RGB);
	private Image fake;
	
	public void rescaleImage2(double factor){
		fake = origin.getScaledInstance((int) (origin.getWidth(null) * factor), -1, BufferedImage.SCALE_FAST);
		fitImageArea2();
	}
	
	private void fitImageArea2(){
		int a = (fake.getHeight(null) < fake.getWidth(null)) ? fake.getWidth(null) : fake.getHeight(null);
		imageArea = new Dimension(a,a);
		imageX = (imageArea.width - fake.getWidth(null)) / 2;
		imageY = (imageArea.height - fake.getHeight(null)) / 2;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		 g.setColor(Color.BLUE);
		 g.drawRect(imageAreaX, imageAreaY, imageArea.width, imageArea.height);
		 g.drawImage(fake, imageAreaX + imageX, imageAreaY + imageY, null);
	}
	
	public ThumbnailComponent(){
		try {
			image = ImageIO.read(new File("image/street_RGB.bmp"));
			rescaleImage2(0.25);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void rescaleImage(double factor){
		image = image.getScaledInstance((int) (image.getWidth(null) * factor), -1, BufferedImage.SCALE_FAST);
		fitImageArea();
	}
	
	private void fitImageArea(){
		int a = (image.getHeight(null) < image.getWidth(null)) ? image.getWidth(null) : image.getHeight(null);
		imageArea = new Dimension(a,a);
		imageX = (imageArea.width - image.getWidth(null)) / 2;
		imageY = (imageArea.height - image.getHeight(null)) / 2;
	}
	
	@Override
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		imageAreaX = (width -  imageArea.width) / 2;
		imageAreaY = (height - imageArea.height) / 2;
	}
	
//	@Override
//	public void paintComponent(Graphics g) {
//		 g.setColor(Color.BLUE);
//		 g.drawRect(imageAreaX, imageAreaY, imageArea.width, imageArea.height);
//		 g.drawImage(image, imageAreaX + imageX, imageAreaY + imageY, null);
//	}
}
