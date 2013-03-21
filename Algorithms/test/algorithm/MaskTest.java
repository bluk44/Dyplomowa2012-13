package algorithm;

public class MaskTest {

	/**
	 * @param args
	 */
	static double LoG(int y, int x){
		double sig = 1.4d;
		double pi = Math.PI;
		double e = Math.E;
		
		double q = (x*x + y*y)/2*sig*sig;
		
		return (-1/(pi * sig * sig * sig * sig))*(1-q)*Math.pow(e, -q);
	}
	
	static double gauss(int x, int y, double sigma){
		double q = (x*x + y*y)/2*sigma*sigma;
		double pi = Math.PI;
		double e = Math.E;
		
		double result = (1/(pi * sigma * sigma))*Math.pow(e, -q);
		return result;
	}
	public static double[][] generateLoGMask(double sigma, int w, int h){
		
		int rw = (w - 1) / 2;
		int rh = (h - 1) / 2;
		
		double[][] mask = new double[h][w];
		
		for (int i = -rh; i <= rh; i++) {
			for (int j = -rw; j <= rw; j++) {
				mask[rh + i][rw + j] = LoG(i, j);
			}
		}
		return mask;
	}
	
	public static double[][] generateGaussMask(double sigma, int n){
		double[][] mask = new double[n][n];
		
		int r = (n - 1) / 2;
		
		for (int i = -r; i <= r; i++) {
			for (int j = -r; j <= r; j++) {
//				System.out.println(i + " "+ j);
//				System.out.println((r+i)+" "+(r+j));
				mask[r+i][r+j] = gauss(i,j,sigma);
			}
		}
		
		return mask;
	}
	public static void main(String[] args) {
//		double[][] LoGMask = generateLoGMask(1.4, 7, 7);
//		
//		for (int i = 0; i < LoGMask.length; i++) {
//			for (int j = 0; j < LoGMask[0].length; j++) {
//				//System.out.format("%10.5f", LoGMask[i][j]);
//				System.out.print(LoGMask[i][j]+" ");
//			}
//			System.out.println();
//		}
	//	System.out.format("%10.4f", 0.23333333333);
		
		double[][] gaussMask = generateGaussMask(1d, 3);
		
		for (int i = 0; i < gaussMask.length; i++) {
			for (int j = 0; j < gaussMask[0].length; j++) {
				//System.out.format("%10.5f", LoGMask[i][j]);
				System.out.print(gaussMask[i][j]+" ");
			}
			System.out.println();
		}
		
		System.out.println(gauss(0,0,1));
	}

}
