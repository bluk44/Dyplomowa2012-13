package algorithm;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class NumberFormatTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	     NumberFormat formatter = new DecimalFormat("0.##E0");
	     System.out.println(formatter.format(10));
	}

}
