package algorithm;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import org.jdesktop.swingx.JXGraph;

import components.GraphComp;

public class Histogram {
	protected int nBands;
	protected int[][] values;
	protected GraphComp[] graphs;
	
	private int[] maxYs;
	public Histogram(BufferedImage image) {
		nBands = image.getRaster().getNumBands();
		values = new int[nBands][256];
		graphs = new GraphComp[nBands];
		maxYs = new int[nBands];
		
		Raster r = image.getRaster();
		for (int i = 0; i < nBands; i++) {
			int maxY = 0;
			int[] samples = new int[r.getHeight() * r.getWidth()];
			r.getSamples(0, 0, r.getWidth(), r.getHeight(), i, samples);
			for (int j = 0; j < samples.length; j++) {
				values[i][samples[j]]++;
				if (maxY < values[i][samples[j]]) {
					maxY = values[i][samples[j]];
				}
			}
			maxYs[i] = maxY;
			graphs[i] = new GraphComp(values[i]);
		}
	}
	
	public Histogram(BufferedImage image, int x, int y, int w, int h) {
		nBands = image.getRaster().getNumBands();
		values = new int[nBands][256];
		graphs = new GraphComp[nBands];
		maxYs = new int[nBands];
		
		Raster r = image.getRaster();
		for (int i = 0; i < nBands; i++) {
			int maxY = 0;
			int[] samples = new int[w * h];
			r.getSamples(x, y, w, h, i, samples);
			for (int j = 0; j < samples.length; j++) {
				values[i][samples[j]]++;
				if (maxY < values[i][samples[j]]) {
					maxY = values[i][samples[j]];
				}
			}
			maxYs[i] = maxY;
			graphs[i] = new GraphComp(values[i]);
		}
	}
	
	public int[] getValues(int b){
		return values[b];
	}
	
	public void setTreshold(int b, int val){
		graphs[b].setTreshold(val);
		
	}
	
	@Override
	public String toString() {
		String ostr = "";

		for (int i = 0; i < nBands; i++) {
			ostr += "\n band " + i + " :\n";
			for (int j = 0; j < values[i].length; j++) {
				ostr += "" + j + ": " + values[i][j] + "\n";
			}
		}

		return ostr;
	}

	public void draw(int band) {
		JFrame frame = new JFrame("plot of " + band);
		JScrollPane scrollPane = new JScrollPane(graphs[band]);
		frame.add(scrollPane);
		frame.pack();
		frame.setVisible(true);
	}
	
	public void draw(){
		draw(0);
	}
	
	public static void main(String[] args) {
		try {
			BufferedImage img = ImageIO.read(new File(
					"images/mars.bmp"));
			Histogram h = new Histogram(img);
			h.setTreshold(0, 210);
			// System.out.println(h);
			h.draw(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
