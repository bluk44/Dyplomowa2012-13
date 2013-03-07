package algorithm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
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
	protected HistGraph[] graphs;
	
	private int[] maxYs;
	public Histogram(BufferedImage image) {
		nBands = image.getRaster().getNumBands();
		values = new int[nBands][256];
		graphs = new HistGraph[nBands];
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
			graphs[i] = new HistGraph(0, 0, 0, 255, 0, maxY, 32, 8, maxY / 10,
					maxY / 15, i);
			graphs[i].addPlots(Color.BLACK, new Values(i));
		}
	}
	
	public int[] getValues(int b){
		return values[b];
	}
	
	public void setTreshold(int b, int val){
		graphs[b].treshold = val;
		
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

	class Values extends JXGraph.Plot {
		public Values(int band) {
			super();
			this.band = band;
		}

		int band;

		@Override
		public double compute(double value) {
			if (value < 0.d || value > 255) {
				return 0;
			}
			int val = (int) value;
			return values[band][val];
		}

	}

	class HistGraph extends JXGraph {
		int b;
		int treshold = -1;
		public HistGraph(double originX, double originY, double minX,
				double maxX, double minY, double maxY, double majorX,
				int minorCountX, double majorY, int minorCountY, int b) {
			super(originX, originY, minX, maxX, minY, maxY, majorX, minorCountX, majorY, minorCountY);
			this.b = b;
		}
		
		@Override
		protected void paintExtra(Graphics2D g2) {
			if(treshold < 0) return;
			g2.setColor(Color.RED);
			g2.drawLine((int)xPositionToPixel(treshold), 0, (int)xPositionToPixel(treshold), maxYs[b]);
			
		}
	}

	public void draw(int band) {
		JFrame frame = new JFrame("plot of " + band);
		frame.add(graphs[band]);
		frame.setPreferredSize(graphs[band].getPreferredSize());
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		try {
			BufferedImage img = ImageIO.read(new File(
					"images/half.bmp"));
			Histogram h = new Histogram(img);
			h.setTreshold(0, 255);
			// System.out.println(h);
			h.draw(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
