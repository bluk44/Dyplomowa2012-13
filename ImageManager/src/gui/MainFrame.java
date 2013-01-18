package gui;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.LayoutStyle;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import component.ThumbnailComponent;
import component.ThumbnailPanel;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class MainFrame extends javax.swing.JFrame {
	private JMenuBar mainMenuBar;
	private JSplitPane secondarySplitPane;
	private JScrollPane thumbnailScrollPane;
	private JScrollPane jScrollPane2;
	private JRadioButton thirdScaleButton;
	private JRadioButton secScaleButton;
	private JRadioButton firstScaleButton;
	private ButtonGroup scaleButtonGroup;
	private JScrollPane jScrollPane4;
	private JTabbedPane collectionsTabbedPane;
	private ThumbnailPanel thumbnailPanel1;
	private JPanel propertiesPanel;
	private JPanel bottomPanel;
	private JPanel topPanel;
	private JScrollPane secondaryScrollPane;
	private JSplitPane mainSplitPane;
	private JMenu jMenu1;

	ThumbnailComponent[] components;

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
		populateThumbnails();
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			GroupLayout thisLayout = new GroupLayout(
					(JComponent) getContentPane());
			getContentPane().setLayout(thisLayout);
			this.setTitle("Image manager");
			getContentPane().setBackground(new java.awt.Color(127, 127, 127));
			this.setPreferredSize(new java.awt.Dimension(874, 697));
			this.setMinimumSize(new java.awt.Dimension(600, 300));
			{
				mainSplitPane = new JSplitPane();
				mainSplitPane.setResizeWeight(1.0);
				mainSplitPane.setDividerSize(5);
				{
					secondaryScrollPane = new JScrollPane();
					mainSplitPane.add(secondaryScrollPane, JSplitPane.RIGHT);
					secondaryScrollPane.setMinimumSize(new java.awt.Dimension(
							150, 1));
					{
						secondarySplitPane = new JSplitPane();
						secondaryScrollPane.setViewportView(secondarySplitPane);
						secondarySplitPane
								.setOrientation(JSplitPane.VERTICAL_SPLIT);
						secondarySplitPane.setResizeWeight(1.0);
						secondarySplitPane.setDividerSize(5);
						{
							propertiesPanel = new JPanel();
							secondarySplitPane.add(propertiesPanel,
									JSplitPane.RIGHT);
							propertiesPanel
									.setMinimumSize(new java.awt.Dimension(150,
											150));
						}
						{
							collectionsTabbedPane = new JTabbedPane();
							secondarySplitPane.add(collectionsTabbedPane,
									JSplitPane.LEFT);
							collectionsTabbedPane
									.setMinimumSize(new java.awt.Dimension(150,
											400));
							{
								jScrollPane2 = new JScrollPane();
								collectionsTabbedPane.addTab("jScrollPane2",
										null, jScrollPane2, null);
							}
							{
								jScrollPane4 = new JScrollPane();
								collectionsTabbedPane.addTab("jScrollPane4",
										null, jScrollPane4, null);
							}
						}
					}
				}
				{
					thumbnailScrollPane = new JScrollPane();
					mainSplitPane.add(thumbnailScrollPane, JSplitPane.LEFT);
					thumbnailScrollPane.setMinimumSize(new java.awt.Dimension(
							650, 200));
					thumbnailScrollPane.setSize(650, 631);
					{
						thumbnailPanel1 = new ThumbnailPanel();
						thumbnailScrollPane.setViewportView(thumbnailPanel1);
						thumbnailPanel1
								.setPreferredSize(new java.awt.Dimension(704,
										573));
					}
				}
				mainSplitPane.setDividerLocation(thumbnailScrollPane
						.getMinimumSize().width);
				secondarySplitPane.setDividerLocation(collectionsTabbedPane
						.getMinimumSize().height);
			}
			{
				bottomPanel = new JPanel();

				scaleButtonGroup = new ButtonGroup();
				thirdScaleButton = new JRadioButton();
				secScaleButton = new JRadioButton();
				firstScaleButton = new JRadioButton();

				scaleButtonGroup.add(firstScaleButton);
				firstScaleButton.setText("10 %");
				firstScaleButton.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent evt) {
						rescaleThumbs(0.10);
					}
				});
				scaleButtonGroup.add(secScaleButton);
				secScaleButton.setText("25 %");
				secScaleButton.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent evt) {
						rescaleThumbs(0.25);
					}
				});
				scaleButtonGroup.add(thirdScaleButton);
				thirdScaleButton.setText("50 %");
				thirdScaleButton.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent evt) {
						rescaleThumbs(0.50);
					}
				});

				GroupLayout bottomPanelLayout = new GroupLayout(
						(JComponent) bottomPanel);
				bottomPanel.setLayout(bottomPanelLayout);
				bottomPanelLayout
						.setHorizontalGroup(bottomPanelLayout
								.createSequentialGroup()
								.addContainerGap(682, Short.MAX_VALUE)
								.addComponent(firstScaleButton,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(secScaleButton,
										GroupLayout.PREFERRED_SIZE, 57,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(thirdScaleButton,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addContainerGap());
				bottomPanelLayout.setVerticalGroup(bottomPanelLayout
						.createSequentialGroup()
						.addGroup(
								bottomPanelLayout
										.createParallelGroup(
												GroupLayout.Alignment.BASELINE)
										.addComponent(firstScaleButton,
												GroupLayout.Alignment.BASELINE,
												0, 23, Short.MAX_VALUE)
										.addComponent(secScaleButton,
												GroupLayout.Alignment.BASELINE,
												GroupLayout.PREFERRED_SIZE, 17,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(thirdScaleButton,
												GroupLayout.Alignment.BASELINE,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.PREFERRED_SIZE))
						.addContainerGap());
			}
			{
				topPanel = new JPanel();
				topPanel.setSize(770, 10);
			}
			thisLayout.setVerticalGroup(thisLayout
					.createSequentialGroup()
					.addComponent(topPanel, GroupLayout.PREFERRED_SIZE, 32,
							GroupLayout.PREFERRED_SIZE)
					.addComponent(mainSplitPane, 0, 540, Short.MAX_VALUE)
					.addComponent(bottomPanel, GroupLayout.PREFERRED_SIZE, 32,
							GroupLayout.PREFERRED_SIZE));
			thisLayout.setHorizontalGroup(thisLayout
					.createParallelGroup()
					.addComponent(topPanel, GroupLayout.Alignment.LEADING, 0,
							770, Short.MAX_VALUE)
					.addComponent(mainSplitPane, GroupLayout.Alignment.LEADING,
							0, 770, Short.MAX_VALUE)
					.addComponent(bottomPanel, GroupLayout.Alignment.LEADING,
							0, 770, Short.MAX_VALUE));
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
			// add your error handling code here
			e.printStackTrace();
		}
	}

	private void rescaleThumbs(double factor) {
		thumbnailPanel1.rescaleComponents(factor);
	}

	private void populateThumbnails() {
		components = new ThumbnailComponent[100];

		for (int i = 0; i < components.length; i++) {
			components[i] = new ThumbnailComponent();
			thumbnailPanel1.add(components[i]);
		}
	}

}
