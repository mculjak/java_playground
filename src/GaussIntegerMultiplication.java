
public class GaussIntegerMultiplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int x = 88;
		int y = 88;
		int result = multiply(x,y); 
		System.out.println(result);
	}

	private static int multiply(int x, int y) {
		if (x < 10 || y < 10) {
			return x*y;
		}
		int n = (int) Math.log10(x);
		
		int a = (int) (x/(Math.pow(10, n)));
		int b = (int) (x%(Math.pow(10, n)));
		int c = (int) (y/(Math.pow(10, n)));
		int d = (int) (y%(Math.pow(10, n)));
		int ac = multiply(a,c);
		int bd = multiply(b,d);
		int adbc = multiply(a+b,c+d)-ac-bd;
		int result = (int) ((Math.pow(10, 2*n))*ac+(Math.pow(10, n))*adbc+bd);
		return result;
	}

}
