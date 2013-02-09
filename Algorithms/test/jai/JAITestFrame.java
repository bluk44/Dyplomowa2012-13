package jai;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.Kernel;
import java.awt.image.Raster;
import java.awt.image.SampleModel;
import java.awt.image.WritableRaster;
import java.util.Properties;

import javax.media.jai.BorderExtender;
import javax.media.jai.JAI;
import javax.media.jai.KernelJAI;
import javax.media.jai.RenderedOp;
import javax.media.jai.operator.BorderDescriptor;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class JAITestFrame extends JFrame {
	{
		Properties p = new Properties(System.getProperties());
		p.put("com.sun.media.jai.disableMediaLib", "true");
		System.setProperties(p);
	}
	
	// convolution test
	static int KERNEL_WIDTH = 3, KERNEL_HEIGHT = 3;
	static int KERNEL_RAD_W = KERNEL_WIDTH / 2, KERNEL_RAD_H = KERNEL_HEIGHT / 2;
	
//	float[] lowPass = {0.1234f, 0.01234f, 0.01234f, 0.01234f, 0.01234f, 0.01234f, 0.01234f, 0.01234f, 0.01234f,
//			0.01234f, 0.01234f, 0.01234f, 0.01234f, 0.01234f, 0.01234f, 0.01234f, 0.01234f, 0.01234f,
//			0.01234f, 0.01234f, 0.01234f, 0.01234f, 0.01234f, 0.01234f, 0.01234f, 0.01234f, 0.01234f,
//			0.01234f, 0.01234f, 0.01234f, 0.01234f, 0.01234f, 0.01234f, 0.01234f, 0.01234f, 0.01234f,
//			0.01234f, 0.01234f, 0.01234f, 0.01234f, 0.01234f, 0.01234f, 0.01234f, 0.01234f, 0.01234f,
//			0.01234f, 0.01234f, 0.01234f, 0.01234f, 0.01234f, 0.01234f, 0.01234f, 0.01234f, 0.01234f,
//			0.01234f, 0.01234f, 0.01234f, 0.01234f, 0.01234f, 0.01234f, 0.01234f, 0.01234f, 0.01234f,
//			0.01234f, 0.01234f, 0.01234f, 0.01234f, 0.01234f, 0.01234f, 0.01234f, 0.01234f, 0.01234f,
//			0.01234f, 0.01234f, 0.01234f, 0.01234f, 0.01234f, 0.01234f, 0.01234f, 0.01234f, 0.01234f};
	float[] lowPass ={0.111f, 0.111f, 0.111f, 0.111f, 0.111f, 0.111f, 0.111f, 0.111f, 0.111f, 0.111f, 0.111f };
	KernelJAI kernel = new KernelJAI(new Kernel(KERNEL_WIDTH, KERNEL_HEIGHT, lowPass));

	RenderedOp img = JAI.create("fileload", "images/kolo.bmp");

	RenderedOp conv = null;
	BorderDescriptor bo = new BorderDescriptor();
	
	
	ImgComp before = new ImgComp(img);
	ImgComp after = new ImgComp();
	
	public JAITestFrame() {
		
		
		JButton btnFilter = new JButton("filter");
		btnFilter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			// kopiuje do zwyklego rastra
			//Raster rast =  img.getExtendedData(new Rectangle(-1,0-1,img.getWidth()+1, img.getHeight()+1), BorderExtender.createInstance(BorderExtender.BORDER_COPY));
//img.getSampleModel().setDa		
			System.out.println("image bounds: "+img.getBounds());
			SampleModel model;
			// do rozszerzenia obrazu potrzebny jest wiekszy WritableRaster
				// stworzenie wiekszego sample model
			SampleModel extSM = img.getSampleModel().createCompatibleSampleModel(img.getWidth() + 2 * KERNEL_RAD_W, img.getHeight() + 2 * KERNEL_RAD_H);
				// stworzenie nowego rastra z nowym SM
			WritableRaster extendedRaster = Raster.createWritableRaster(extSM, null);
			System.out.println("writable raster bounds: "+extendedRaster.getBounds());
			// skopiowanie do nowego rastra i stworzenie obwodki
			img.copyExtendedData(extendedRaster, BorderExtender.createInstance(BorderExtender.BORDER_COPY));
			//
			
			RenderedOp conv = JAI.create("convolve", img, kernel, BorderExtender.BORDER_REFLECT, null);
			after.setImage(conv);
			}
		});

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(before, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
							.addGap(37)
							.addComponent(after, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(107)
							.addComponent(btnFilter, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(73, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(after, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
						.addComponent(before, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE))
					.addGap(36)
					.addComponent(btnFilter)
					.addContainerGap(33, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		this.pack();
	}
	public void fun(){
		// Load the image.
//		RenderedOp src = JAI.create("fileload", fileName);
//		// Create the BoxFilter operation.
//		RenderedOp dst = JAI.create("boxfilter", src,
//		width, height,
//		width/2, height/2);

	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JAITestFrame inst = new JAITestFrame();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
}
