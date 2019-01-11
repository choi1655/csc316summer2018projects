/**
 * 
 */
package edu.ncsu.csc316.security_manager.datastructure;

/**
 * Defines a binary search tree.
 * Code taken from Mr. Gaweda's TYPOS research platform under Binary Search Tree section with minor adjustments
 * to meet the requirements of this project.
 * 
 * @author Adam Gaweda
 * @author John Choi
 * @version 07022018
 * 
 * @param <E> generic type
 * @param <K> generic type key
 */
public class BinarySearchTree<K extends Comparable<K>, E> {

	private BSTNode<K, E> root;
	
	/**
	 * Defines one binary search tree node.
	 * Code taken from Mr. Gaweda's TYPOS research platform under Binary Search Tree section with minor adjustments
	 * to meet the requirements of this project.
	 * 
	 * @author Adam Gaweda
	 * @author John Choi
	 * @version 07022018
	 *
	 * @param <K> generic type key
	 * @param <E> generic type element
	 */
	public class BSTNode<K extends Comparable<K>, E> {
		private K key;
		private int duplicates = 0;
		private CustomArrayList<E> duplicateNodes;
		
//		private BSTNode<K, E> parent;
		private BSTNode<K, E> left;
		private BSTNode<K, E> right;
		
		/**
		 * Constructs one bst node.
		 * 
		 * @param key of the node
		 * @param data of the node
		 */
		public BSTNode(K key, E data) {
			this(key, data, null);
		}
		
		/**
		 * Constructs one bst node with parent.
		 * 
		 * @param key of the node
		 * @param data of the node
		 * @param parent of the node
		 */
		public BSTNode(K key, E data, BSTNode<K, E> parent) {
			this.key = key;
			duplicateNodes = new CustomArrayList<>();
			duplicateNodes.add(data, 0);
//			this.parent = parent;
		}
		
		/**
		 * Returns the data at the end of the list in this node.
		 * 
		 * @return data of the node
		 */
		public E getData() {
			return duplicateNodes.get(0);
		}

		/**
		 * Returns the list of elements with duplicate keys.
		 * 
		 * @return the duplicateNodes - list of elements
		 */
		public CustomArrayList<E> getDuplicateNodes() {
			return duplicateNodes;
		}
		
		/**
		 * Adds a duplicate with same key.
		 * 
		 * @param element duplicate node
		 */
		public void addDuplicates(E element) {
			duplicateNodes.add(element, 0);
			duplicates++;
		}
		
		/**
		 * Returns number of duplicate nodes.
		 * 
		 * @return duplicates number
		 */
		public int getDuplicates() {
			return duplicates;
		}
	}
	
	/**
	 * Constructs a binary search tree.
	 */
	public BinarySearchTree() {
		root = null;
	}
	
	/**
	 * Recursively inserts an element into the binary search tree.
	 * 
	 * @param key of the node
	 * @param element of the node
	 */
	public void insert(K key, E element) {
		if (root == null) {
			root = new BSTNode<>(key, element);
		} else {
			insert(key, element, root, null);
		}
	}
	
	/**
	 * Helper method that recursively inserts a node into the BST.
	 * If key is duplicate, adds a new node with duplicate key to the left
	 * 
	 * @param key of the node
	 * @param element of the node
	 * @param node - current node
	 * @param parent - current node's parent
	 */
	private void insert(K key, E element, BSTNode<K, E> node, BSTNode<K, E> parent) {
		if (key.compareTo(node.key) < 0) {
			if (node.left == null) {
				node.left = new BSTNode<>(key, element, node);
			} else {
				insert(key, element, node.left, node);
			}
		} else if (key.compareTo(node.key) > 0) {
			if (node.right == null) {
				node.right = new BSTNode<>(key, element, node);
			} else {
				insert(key, element, node.right, node);
			}
		} else { //duplicate case
			node.addDuplicates(element);
		}
	}
	
	/**
	 * Recursively looks up a node in BST using a key.
	 * 
	 * @param key of the node to search
	 * @return requested node
	 */
	public BSTNode<K, E> lookup(K key) {
		return lookup(key, root);
	}
	
	/**
	 * Recursively looks up a node in BST using a key and the current node.
	 * 
	 * @param key to look up
	 * @param node - current node
	 * @return node with passed in key - null if not found
	 */
	private BSTNode<K, E> lookup(K key, BSTNode<K, E> node) {
		if (node == null) {
			return null;
		} else if (key.compareTo(node.key) == 0) {
			return node;
		} else if (key.compareTo(node.key) < 0) {
			return lookup(key, node.left);
		} else /* (key.compareTo(node.key) > 0)*/ {
			return lookup(key, node.right);
		}
	}
	
//	/**
//	 * Recursively removes a node with passed in key.
//	 * 
//	 * @param key of the node to remove
//	 * @return element of the removed node
//	 */
//	public E remove(K key) {
//		return remove(key, root, null);
//	}
//	
//	/**
//	 * Recursively removes a node with passed in key, current node, and element that will be removed.
//	 * 
//	 * @param key of the node to remove
//	 * @param node - current node
//	 * @param removingNode element of the node to be removed
//	 * @return removed element of the node
//	 */
//	private E remove(K key, BSTNode<K, E> node, E removingNode) {
//		//if node is found
//		if (node != null && key.compareTo(node.key) == 0) {
//			
//			//Node to remove is leaf node
//			if (node.left == null && node.right == null) {
//				// simply remove it
//				if (node.key.compareTo(node.parent.left.key) == 0) {
//					node.parent.left = null;
//					return removingNode;
//				} else {
//					node.parent.right = null;
//					return removingNode;
//				}
//			} else if (node.left == null || node.right == null) {
//				//removed node has 1 child so move that child to this location
//				BSTNode<K, E> toMove;
//				boolean isLeft;
//				if (node.left == null) {
//					toMove = node.right;
//					isLeft = false;
//				} else {
//					toMove = node.left;
//					isLeft = true;
//				}
//				toMove.parent = node.parent;
//				if (isLeft) {
//					node.parent.left = toMove;
//				} else {
//					node.parent.right = toMove;
//				}
//				node = toMove;
//				return removingNode;
//			} else {
////				removingNode = node.data;
//				K key2 = leftMost(node.right).key;
//				node.data = leftMost(node.right).data;
//				node.key = key2;
//				return remove(key2, node.right, removingNode);
//			}
//		} else if (node != null && node.key.compareTo(key) > 0) {
//			return remove(key, node.left, node.left.data);
//			//right child
//		} else if (node != null) {
//			return remove(key, node.right, node.right.data);
//		}
//		return null;
//	}
//
//	/**
//	 * Recursively finds the left most node of the tree.
//	 * 
//	 * @param node - current node
//	 * @return left most node
//	 */
//	private BSTNode<K, E> leftMost(BSTNode<K, E> node) {
//		if (node.left == null) {
//			return node;
//		} else {
//			return leftMost(node);
//		}
//	}

	/**
	 * Prints the nodes in level order.
	 * 
	 * @return level order logs
	 */
	public String printLevelOrder() {
		return printLevelOrder(root);
	}
	
	/**
	 * Helper method that prints nodes in level order.
	 * 
	 * @param node - current node
	 * @return level order nodes
	 */
	private String printLevelOrder(BSTNode<K, E> node) {
		CustomQueue<BSTNode<K, E>> list = new CustomQueue<>();
		list.enqueue(node);
		StringBuilder sb = new StringBuilder();
		while (!list.isEmpty()) {
			BSTNode<K, E> temp = list.dequeue();
			sb.append("   " + temp.getData().toString());
//			if (temp.getDuplicates() != 0) {
//				for (int i = 1; i <= temp.getDuplicates(); i++) {
//					sb.append("\n   " + temp.getDuplicateNodes().get(i).toString());
//				}
//			}
			if (temp.left != null) {
				list.enqueue(temp.left);
			}
			if (temp.right != null) {
				list.enqueue(temp.right);
			}
			if (!list.isEmpty()) {
				sb.append("\n");
			}
		}
		return sb.toString();
	}
}
