package jai;

import java.awt.image.renderable.ParameterBlock;
import java.util.Properties;

import javax.media.jai.BorderExtender;
import javax.media.jai.BorderExtenderConstant;
import javax.media.jai.JAI;
import javax.media.jai.RenderableOp;
import javax.media.jai.RenderedOp;
import javax.media.jai.operator.BorderDescriptor;
import javax.swing.JFrame;

public class RenderedGraphsTest {
	static {
		Properties p = new Properties(System.getProperties());
		p.put("com.sun.media.jai.disableMediaLib", "true");
		System.setProperties(p);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// metody tworzenia grafu:
		
		// nie jawna
		// kazde wywolanie metody klasy z RenderedImage na RenderedOp spowoduje stworzenie nowej instancji 
		// nowej instancji RenderedOp
		
		// jawna
		// wywolanie createInstane powoduje stworzenie nowego zdjecia
		// czyli tworzy kopie tej czesci grafu wraz z zrodlami 
		
		// testowanie splotu
		
		// zaladowanie obrazka
		RenderedOp loadedImage = JAI.create("fileload", "images/kolo.bmp");
		
		// dodanie obwodki 
		Integer borderSize = new Integer(16);
		BorderExtender extenderInst = BorderExtender.createInstance(BorderExtender.BORDER_WRAP);
		RenderedOp bordered = JAI.create("border", loadedImage, borderSize, borderSize, borderSize, borderSize, extenderInst);
				
		// efekt koncowy
		RenderedOp finalImage = bordered;

		RenderableOp ro;
		JFrame frame = new JFrame("graphs test");
		ImgComp ic = new ImgComp(finalImage);
		frame.getContentPane().add(ic);
		frame.pack();
		frame.setVisible(true);
	}
	
	
}
