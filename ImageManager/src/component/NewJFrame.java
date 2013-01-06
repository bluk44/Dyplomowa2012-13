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
import javax.swing.JButton;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
	private ThumbnailPanel thumbnailPanel1;
	private JScrollPane scrollPane;
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
	}
	
	private void initGUI() {
		try {
			{
				scrollPane = new JScrollPane();
				getContentPane().add(scrollPane, BorderLayout.CENTER);
				scrollPane.addPropertyChangeListener(new PropertyChangeListener() {
					public void propertyChange(PropertyChangeEvent evt) {
						System.out.println("scrollPane.propertyChange, event="+evt);
						//TODO add your code for scrollPane.propertyChange
					}
				});
				{
					thumbnailPanel1 = new ThumbnailPanel();
					scrollPane.setViewportView(thumbnailPanel1);
					thumbnailPanel1.setPreferredSize(new java.awt.Dimension(347, 520));
					thumbnailPanel1.setBackground(new java.awt.Color(255,165,0));
					thumbnailPanel1.addPropertyChangeListener(new PropertyChangeListener() {
						public void propertyChange(PropertyChangeEvent evt) {
							System.out.println("thumbnailPanel1.propertyChange, event="+evt);
							//TODO add your code for thumbnailPanel1.propertyChange
						}
					});
				}
			}

			pack();
			this.setSize(381, 485);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	
}
