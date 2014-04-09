


public class BinarySearchTreeArray {

	private class Node {
		private int value;
		
		public void setValue(int value) {
			this.value = value;
		}
		
		public int getValue() {
			return value;
		}

		public Node(int value) {
			this.value = value;
		}	
	}
	
	private Node[] tree;
	
	public Node[] getTree() {
		return this.tree;
	}
	
	public BinarySearchTreeArray(int size, int rootValue) {
		this.tree = new Node[100];
		
		//tree[0] will be left empty for easier access to children indices
		tree[1] = new Node(rootValue);
	}
	
	public Node search(int value) {
		return search(value, 1);
	}
	
	private Node search(int value, int startingIndex) {
		if (startingIndex >= tree.length) {
			return null;
		}
		if (tree[startingIndex] == null) {
			return null;
		}
		else if (tree[startingIndex].getValue() == value) {
			return tree[startingIndex];
		}
		else if (tree[startingIndex].getValue() < value) {
			return search(value, 2*startingIndex+1); 
		}
		else if (tree[startingIndex].getValue() > value) {
			return search(value, 2*startingIndex); 
		}
		return null;
	}
	
	public void insert(int value) {
		insert(value, 1);
	}
	
	private void insert(int value, int startingIndex) {
		if (startingIndex >= tree.length) {
			return;
		}
		
		if (tree[startingIndex].getValue() == value) {
			return;
		}
		else if (tree[startingIndex].getValue() < value) {
			if (tree[2*startingIndex+1] == null) {
				tree[2*startingIndex+1] = new Node(value);
			} else {
				insert(value, 2*startingIndex+1);
			}
		}
		else if (tree[startingIndex].getValue() > value) {
			if (tree[2*startingIndex] == null) {
				tree[2*startingIndex] = new Node(value);
			} else {
				insert(value, 2*startingIndex);
			} 
		}
	}
	
	public void printInOrder() {
		printInOrder(1);
	}
	
	public void printInOrder(int startingIndex) {
		if (startingIndex >= tree.length) {
			return;
		} 
		if (tree[2*startingIndex] != null) {
			printInOrder(2*startingIndex);
		}
		System.out.println(tree[startingIndex].getValue());
		if (tree[2*startingIndex+1] != null) {
			printInOrder(2*startingIndex+1);
		}	
	}
	
	public void printPreOrder(int startingIndex) {
		if (startingIndex >= tree.length) {
			return;
		} 
		System.out.println(tree[startingIndex].getValue());
		if (tree[2*startingIndex] != null) {
			printPreOrder(2*startingIndex);
		}
		if (tree[2*startingIndex+1] != null) {
			printPreOrder(2*startingIndex+1);
		}
	}
	
	public void printPostOrder(int startingIndex) {
		if (startingIndex >= tree.length) {
			return;
		} 
		
		if (tree[2*startingIndex] != null) {
			printPostOrder(2*startingIndex);
		}
		if (tree[2*startingIndex+1] != null) {
			printPostOrder(2*startingIndex+1);
		}
		System.out.println(tree[startingIndex].getValue());
	}
	
	
	public static void main(String[] args) {
		BinarySearchTreeArray bst = new BinarySearchTreeArray(5,3);
		bst.insert(1);
		bst.insert(5);
		bst.insert(2);
		bst.insert(4);
		bst.printPostOrder(1);

	}

}
