import java.util.Arrays;

public class CountInversions {

	public class ArrayInversions {
		private int[] a;
		private int invNum;
		
		public ArrayInversions(int[] a) {
			this.a = a;
			this.invNum = 0;
		}
		
		public void setInvNum(int invNum) {
			this.invNum = invNum;
		}

		public int[] getA() {
			return a;
		}

		public int getInvNum() {
			return invNum;
		}
		
	}

	public ArrayInversions countInversions(int[] a) {
		int invNum;
		ArrayInversions merged = new ArrayInversions(a);
		if (a.length == 1) { 
			invNum=0;
		} else {
			int middle = a.length/2;
			int[] left = Arrays.copyOfRange(a, 0, middle);
			int[] right = Arrays.copyOfRange(a, middle, a.length);
			ArrayInversions leftInv = new ArrayInversions(left);
			ArrayInversions rightInv = new ArrayInversions(right);
			leftInv = countInversions(left);
			rightInv = countInversions(right);
			merged = countSplitInversions(leftInv,rightInv);
			invNum = merged.invNum+leftInv.invNum+rightInv.invNum;
		}
		merged.setInvNum(invNum);
		return merged;
	}
	
	public ArrayInversions countSplitInversions(ArrayInversions left, ArrayInversions right) {
		int [] result = new int[left.a.length+right.a.length];
		int i=0, j=0;
		int invNum = 0;
		for (int k = 0; k < result.length; k++) {
			if (i == left.a.length) {
				result[k] = right.a[j];
				j++;
			} else if (j == right.a.length) {
				result[k] = left.a[i];
				i++;
			} else if (left.a[i] < right.a[j]) { 
				result[k] = left.a[i];
				i++;
			} else {
				result[k] = right.a[j];
				j++;
				invNum += left.a.length-i;
			}
		}
		ArrayInversions resultInv = new ArrayInversions(result);
		resultInv.setInvNum(invNum);
		return resultInv;
	}
	
	
	public static void main(String[] args) {
		int[] b = {5, 3, 8, 6, 2, 1, 7, 2, 9, 4};
		//int[] a = {2, 4, 6, 3, 5};
		System.out.println(Arrays.toString(b));
		CountInversions c = new CountInversions();
		//ArrayInversions aInv = c.new ArrayInversions(b);
		ArrayInversions aInv = c.countInversions(b);
		System.out.println(Arrays.toString(aInv.a));
		System.out.println(aInv.invNum);
	}

}
