
public class BinarySearchTree {

	private class Node {
		private Node leftChild;
		private Node rightChild;
		private int value;
		
		public void setLeftChild(Node leftChild) {
			this.leftChild = leftChild;
		}
		public void setRightChild(Node rightChild) {
			this.rightChild = rightChild;
		}
		public void setValue(int value) {
			this.value = value;
		}
		
		public Node getLeftChild() {
			return leftChild;
		}
		public Node getRightChild() {
			return rightChild;
		}
		public int getValue() {
			return value;
		}

		public Node(int value) {
			this.value = value;
			this.leftChild = null;
			this.rightChild = null;
		}	
	}
	
	private Node root;
	
	public BinarySearchTree(int rootValue) {
		this.root = new Node(rootValue);
	}
	
	public Node getRoot() {
		return this.root;
	}
	
	public Node search(int value) {
		return search(value, root);
	}
	
	private Node search(int value, Node startingNode) {
		if (startingNode == null) {
			return null;
		}
		else if (startingNode.getValue() == value) {
			return startingNode;
		}
		else if (startingNode.getValue() < value) {
			return search(value, startingNode.getRightChild()); 
		}
		else if (startingNode.getValue() > value) {
			return search(value, startingNode.getLeftChild()); 
		}
		return null;
	}
	
	public void insert(int value) {
		insert(value, root);
	}
	
	private void insert(int value, Node startingNode) {
		if (startingNode.getValue() == value) {
			return;
		}
		else if (startingNode.getValue() < value) {
			if (startingNode.getRightChild() == null) {
				startingNode.setRightChild(new Node(value));
			} else {
				insert(value, startingNode.getRightChild());
			}
		}
		else if (startingNode.getValue() > value) {
			if (startingNode.getLeftChild() == null) {
				startingNode.setLeftChild(new Node(value));
			} else {
				insert(value, startingNode.getLeftChild());
			} 
		}
	}
	
	public void printInOrder() {
		printInOrder(root);
	}
	
	public void printInOrder(Node root) {
		if (root.getLeftChild() != null) {
			printInOrder(root.getLeftChild());
		}
		System.out.println(root.getValue());
		if (root.getRightChild() != null) {
			printInOrder(root.getRightChild());
		}	
	}
	
	public void printPreOrder(Node root) {
		System.out.println(root.getValue());
		if (root.getLeftChild() != null) {
			printPreOrder(root.getLeftChild());
		}
		if (root.getRightChild() != null) {
			printPreOrder(root.getRightChild());
		}
	}
	
	public void printPostOrder(Node root) {
		if (root.getLeftChild() != null) {
			printPostOrder(root.getLeftChild());
		}
		if (root.getRightChild() != null) {
			printPostOrder(root.getRightChild());
		}
		System.out.println(root.getValue());
	}
	
	public static void main(String[] args) {
		BinarySearchTree bst = new BinarySearchTree(3);
		bst.insert(1);
		bst.insert(5);
		bst.insert(2);
		bst.insert(4);
		bst.printPostOrder(bst.getRoot());
	}
}
