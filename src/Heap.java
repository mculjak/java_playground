


public class Heap<T extends Comparable<T>>{
	
	private T[] data;
	private int size;
	
	@SuppressWarnings("unchecked")
	public Heap(int capacity) {
		this.data = (T[]) new Comparable[capacity];
		this.size = 0;
	}
	
	public void insert(T value) {
		data[size+1] = value;
		size++;
		bubbleUp(size);
	}
	
	public T extractMin() {
		T result = data[1];
		data[1] = data[size];
		size--;
		int i = 1;
		bubbleDown(i);
		return result;
		
	}
	
	public boolean change(T oldValue, T newValue) {
		int index = 0;
		for (int i = 1; i <= size; i++) {
			if (oldValue.equals(data[i])) {
				//found oldValue
				index = i;
				break;
			}
		}
		if (index == 0) {
			//couldn't find oldValue
			return false;
		}
		data[index] = newValue;
		if (newValue.compareTo(oldValue)>0) {
			bubbleDown(index);
		} else {
			bubbleUp(index);
		}
		return true;		
	}
	
	private void bubbleDown(int index) {
		while (true) {
			int leftIdx = 2*index;
			int rightIdx = 2*index+1;
			if (leftIdx > size) {
				break;
			}
			int childIdx = leftIdx;
			if (rightIdx <= size) {
				if (data[rightIdx].compareTo(data[leftIdx])<0) { //take smaller child
					childIdx = rightIdx;
				}
			}
			if (data[childIdx].compareTo(data[index])<0){ //child<parent - bubble down
				T tmp = data[childIdx];
				data[childIdx] = data[index];
				data[index] = tmp;
			}
			index = childIdx;
		}
	}
	
	private void bubbleUp(int index) {
		for (int i = index; i > 0; i = i/2) {
			if ((i/2) < 1) {
				break;
			}
			if (data[i].compareTo(data[i/2])<0) { //child<parent - bubble up
				T tmp = data[i];
				data[i] = data[i/2];
				data[i/2] = tmp;
			} else break;
		}
	}
	
	public boolean isEmpty() {
		if (size == 0) return true;
		else return false;
	}
	
	public static void main(String[] args) {
		Heap<Integer> h = new Heap<Integer>(20);
		h.insert(4);
		h.insert(4);
		h.insert(9);
		h.insert(6);
		h.insert(10);
		h.insert(12);
		h.insert(2);
		h.change(4, 11);
		System.out.println(h.extractMin());
		h.change(6, 8);
		h.change(8, 10);
		h.change(12, 3);
		System.out.println(h.extractMin());
		System.out.println(h.extractMin());
		System.out.println(h.extractMin());
		System.out.println(h.extractMin());
		System.out.println(h.extractMin());
		System.out.println(h.extractMin());
	}

}
