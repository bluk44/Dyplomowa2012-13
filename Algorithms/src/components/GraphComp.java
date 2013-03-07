package components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class GraphComp extends JComponent {

	static final int XAxisLenTotal = 544;
	static final int XZeroXPos = 60;
	static final int XMaxXPos = 524;
	static final int pixPerUnit = 2;
	private int XAxisX1, XAxisY, XAxisX2, XAxisY2;

	private int YAxisLenTotal;
	private int YAxisX1, YAxisY1, YAxisX2, YAxisY2;
	private double YAxisStep = 1, YMax = 250;

	private int[] pixelCount;

	GraphComp() {
		YAxisLenTotal = 530;

		// x axis position
		XAxisX1 = 0;
		XAxisX2 = XAxisLenTotal;
		XAxisY = YAxisLenTotal - 20;

		// y axis position
		YAxisX2 = YAxisX1 = 50;
		YAxisY1 = 0;
		YAxisY2 = YAxisLenTotal;
		this.setPreferredSize(new Dimension(XAxisLenTotal, YAxisLenTotal));
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.setFont(new Font("Dialog", Font.PLAIN, 8));

		// y axis
		g.drawLine(YAxisX1, YAxisY1, YAxisX2, YAxisY2);
		// kreski
		NumberFormat formatter = new DecimalFormat("0.##E0");
		for (int i = XAxisY; i >= 0; i--) {
			if (i % 10 == 0) {
				g.drawLine(YAxisX1 - 5, XAxisY - i * pixPerUnit, YAxisX1, XAxisY - i
						* pixPerUnit);
				g.drawString(formatter.format(i * YAxisStep), YAxisX1 - 50,
						XAxisY - i * pixPerUnit + 3);
				System.out.println(i * YAxisStep);
			} else {
				g.drawLine(YAxisX1 - 2, XAxisY - i * pixPerUnit, YAxisX1, XAxisY - i
						* pixPerUnit);
			}
		}
		// x axis
		g.drawLine(XAxisX1, XAxisY, XAxisX2, XAxisY2);
		// kreski
		for (int i = 0; i <= 256; i++) {
			if (i % 10 == 0) {
				g.drawLine(XZeroXPos + i * pixPerUnit, XAxisY, XZeroXPos + i * pixPerUnit,
						XAxisY + 5);
				g.drawString("" + i, XZeroXPos + i * pixPerUnit - 4, XAxisY + 16);

			} else {
				g.drawLine(XZeroXPos + i * pixPerUnit, XAxisY, XZeroXPos + i * pixPerUnit,
						XAxisY + 2);
			}
		}
		// data
		if (pixelCount != null) {
			for (int i = 0; i < pixelCount.length; i++) {
				int len = (int)Math.floor(pixelCount[i]/YAxisStep);
				g.setColor(Color.BLACK);
				g.drawLine(XZeroXPos + i * pixPerUnit, XAxisY, XZeroXPos + i * pixPerUnit, XAxisY+len*pixPerUnit);
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GraphComp comp1 = new GraphComp();
		JScrollPane scrollPane = new JScrollPane(comp1);
		JFrame frame = new JFrame("graph test");
		frame.add(scrollPane);
		frame.pack();
		frame.setVisible(true);
	}

	public int[] getPixelCount() {
		return pixelCount;
	}

	public void setPixelCount(int[] pixelCount) throws IllegalArgumentException {
		if (pixelCount.length != 256) {
			throw new IllegalArgumentException(
					"pixelCount must be of length 256");
		}
		this.pixelCount = pixelCount;
	}

	private void countStep(int[] pixelCount) {
		int max = 0;
		for (int i = 0; i < pixelCount.length; i++) {
			if (pixelCount[i] > max) {
				max = pixelCount[i];
			}
		}
		YAxisStep = max / YMax;

	}
}
