package component;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

import javax.swing.JPanel;
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
	JPanel panel;
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
			
			panel = new JPanel();
			panel.setMinimumSize(new Dimension(300, 300));
			//panel.setBounds(1,1 , 1, 1);
	//		panel.setLayout(new FlowLayout());
			
			panel.setLayout(new ThumbnailLayout(15, 15));
			panel.setPreferredSize(new Dimension(300, 300));
			
			panel.setBackground(Color.RED);
			
			for(int i=0;i<components.length;i++){
				components[i] = new ThumbnailComponent(""+i);
				panel.add(components[i]);
			}
			
			getContentPane().setLayout(new GridLayout(1,1));
			add(panel);
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent evt) {
					System.out.println("mouse clicked");
					panel.setBounds(0, 0, 500, 500);
				}
			});
			pack();
			this.setSize(837, 742);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	
}
