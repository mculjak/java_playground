import java.util.Arrays;
import java.util.Random;


public class OrderStatistic {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//int[] a = {5, 3, 8, 6, 2, 1, 7, 2, 9, 4};
		int[] a = new int[10000];
		Random r = new Random();
		for (int i = 0; i < a.length; i++) {
			a[i] = r.nextInt(66666);
		}
		int i = 444;
		//System.out.println(Arrays.toString(a));
		OrderStatistic o = new OrderStatistic();
		int orderStatistic = o.calcOrderStatistic(a,i);
		System.out.println(orderStatistic);
		Arrays.sort(a);
		System.out.println(a[i-1]);
		//System.out.println(Arrays.toString(a));
		
	}

	private int calcOrderStatistic(int[] a, int i) {
		i--; //neccessary to return 1st order statistic for element 0
		return calcOrderStatistic(a, 0, a.length, i);
	}

	private int calcOrderStatistic(int[] a, int startIdx, int endIdx, int i) {
		int pivotIdx = randomChoosePivot(startIdx,endIdx);
		//System.out.println(String.format("Pivot %d, val %d; i=%d; start=%d; end=%d", pivotIdx, a[pivotIdx], i, startIdx, endIdx));
		//int pivotIdx = deterministicChoosePivot(a);
		int newPivotIdx = partition(a,pivotIdx,startIdx,endIdx);
		if (i+startIdx==newPivotIdx) {
			return a[newPivotIdx];
		} else if (newPivotIdx > i+startIdx) {
			return calcOrderStatistic(a, startIdx, newPivotIdx, i);
		} else {
			return calcOrderStatistic(a, newPivotIdx+1, endIdx, i+startIdx-newPivotIdx-1);
		}
				
	}
	

	private int partition(int[] a, int pivotIdx, int startIdx, int endIdx) {
		int i = startIdx+1;
		int j;
		//swap(pivot, first element):
		int tmp = a[startIdx];
		a[startIdx] = a[pivotIdx];
		a[pivotIdx] = tmp;
		int pivot = a[startIdx];
		
		for (j = startIdx+1; j < endIdx; j++) {
			if (a[j]<pivot) {
				//swap(a[i],a[j]);
				tmp = a[i];
				a[i] = a[j];
				a[j] = tmp;
				i++;
			}
		}
		//swap(pivot,last element from left):
		tmp = a[startIdx];
		a[startIdx] = a[i-1];
		a[i-1] = tmp;
		int newPivotIdx = i-1;
		return newPivotIdx;
		
	}

	private int randomChoosePivot(int startIdx, int endIdx) {
		Random rand = new Random();
		return rand.nextInt(endIdx-startIdx) + startIdx;
	}
	
	private int deterministicChoosePivot(int[] a, int startIdx, int endIdx) {
		return 0;
	}
}
