/**
 * 
 */
package edu.ncsu.csc316.security_manager.datastructure;

/**
 * Defines the general tree that can contain infinite number of children.
 * Code taken from Mr. Gaweda's TYPOS research platform under General Tree section with minor adjustments
 * to meet the requirements of this project.
 * 
 * @author Adam Gaweda
 * @author John Choi
 * @version 07032018
 * 
 * @param <E> generic type
 */
public class GeneralTree<E> {

	private GeneralTreeNode<E> root;
//	private GeneralTreeNode<E> current;
	
	/**
	 * Inner class that defines the single node in the tree.
	 * Code taken from Mr. Gaweda's TYPOS research platform under General Tree section with minor adjustments
	 * to meet the requirements of this project.
	 * 
	 * @author Adam Gaweda
	 * @author John Choi
	 * @version 07032018
	 * 
	 * @param <E> generic type
	 */
	public class GeneralTreeNode<E> {
		
		private E data;
		private CustomArrayList<GeneralTreeNode<E>> children;
		
		/**
		 * Constructs a root of tree.
		 * 
		 * @param data of the node
		 */
		public GeneralTreeNode(E data) {
			this(data, null);
		}
		
		/**
		 * Constructs a node with children.
		 * 
		 * @param data of the node
		 * @param children of the node
		 */
		public GeneralTreeNode(E data, CustomArrayList<GeneralTreeNode<E>> children) {
			this.data = data;
			if (children != null) {
				this.children = children;
			} else {
				this.children = new CustomArrayList<GeneralTreeNode<E>>();
			}
		}
		
		/**
		 * Getter for the data.
		 * 
		 * @return data of the node
		 */
		public E getData() {
			return data;
		}
		
		/**
		 * Returns the children of this node.
		 * 
		 * @return children of the node
		 */
		public CustomArrayList<GeneralTreeNode<E>> getChildren() {
			return children;
		}
		
		/**
		 * Inserts a node to the tree.
		 * 
		 * @param generalTreeNode to add
		 */
		public void insert(GeneralTreeNode<E> generalTreeNode) {
			this.children.add(generalTreeNode);
		}
	}
	
	/**
	 * Constructs this general tree.
	 */
	public GeneralTree() {
		root = null;
	}

	/**
	 * Reads pre/post orders and inserts data into the tree.
	 * 
	 * @param preOrder of the tree
	 * @param postOrder of the tree
	 */
	public void buildAttackTree(CustomArrayList<E> preOrder, CustomArrayList<E> postOrder) {
		root = new GeneralTreeNode<>(preOrder.get(0));
		buildAttackTree(preOrder, postOrder, root, 1, 0, postOrder.size() - 1);
	}
	
	/**
	 * Helper method that generates the attack tree.
	 * 
	 * @param preOrder of the tree
	 * @param postOrder of the tree
	 * @param node - current node
	 * @param preIdx index of the pre list
	 * @param startIdx starting index
	 * @param endIdx ending index
	 */
	private void buildAttackTree(CustomArrayList<E> preOrder, CustomArrayList<E> postOrder, GeneralTreeNode<E> node, int preIdx, int startIdx, int endIdx) {
		int postIdx = startIdx;
		
		while (postIdx < endIdx) {
			E preElement = preOrder.get(preIdx);
			E postElement = postOrder.get(postIdx);
			if (preElement.equals(postElement)) {
				GeneralTreeNode<E> newest = new GeneralTreeNode<>(postElement);
//				newest.setParent(node);
				node.insert(newest);
				preIdx++;
				postIdx++;
			} else {
				GeneralTreeNode<E> tempNode = new GeneralTreeNode<>(preElement);
//				tempNode.setParent(node);
				node.insert(tempNode);
				int newStartIdx = postIdx;
				int increment = 0;
				while (postIdx < postOrder.size() && !postOrder.get(postIdx).equals(tempNode.getData())) {
					increment++;
					postIdx++;
				}
				if (preIdx + 1 < preOrder.size()) {
					buildAttackTree(preOrder, postOrder, tempNode, preIdx + 1, newStartIdx, postIdx);
				}
				preIdx += increment + 1;
				postIdx++;
			}
		}
	}
	
	/**
	 * Getter method for root.
	 * 
	 * @return the root
	 */
	public GeneralTreeNode<E> getRoot() {
		return root;
	}
	
	/**
	 * Sets the root.
	 * This allows the tree to start without root being specified.
	 * 
	 * @param newRoot to set
	 */
	public void setRoot(E newRoot) {
		this.root = new GeneralTreeNode<E>(newRoot); 
	}
	
	/**
	 * Prints the tree in level order.
	 * 
	 * @return level order of the tree
	 */
	public String printLevelOrder() {
		return printLevelOrder(root);
	}

	/**
	 * Helper method for printing the tree in level order.
	 * 
	 * @param node - current node
	 * @return level order of the tree
	 */
	private String printLevelOrder(GeneralTreeNode<E> node) {
		CustomQueue<GeneralTreeNode<E>> list = new CustomQueue<>();
		list.enqueue(node);
		StringBuilder sb = new StringBuilder();
		while (!list.isEmpty()) {
			GeneralTreeNode<E> temp = list.dequeue();
			sb.append("   " + temp.getData().toString());
			if (!temp.getChildren().isEmpty()) {
				for (int i = 0; i < temp.getChildren().size(); i++) {
					list.enqueue(temp.getChildren().get(i));
				}
			}
			if (!list.isEmpty()) {
				sb.append("\n");
			}
		}
		
		return sb.toString();
	}
}
