package component;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.JPanel;

public class ThumbnailPanel extends JPanel {
	
	public ThumbnailPanel(){
		this.setLayout(new ThumbnailLayout());
	}
	
	class ThumbnailLayout implements LayoutManager{
		
		// wartosci odpowiadaja aktualnemu rozmiarowi miniaturki
		private int minCompWidth = 50, minCompHeight = 50, maxCompWidth = 75, maxCompHeight = 75, compWidth = -1, compHeight = -1;
		
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
			
			// pierwsze uruchomienie
			if(compWidth == -1 && compHeight == -1){
				compWidth = maxCompWidth;
				compHeight = maxCompHeight;
			}
			
			// obliczyc ile komponentow w rzedzie i skorygowac szerokosc panelu
			int compsInRow = panelWidth / compWidth;
			panelWidth = compsInRow * compWidth;
			
			// obliczyc ile komponentow w kolumnie i wysokosc panelu
			int compsInCol = compsCount / compsInRow;
			compsInCol += ((compsCount % compsInRow) > 0) ? 1 : 0;
			
			panelHeight = compsInCol * compHeight;
			
			parent.setSize(panelWidth, panelHeight);
			
			// wyznaczyc polozenie komponentow
			
			Component[] comps = parent.getComponents();
			int i = 0, j = 0;
			for (Component c : comps) {
				c.setBounds(j * compWidth, i * compHeight, compWidth, compHeight);
				++j;
				if (j == compsInRow) {
					j = 0;
					++i;
				}
			}
			
		}
		
	public void setComponentLimits(int minWidth, int minHeight, int maxWidth, int maxHeight, int width, int height){
		
	}

	public void setComponentLimits(int minWidth, int minHeight, int maxWidth, int maxHeight){
		setComponentLimits(minWidth, minHeight, maxWidth, maxHeight, -1, -1);
	}
	
	}
}
