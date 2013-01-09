package component;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.Scrollable;

public class ThumbnailPanel extends JPanel implements Scrollable{
	
	private static int DEF_COMP_WIDTH = 100, DEF_COMP_HEIGHT = 100;
	public ThumbnailPanel(){
		this.setLayout(new ThumbnailLayout());
		((ThumbnailLayout)getLayout()).setThumbnailDimension(new Dimension(DEF_COMP_WIDTH, DEF_COMP_HEIGHT));
	}
	
	public void rescaleThumbnails(Dimension dim){
		((ThumbnailLayout)getLayout()).setThumbnailDimension(dim);
		getLayout().layoutContainer(this);
	}
	class ThumbnailLayout implements LayoutManager{
		
		// wartosci odpowiadaja aktualnemu rozmiarowi miniaturki
		private Dimension compDim;
		@Override
		public void addLayoutComponent(String name, Component comp) {}

		@Override
		public void removeLayoutComponent(Component comp) {}

		@Override
		public Dimension preferredLayoutSize(Container parent) {
			return null;
		}

		@Override
		public Dimension minimumLayoutSize(Container parent) {
			return null;
		}

		@Override
		public void layoutContainer(Container parent) {
			System.out.println("layoutContainer called");
			fitComponents(parent);
		}
		
		public void setThumbnailDimension(Dimension thDim){
			this.compDim = thDim;
		}
		
		public Dimension getThumbnailDimension(){
			return compDim;
		}
		
		private void fitComponents(Container parent){
			if(parent.getComponentCount() == 0){
				return;
			}
			
			int compsCount = parent.getComponentCount();
			System.out.println(compsCount);
			int panelWidth = parent.getWidth(), panelHeight = parent.getHeight();
			// obliczyc szerokosc przypadajaca na komponent
						
			// obliczyc ile komponentow w rzedzie i skorygowac szerokosc panelu
			int compsInRow = panelWidth / compDim.width;
			int leftPixels = panelWidth % compDim.width;
			int addPixels = leftPixels / compsInRow;
			
			panelWidth = compsInRow * compDim.width;
			
			// obliczyc ile komponentow w kolumnie i wysokosc panelu
			int compsInCol = compsCount / compsInRow;
			compsInCol += ((compsCount % compsInRow) > 0) ? 1 : 0;
			
			panelHeight = compsInCol * compDim.height;
			
			parent.setPreferredSize(new Dimension(panelWidth, panelHeight));
			
			// wyznaczyc polozenie komponentow
			
			Component[] comps = parent.getComponents();
			int i = 0, j = 0;
			for (Component c : comps) {
				c.setBounds(j * (compDim.width+addPixels), i * compDim.height, compDim.width+addPixels, compDim.height);
				++j;
				if (j == compsInRow) {
					j = 0;
					++i;
				}
			}
			
		}
			
	}

	@Override
	public Dimension getPreferredScrollableViewportSize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getScrollableUnitIncrement(Rectangle visibleRect,
			int orientation, int direction) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getScrollableBlockIncrement(Rectangle visibleRect,
			int orientation, int direction) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getScrollableTracksViewportWidth() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean getScrollableTracksViewportHeight() {
		// TODO Auto-generated method stub
		return false;
	}

}
