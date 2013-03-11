package components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

import algorithm.Histogram;

public class AdaptiveTresholdingData extends JComponent implements MouseListener{

	/**
	 * @param args
	 */
	private BufferedImage image;
	int[] xSections, ySections;
	Histogram[][] histograms;	
	public AdaptiveTresholdingData(int xSec, int ySec) {
		histograms = new Histogram[ySec][xSec];
		addMouseListener(this);
	}
	public BufferedImage getImage() {
		return image;
	}
	
	public void setImage(BufferedImage image) {
		this.image = image;
		setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
	}

	public int[] getxSections() {
		return xSections;
	}

	public void setxSections(int[] xSections) {
		this.xSections = xSections;
	}

	public int[] getySections() {
		return ySections;
	}

	public void setySections(int[] ySections) {
		this.ySections = ySections;
	}
	
	public void addHistogram(Histogram h, int x, int y){
		histograms[y][x] = h;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (image != null) {
			g.drawImage(image, 0, 0, null);
		}
		if (xSections != null && ySections != null) {
			g.setColor(Color.BLUE);
			for (int i = 0; i < ySections.length; i++) {
				g.drawLine(0, ySections[i], image.getWidth(), ySections[i]);
			}
			for (int j = 0; j < xSections.length; j++) {
				g.drawLine(xSections[j], 0, xSections[j], image.getHeight());				
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("mouse clicked");
		int xClick = e.getX(), yClick = e.getY();
		int xHist = xClick, j=-1, yHist = yClick, i = -1;
		for (int k = 0; k < xSections.length; k++) {
			int t = xClick - xSections[k];
			if(t < 0){
				j = k-1;
				break;
			}
		}
		if(j < 0) j = xSections.length-1;
		
		for (int k = 0; k < ySections.length; k++) {
			int t = yClick - ySections[k];
			if(t < 0){
				i = k-1;
				break;
			}
		}
		if(i < 0) i = ySections.length-1;
		
//		System.out.println("xClick: "+xClick+" yClick: "+yClick);
//		System.out.print("xSections: ");
//		for (int k = 0; k < xSections.length; k++) {
//			System.out.print(""+xSections[k]+" ");
//		}
//		System.out.println();
//		System.out.print("ySections: ");
//		for (int k = 0; k < ySections.length; k++) {
//			System.out.print(""+ySections[k]+" ");
//		}
//		System.out.println("i: "+i+"j: "+j);
		System.out.println("histogram of sector "+i+" "+j);
		histograms[i][j].draw();
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
