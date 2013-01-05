package component;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.JPanel;

public class ThumbnailPanel extends JPanel {
		
	class ThumbnailLayout implements LayoutManager{
		
		private static final int minCompWidth = 50, minCompHeight = 50;
		
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
		private void fitComponents(Container parent){
			if(parent.getComponentCount() == 0){
				return;
			}
			
			int width = parent.getWidth(), height = parent.getHeight();
			int compsInRow = 10, compsInCol = 10;
		}
		@Override
		public void layoutContainer(Container parent) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
