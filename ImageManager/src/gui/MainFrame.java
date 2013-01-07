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
	private JSplitPane jSplitPane2;
	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane2;
	private JScrollPane jScrollPane4;
	private JTabbedPane jTabbedPane1;
	private JPanel jPanel1;
	private JPanel bottomPanel;
	private JPanel topPanel;
	private JScrollPane jScrollPane3;
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
			this.setPreferredSize(new java.awt.Dimension(874, 697));
			{
				jSplitPane1 = new JSplitPane();
				jSplitPane1.setResizeWeight(1.0);
				{
					jScrollPane3 = new JScrollPane();
					jSplitPane1.add(jScrollPane3, JSplitPane.RIGHT);
					jScrollPane3.setMinimumSize(new java.awt.Dimension(1, 1));
					{
						jSplitPane2 = new JSplitPane();
						jScrollPane3.setViewportView(jSplitPane2);
						jSplitPane2.setOrientation(JSplitPane.VERTICAL_SPLIT);
						{
							jPanel1 = new JPanel();
							jSplitPane2.add(jPanel1, JSplitPane.RIGHT);
						}
						{
							jTabbedPane1 = new JTabbedPane();
							jSplitPane2.add(jTabbedPane1, JSplitPane.LEFT);
							{
								jScrollPane2 = new JScrollPane();
								jTabbedPane1.addTab("jScrollPane2", null, jScrollPane2, null);
							}
							{
								jScrollPane4 = new JScrollPane();
								jTabbedPane1.addTab("jScrollPane4", null, jScrollPane4, null);
							}
						}
					}
				}
				{
					jScrollPane1 = new JScrollPane();
					jSplitPane1.add(jScrollPane1, JSplitPane.LEFT);
				}
			}
			{
				bottomPanel = new JPanel();
			}
			{
				topPanel = new JPanel();
				topPanel.setSize(770, 10);
			}
			thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
				.addComponent(topPanel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
				.addComponent(jSplitPane1, 0, 540, Short.MAX_VALUE)
				.addComponent(bottomPanel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE));
			thisLayout.setHorizontalGroup(thisLayout.createParallelGroup()
				.addComponent(topPanel, GroupLayout.Alignment.LEADING, 0, 770, Short.MAX_VALUE)
				.addComponent(jSplitPane1, GroupLayout.Alignment.LEADING, 0, 770, Short.MAX_VALUE)
				.addComponent(bottomPanel, GroupLayout.Alignment.LEADING, 0, 770, Short.MAX_VALUE));
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
			this.setSize(874, 697);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}

}
