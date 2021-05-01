import java.util.*;

public class Tree<T> {
  
	public static class Node<T> implements Comparable {
		
		private T data;
    private Node<T> parent;
    private List<Node<T>> children;
    
		public Node(T data) {
			this.setData(data);
		}
		
		public T getData() {
			return data;
		}
		public void setData(T data) {
			this.data = data;
		}
		
		public Node<T> getParent() {
			return parent;
		}
		public void setParent(Node<T> parent) {
			this.parent = parent;
		}
		
		public List<Node<T>> getChildren() {
			return children;
		}
		public void setChildren(List<Node<T>> children) {
			this.children = children;
		}

		@Override
		public int compareTo(Object o) {
			return 0;
		}
	}
		
	private Node<T> root;
   
	public Tree(T rootData) {
		root = new Node<T>(rootData);
    root.setData(rootData);
    root.setChildren(new ArrayList<Node<T>>());
	}
  
	public void addToTree(T data) {
		Node<T> node = new Node<T>(data);
		Node<T> tracker = root;
		while(true) {
			if(tracker.children.get(0).getData().equals(data) ) {
				
			}
			if(tracker.children.get(0).getData().equals(data)  ) {
				
			}
		}
		
	} 
}