package algorithm;

public abstract class Mask {

	public static final float[][] LAPLACIAN = { { 0, 1, 0 }, { 1, -4, 1 },
			{ 0, 1, 0 } };
	public static final float[][] LAPLACIAN_DIAG = { { -1, -1, -1 },
			{ -1, 8, -1 }, { -1, -1, -1 } };

	public static final float[][] SOBEL_V = { { -1, 0, 1 }, { -2, 0, 2 },
			{ -1, 0, 1 } };
	public static final float[][] SOBEL_H = { { -1, -2, -1 }, { 0, 0, 0 },
			{ 1, 2, 1 } };
	public static final float[][] SOBEL = { { -2, -2, 0 }, { -2, 0, 2 },
			{ 0, 2, 2 } };

	public static final float[][] UNIFORM = { { 0.111f, 0.111f, 0.111f },
			{ 0.111f, 0.111f, 0.111f }, { 0.111f, 0.111f, 0.111f } };
	public static final float[][] GAUSS_3_3 = { { 1, 2, 1 }, { 2, 4, 2 },
			{ 1, 2, 1 } };
	public static final float[][] GAUSS_5_5 = { { 1, 2, 4, 2, 1 },
			{ 2, 4, 8, 4, 2 }, { 4, 8, 16, 8, 4 }, { 2, 4, 8, 4, 2 },
			{ 1, 2, 4, 2, 1 } };

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
