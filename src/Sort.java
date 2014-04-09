import java.util.Arrays;
import java.util.Random;



public class Sort {

	public void selectionSort(int[] a) {
		int i, j, jMin,tmp;
		for (i=0; i<a.length; i++) {
			jMin = i;
			for (j=i; j<a.length; j++) {
				if (a[j] < a[jMin]) jMin = j;
			}
			if (jMin != i) {
				tmp = a[i];
				a[i] = a[jMin];
				a[jMin] = tmp;
			}
		}
	}
	
	public void insertionSort(int[] a) {
		int i,j,tmp;
		for (i = 1;  i < a.length; i++) {
			j = i;
			while (j > 0 && a[j-1] > a[j]) {
				tmp = a[j];
				a[j] = a[j-1];
				a[j-1] = tmp;
				j--;
			}
		}
	}
	
	public void bubbleSort(int[] a) {
		int i, tmp;
		boolean swapped = false;
		do {
			swapped = false;
			for (i = 1; i < a.length; i++) {
				if (a[i-1] > a[i]) {
					tmp = a[i];
					a[i] = a[i-1];
					a[i-1] = tmp;
					swapped = true;
				}
			}
		} while (swapped);
	}
	
	public int[] mergeSort(int[] a) {
		if (a.length > 1) {
			int middle = a.length/2;
			int[] left, right;
			left = Arrays.copyOfRange(a, 0, middle);
			right = Arrays.copyOfRange(a, middle, a.length);
			left = mergeSort(left);
			right = mergeSort(right);
			a = merge(left,right);
		}
		int[] result = a;
		return result;
	}
	
	public int[] merge(int[] left, int[] right) {
		int[] result = new int[left.length+right.length];
		int i=0, j=0;
		for (int k = 0; k < result.length; k++) {
			if (i == left.length) {
				result[k] = right[j];
				j++;
			} else if (j == right.length) {
				result[k] = left[i];
				i++;
			} else if (left[i]<right[j]) {
				result[k] = left[i];
				i++;
			} else {
				result[k] = right[j];
				j++;
			}
		}
		return result;
	}
	
	private void quickSort(int[] a) {
		quickSort(a, 0, a.length);
	}
	
	private void quickSort(int[] a, int startIdx, int endIdx) {
		if (endIdx-startIdx <= 1) {
			return;
		}
		int pivotIndex = choosePivot(startIdx,endIdx);
		int newPivotIndex = partition(a,pivotIndex,startIdx,endIdx);
		quickSort(a,startIdx,newPivotIndex);
		quickSort(a,newPivotIndex+1,endIdx);
				
	}
	
	
	private int partition(int[] a, int pivotIndex, int startIdx, int endIdx) {
		int i=startIdx+1;
		int j;
		//swap(pivot, first element):
		int tmp = a[pivotIndex];
		a[pivotIndex] = a[startIdx];
		a[startIdx] = tmp;
		int pivot = a[startIdx];
		
		for (j = startIdx+1;  j<endIdx; j++) {
			if (a[j]<pivot) {
				//swap(a[i],a[j]);
				tmp = a[j];
				a[j] = a[i];
				a[i] = tmp;
				i++;
			}
		}
		//swap(pivot,last element from left):
		tmp = a[startIdx];
		a[startIdx] = a[i-1];
		a[i-1] = tmp;
		int newPivotIndex = i-1;
		return newPivotIndex;
	}

	private int choosePivot(int startIdx, int endIdx) {
		Random rand = new Random();
		int randomIdx = rand.nextInt(endIdx-startIdx) + startIdx;
		return randomIdx;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] a = {5, 3, 8, 6, 2, 1, 7, 2, 9, 4};
		System.out.println(Arrays.toString(a));
		Sort s = new Sort();
		//s.bubbleSort(a);
		//s.insertionSort(a);
		//s.selectionSort(a);
		//int[] result = s.mergeSort(a);
		s.quickSort(a);
		System.out.println(Arrays.toString(a));	
	}



}
