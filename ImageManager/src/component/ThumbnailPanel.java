package component;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.JPanel;

public class ThumbnailPanel extends JPanel {
		
	class ThumbnailLayout implements LayoutManager{
		
		// wartosci odpowiadaja aktualnemu rozmiarowi miniaturki
		private int minCompWidth, minCompHeight, maxCompWidth, maxCompHeight, compWidth = -1, compHeight = -1;
		
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
			for (int i = 0; i < compsInCol; i++) {
				for (int j = 0; j < compsInRow; j++) {
					comps[i * compsInRow + j].setBounds(j * compWidth, i
							* compHeight, compWidth, compHeight);
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
