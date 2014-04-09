
public class Stack {

	private int[] data;
	private int size;
	
	public Stack(int capacity) {
		this.data = new int[capacity];
		this.size = 0;
	}
	
	public void put(int elem) {
		data[size] = elem;
		size++;
	}
	
	public int pop() {
		size--;
		return data[size];
	}
	
	public boolean isEmpty() {
		if (size == 0) return true;
		else return false;
	}
	
	
	
	
	public static void main(String[] args) {
		Stack s=  new Stack(10);
		System.out.println(s.isEmpty());
		s.put(1);
		s.put(2);
		s.put(3);
		s.put(4);
		s.put(5);
		System.out.println(s.isEmpty());
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.isEmpty());
	}

}
