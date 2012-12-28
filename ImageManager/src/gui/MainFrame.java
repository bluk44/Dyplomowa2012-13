package gui;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.LayoutStyle;

import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
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
	private JPanel jPanel1;
	private JTabbedPane jTabbedPane1;
	private JSplitPane jSplitPane1;
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
			this.setTitle("Image manager");
			getContentPane().setBackground(new java.awt.Color(127,127,127));
			{
				jSplitPane1 = new JSplitPane();
				{
					jPanel1 = new JPanel();
					jSplitPane1.add(jPanel1, JSplitPane.LEFT);
					jPanel1.setPreferredSize(new java.awt.Dimension(339, 557));
					jPanel1.setBackground(new java.awt.Color(30,144,255));
				}
				{
					jTabbedPane1 = new JTabbedPane();
					jSplitPane1.add(jTabbedPane1, JSplitPane.RIGHT);
				}
				//jSplitPane1.setDividerLocation(0.5);
				//jSplitPane1.setEnabled(false);
			}
			thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
				.addComponent(jSplitPane1, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addContainerGap());
			thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
				.addComponent(jSplitPane1, 0, 788, Short.MAX_VALUE));
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
