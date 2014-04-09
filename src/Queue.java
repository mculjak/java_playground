
public class Queue {

	private int[] data;
	private int head;
	private int tail;
	
	public Queue(int capacity) {
		this.data = new int[capacity];
		this.head = 0;
		this.tail = 0;
	}
	
	public void put(int elem) {
		if (tail - head >= data.length) return;
		data[tail%data.length] = elem;
		tail++;
	}
	
	public int pop() {
		int result = data[head%data.length];
		head++;
		return result;
		
	}
	
	public boolean isEmpty() {
		if (tail-head == 0) return true;
		else return false;
	}
	
	
	
	
	public static void main(String[] args) {
		Queue q = new Queue(10);
		System.out.println(q.isEmpty());
		q.put(1);
		q.put(2);
		q.put(3);
		q.put(4);
		q.put(5);
		System.out.println(q.isEmpty());
		System.out.println(q.pop());
		System.out.println(q.pop());
		System.out.println(q.pop());
		System.out.println(q.pop());
		System.out.println(q.pop());
		System.out.println(q.isEmpty());
	}

}
