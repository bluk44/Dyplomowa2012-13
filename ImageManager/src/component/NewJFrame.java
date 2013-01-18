package component;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle;
import javax.swing.SwingUtilities;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class NewJFrame extends javax.swing.JFrame {
	private boolean clicked= false;
	ThumbnailComponent[] components = new ThumbnailComponent[100];
	private ThumbnailComponent thumbnailComponent1;
	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				NewJFrame inst = new NewJFrame();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
		
	}
	
	public NewJFrame() {
		super();
		initGUI();
	}
		
	private void initGUI() {
		try {
			{
				this.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent evt) {
						System.out.println("this.mouseClicked, event="+evt);
					}
				});
			}
			GroupLayout thisLayout = new GroupLayout((JComponent)getContentPane());
			getContentPane().setLayout(thisLayout);
			{
				thumbnailComponent1 = new ThumbnailComponent();
				thumbnailComponent1.addComponentListener(new ComponentAdapter() {
					public void componentResized(ComponentEvent evt) {
						System.out.println("thumbnailComponent1.componentResized, event="+evt);
						//TODO add your code for thumbnailComponent1.componentResized
					}
				});
			}
				thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
					.addContainerGap(55, 55)
					.addComponent(thumbnailComponent1, 0, 168, Short.MAX_VALUE)
					.addContainerGap(95, 95));
				thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
					.addContainerGap(59, 59)
					.addComponent(thumbnailComponent1, GroupLayout.PREFERRED_SIZE, 296, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(108, Short.MAX_VALUE));

			pack();
			this.setSize(473, 348);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	
}
