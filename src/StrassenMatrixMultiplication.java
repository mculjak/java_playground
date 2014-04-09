import java.util.Arrays;

/**
 * TODO:
 * For now works only if submatrices size are even (1 is not included) (e.g. 4×4 - divisible to 2×2 then 1×1)
 * @author mateja
 *
 */
public class StrassenMatrixMultiplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[][] X = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
		int[][] Y = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
		//int[][] X = {{1,2},{3,4}};
		//int[][] Y = {{5,6},{7,8}};
		int[][] result = multiply(X,Y);
		for (int i = 0; i < result.length; i++) {
			System.out.println(Arrays.toString(result[i]));
		}

	}

	private static int[][] multiply(int[][] x, int[][] y) {
		if (x.length==1 && y.length==1) {
			int[][] result = {{x[0][0]*y[0][0]}};
			return result;
		}
		int middle = x.length/2;
		int [][] a = Arrays.copyOfRange(x, 0, middle);
		for (int i = 0; i < a.length; i++) {
			a[i] = Arrays.copyOfRange(x[i], 0, middle);
		}
		int [][] b = Arrays.copyOfRange(x, 0, middle);
		for (int i = 0; i < b.length; i++) {
			b[i] = Arrays.copyOfRange(x[i], middle, x[i].length);
		}
		int [][] c = Arrays.copyOfRange(x, middle, x.length);
		for (int i = 0; i < c.length; i++) {
			c[i] = Arrays.copyOfRange(x[i+middle], 0, middle);
		}
		int [][] d = Arrays.copyOfRange(x, middle, x.length);
		for (int i = 0; i < d.length; i++) {
			d[i] = Arrays.copyOfRange(x[i+middle], middle, x[i].length);
		}
		int [][] e = Arrays.copyOfRange(y, 0, middle);
		for (int i = 0; i < e.length; i++) {
			e[i] = Arrays.copyOfRange(y[i], 0, middle);
		}
		int [][] f = Arrays.copyOfRange(y, 0, middle);
		for (int i = 0; i < f.length; i++) {
			f[i] = Arrays.copyOfRange(y[i], middle, y[i].length);
		}
		int [][] g = Arrays.copyOfRange(y, middle, y.length);
		for (int i = 0; i < g.length; i++) {
			g[i] = Arrays.copyOfRange(y[i+middle], 0, middle);
		}
		int [][] h = Arrays.copyOfRange(y, middle, y.length);
		for (int i = 0; i < h.length; i++) {
			h[i] = Arrays.copyOfRange(y[i+middle], middle, y[i].length);
		}
		
		int[][] p1 = multiply(a, subtract(f,h));
		int[][] p2 = multiply(add(a,b),h);
		int[][] p3 = multiply(add(c,d),e);
		int[][] p4 = multiply(d, subtract(g,e));
		int[][] p5 = multiply(add(a,d), add(e,h));
		int[][] p6 = multiply(subtract(b,d), add(g,h));
		int[][] p7 = multiply(subtract(a,c), add(e,f));
		
		a = subtract(add(add(p5,p4),p6),p2);
		b = add(p1,p2);
		c = add(p3,p4);
		d = add(subtract(subtract(p5,p3),p7),p1);
		
		int[][] result = new int[x.length][x.length];
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result.length; j++) {
				if (i < middle) {
					if (j < middle) {
						result[i][j] = a[i][j];
					} else {
						result[i][j] = b[i][j-middle];
					}
				} else {
					if (j < middle) {
						result[i][j] = c[i-middle][j];
					} else {
						result[i][j] = d[i-middle][j-middle];
					}
				}
			}
		}
		return result;
		
		
	}

	private static int[][] add(int[][] a, int[][] b) {
		int[][] result = new int[a.length][a.length];
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result.length; j++) {
				result[i][j] = a[i][j]+b[i][j];
			}
		}
		return result;
	}

	private static int[][] subtract(int[][] a, int[][] b) {
		int[][] result = new int[a.length][a.length];
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result.length; j++) {
				result[i][j] = a[i][j]-b[i][j];
			}
		}
		return result;
	}

}
