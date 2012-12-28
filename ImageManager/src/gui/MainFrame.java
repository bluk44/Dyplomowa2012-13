package gui;
import javax.swing.GroupLayout;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.LayoutStyle;

import javax.swing.WindowConstants;
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
public class MainFrame extends javax.swing.JFrame {
	private JMenuBar mainMenuBar;
	private JMenu jMenu1;

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MainFrame inst = new MainFrame();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public MainFrame() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			GroupLayout thisLayout = new GroupLayout((JComponent)getContentPane());
			getContentPane().setLayout(thisLayout);
			thisLayout.setVerticalGroup(thisLayout.createParallelGroup());
			thisLayout.setHorizontalGroup(thisLayout.createParallelGroup());
			this.setTitle("Image manager");
			getContentPane().setBackground(new java.awt.Color(127,127,127));
			{
				mainMenuBar = new JMenuBar();
				setJMenuBar(mainMenuBar);
				{
					jMenu1 = new JMenu();
					mainMenuBar.add(jMenu1);
					jMenu1.setText("jMenu1");
				}
			}
			pack();
			this.setSize(798, 626);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}

}
