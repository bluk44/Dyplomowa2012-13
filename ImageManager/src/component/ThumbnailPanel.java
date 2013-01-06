package component;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.Scrollable;

public class ThumbnailPanel extends JPanel implements Scrollable{
	
	public ThumbnailPanel(){
		this.setLayout(new ThumbnailLayout());
	}
	
	class ThumbnailLayout implements LayoutManager{
		
		// wartosci odpowiadaja aktualnemu rozmiarowi miniaturki
		private int compWidth = 100, compHeight = 100;
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
			fitComponents(parent);
		}
		
		private void fitComponents(Container parent){
			if(parent.getComponentCount() == 0){
				return;
			}
			
			int compsCount = parent.getComponentCount();
			int panelWidth = parent.getWidth(), panelHeight = parent.getHeight();
			// obliczyc szerokosc przypadajaca na komponent
						
			// obliczyc ile komponentow w rzedzie i skorygowac szerokosc panelu
			int compsInRow = panelWidth / compWidth;
			int leftPixels = panelWidth % compWidth;
			int addPixels = leftPixels / compsInRow;
			
			panelWidth = compsInRow * compWidth;
			
			// obliczyc ile komponentow w kolumnie i wysokosc panelu
			int compsInCol = compsCount / compsInRow;
			compsInCol += ((compsCount % compsInRow) > 0) ? 1 : 0;
			
			panelHeight = compsInCol * compHeight;
			
			parent.setPreferredSize(new Dimension(panelWidth, panelHeight));
			
			// wyznaczyc polozenie komponentow
			
			Component[] comps = parent.getComponents();
			int i = 0, j = 0;
			for (Component c : comps) {
				c.setBounds(j * (compWidth+addPixels), i * compHeight, compWidth+addPixels, compHeight);
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
