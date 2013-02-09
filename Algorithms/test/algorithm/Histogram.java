package algorithm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import org.jdesktop.swingx.JXGraph;

public class Histogram {
	protected int nBands;
	protected int[][] values;
	protected JXGraph[] graphs;
	
	public Histogram(BufferedImage image){
		nBands = image.getRaster().getNumBands();
		values = new int[nBands][256];
		graphs = new JXGraph[nBands];
		
		Raster r = image.getRaster();
		for (int i = 0; i < nBands; i++) {
			int maxY = 0;
			int[] samples = new int[r.getHeight() * r.getWidth()];
			r.getSamples(0, 0, r.getWidth(), r.getHeight(), i, samples);
			for (int j = 0; j < samples.length; j++) {
				values[i][samples[j]]++;
				if(maxY < values[i][samples[j]]){
					maxY = values[i][samples[j]];
				}
			}
			graphs[i] = new JXGraph(0, 0, 0, 256, 0, maxY, 32, 8, maxY/10, maxY/15);
			graphs[i].addPlots(Color.BLACK, new Values(i));
			System.out.println(maxY);
		}
	}
	@Override
	public String toString() {
		String ostr = "";
		
		for (int i = 0; i < nBands; i++) {
			ostr += "\n band "+i+" :\n";
			for (int j = 0; j < values[i].length; j++) {
				ostr += ""+j+": "+values[i][j]+"\n";
			}
		}
		
		return ostr;
	}
	
	
	class Values extends JXGraph.Plot{
		public Values(int band){
			super();
			this.band = band;
		}
		int band;
		@Override
		public double compute(double value) {
			if(value < 0.d){
				return 0;
			}
			int val = (int)value;
			return values[band][val];
		}
		
	}
	
	public void draw(int band){
		 JFrame frame = new JFrame("plot of "+band);
		 frame.add(graphs[band]);
		 frame.setPreferredSize(graphs[band].getPreferredSize());
		 frame.pack();
		 frame.setVisible(true);
	}
	
	public static void main(String[] args){
		try {
			BufferedImage img = ImageIO.read(new File("images/whiteboard/wb01.jpg"));
			Histogram h = new Histogram(img);
			//System.out.println(h);
			h.draw(2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
