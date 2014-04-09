import java.util.Arrays;

//TODO: add searching for root of number n using binary search 
//(as new algorithm)
public class BinarySearch {

	public static void main(String[] args) {
		int[] arr = {1,2,2,5,6,8,11,15};
		int elem = 6;
		boolean resultRec = doBinarySearchRecursively(arr,elem);
		boolean resultIter = doBinarySearchIteratively(arr,elem);
		System.out.println(resultRec);
		System.out.println(resultIter);
	}

	private static boolean doBinarySearchIteratively(int[] arr, int elem) {
		if (arr.length == 0) {
			return false;
		}
		int leftIdx = 0;
		int rightIdx = arr.length-1;
		
		while (leftIdx <= rightIdx) {
			int middle = (rightIdx-leftIdx)/2+leftIdx;
			if (arr[middle]==elem) {
				return true;
			} else if (arr[middle] > elem) {
				rightIdx = middle-1;
			} else {
				leftIdx = middle+1;
			}
		}
		
		return false;
	}

	private static boolean doBinarySearchRecursively(int[] arr, int elem) {
		if (arr.length == 0) {
			return false;
		}
		int middle = arr.length/2;
		if (arr[middle] == elem) {
			return true;
		} else if (arr[middle] > elem) {
			int[] left = Arrays.copyOfRange(arr, 0, middle);
			return doBinarySearchRecursively(left, elem);
		} else {
			int[] right = Arrays.copyOfRange(arr, middle+1, arr.length);
			return doBinarySearchRecursively(right, elem);
		}
	}
}
