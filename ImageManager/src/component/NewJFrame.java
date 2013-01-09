package component;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;


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
	private JScrollPane jScrollPane1;
	private ThumbnailPanel thumbnailPanel1;
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
		setUpComponents();
	}
	
	private void setUpComponents(){
		for(int i=0;i<components.length;i++){


			components[i] = new ThumbnailComponent(""+i);
			thumbnailPanel1.add(components[i]);
		}
		System.out.println(thumbnailPanel1.getComponentCount());
		
	}
	
	private void initGUI() {
		try {
			{
				this.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent evt) {
						System.out.println("this.mouseClicked, event="+evt);
						thumbnailPanel1.rescaleThumbnails(new Dimension(200, 200));
					}
				});
			}
			GroupLayout thisLayout = new GroupLayout((JComponent)getContentPane());
			getContentPane().setLayout(thisLayout);
			{
				jScrollPane1 = new JScrollPane();
				{
					thumbnailPanel1 = new ThumbnailPanel();
					jScrollPane1.setViewportView(thumbnailPanel1);
					thumbnailPanel1.setPreferredSize(new java.awt.Dimension(543, 601));
				}
			}
				thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
					.addContainerGap(32, 32)
					.addComponent(jScrollPane1, 0, 280, Short.MAX_VALUE));
				thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
					.addContainerGap(37, 37)
					.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 548, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(74, Short.MAX_VALUE));

			pack();
			this.setSize(669, 342);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	
}
