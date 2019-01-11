/**
 * 
 */
package edu.ncsu.csc316.transportation_manager.heap;

import edu.ncsu.csc316.transportation_manager.highway.Asphalt;
import edu.ncsu.csc316.transportation_manager.highway.Cost;
import edu.ncsu.csc316.transportation_manager.highway.Highway;

/**
 * Heap Tree.
 * Source code taken from Mr. Gaweda's TYPOS research platform.
 * 
 * @author Adam Gaweda
 * @author John Choi
 * @version 07232018
 */
public class MinHighwayHeap {

	private Type type;
	private int levels;
	private int capacity;
	private Object[] tree;
	private int next;

	/**
	 * Heap Tree Node.
	 * Source code taken from Mr.Gaweda's TYPOS research platform.
	 * 
	 * @author Adam Gaweda
	 * @author John Choi
	 * @version 07212018
	 *
	 * @param <K> generic type key
	 */
	public class HeapNode<K extends Comparable<K>> implements Comparable<HeapNode<K>> {
		// Nodes with a higher 'priority' move toward the top of the heap
		private K priority; // Using K because we've used K for keys, could have picked P
		private Highway highway;

		/**
		 * Constructs one heap node with priority and highway data.
		 * 
		 * @param priority of the node
		 * @param data of highway
		 */
		public HeapNode(K priority, Highway data) {
			this.highway = data;
			this.priority = priority;
		}

		/**
		 * Used to calculate difference between two objects.
		 * 
		 * @param o object to compare
		 * @return difference between two objects
		 */
		@Override
		public int compareTo(HeapNode<K> o) {
			// Since nodes can be compared, we're passing the comparison
			// to this priority field instead
			return this.priority.compareTo(o.priority);
		}
		
		/**
		 * Returns the highway data.
		 * 
		 * @return highway
		 */
		public Highway getData() {
			return highway;
		}
		
		/**
		 * Returns the formatted information of highway.
		 * 
		 * @return formatted information
		 */
		public String toString() {
			return getData().toString();
		}
	}

	/**
	 * Constructs highway heap tree.
	 */
	public MinHighwayHeap() {
		levels = 10;
		capacity = (int) Math.pow(2, levels);
		tree = new Object[capacity];
		next = 0;
	}
	
	/**
	 * Constructs a new Highway heap
	 * Required method.
	 * 
	 * @param type the type of weight to consider ("COST" or "ASPHALT") when
	 *         operating on the heap
	 */
	public MinHighwayHeap(String type) {
		this();
		if (type.equals("COST")) {
			this.type = Type.COST;
		} else if (type.equals("ASPHALT")) {
			this.type = Type.ASPHALT;
		} else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Returns the size of the heap tree.
	 * 
	 * @return size of the heap tree
	 */
	public int size() {
		return next;
	}
	
	/**
	 * Inserts the given Highway into the minheap
	 * Required method
	 * 
	 * @param hwy the Highway to insert into the minheap
	 */
	public void insert(Highway hwy) {
		if (type == Type.ASPHALT) {
			insertAsphalt(hwy.getAsphalt(), hwy);
		} else if (type == Type.COST) {
			insertCost(hwy.getCost(), hwy);
		} else {
			throw new UnsupportedOperationException();
		}
	}

	/**
	 * Insert will add the node to the next available location on the bottom level and then propagate upward.
	 * 
	 * @param priority of the inserting node
	 * @param element of the inserting node
	 */
	@SuppressWarnings("unchecked")
	private void insertAsphalt(Asphalt priority, Highway element) {
		HeapNode<Asphalt> newNode = new HeapNode<>(priority, element);
		tree[next] = newNode;
		bubbleUpAsphalt(next);
		next++;

		//handling if array size is filled, while an O(n) operation.
		if (next > capacity) {
			levels *= 2;
			capacity = (int) Math.pow(2, levels);
			Object[] temp = new Object[capacity];
			for (int i = 0; i < temp.length; i++) {
				temp[i] = (HeapNode<Asphalt>) tree[i];
			}
			tree = temp;
		}
	}
	
	/**
	 * Insert will add the node to the next available location on the bottom level and then propagate upward.
	 * 
	 * @param priority of the inserting node
	 * @param element of the inserting node
	 */
	@SuppressWarnings("unchecked")
	private void insertCost(Cost priority, Highway element) {
		HeapNode<Cost> newNode = new HeapNode<>(priority, element);
		tree[next] = newNode;
		bubbleUpCost(next);
		next++;

		//handling if array size is filled, while an O(n) operation.
		if (next > capacity) {
			levels *= 2;
			capacity = (int) Math.pow(2, levels);
			Object[] temp = new Object[capacity];
			for (int i = 0; i < temp.length; i++) {
				temp[i] = (HeapNode<Cost>) tree[i];
			}
			tree = temp;
		}
	}

	/**
	 * If the order property is violated because a node's priority becomes larger than that its 
	 * parent's priority, then we swap the two nodes positions in the array and compare to the newly 
	 * moved node's new parent. Once we see a higher priority parent, or we arrive at root, we are finished.
	 * 
	 * @param i level of the tree
	 */
	@SuppressWarnings("unchecked")
	private void bubbleUpAsphalt(int i) {
		while (i > 0 && ((HeapNode<Asphalt>) tree[(i - 1) / 2]).compareTo((HeapNode<Asphalt>) tree[i]) > 0) {
			HeapNode<Asphalt> temp = (HeapNode<Asphalt>) tree[(i - 1) / 2];
			tree[(i - 1) / 2] = tree[i]; // [(i - 1) / 2] is the parent of i
			tree[i] = temp;
			i = (i - 1) / 2;
		}
	}
	
	/**
	 * If the order property is violated because a node's priority becomes larger than that its 
	 * parent's priority, then we swap the two nodes positions in the array and compare to the newly 
	 * moved node's new parent. Once we see a higher priority parent, or we arrive at root, we are finished.
	 * 
	 * @param i level of the tree
	 */
	@SuppressWarnings("unchecked")
	private void bubbleUpCost(int i) {
		while (i > 0 && ((HeapNode<Cost>) tree[(i - 1) / 2]).compareTo((HeapNode<Cost>) tree[i]) > 0) {
			HeapNode<Cost> temp = (HeapNode<Cost>) tree[(i - 1) / 2];
			tree[(i - 1) / 2] = tree[i]; // [(i - 1) / 2] is the parent of i
			tree[i] = temp;
			i = (i - 1) / 2;
		}
	}

	/**
	 * Returns the Highway with minimum weight in the minheap
	 * Required method.
	 * 
	 * @return the Highway with minimum weight in the minheap
	 */
	public Highway deleteMin() {
		if (type == Type.ASPHALT) {
			return removeMinAsphalt().getData();
		} else if (type == Type.COST) {
			return removeMinCost().getData();
		} else {
			throw new UnsupportedOperationException();
		}
	}

	/**
	 * Removes the highest priority element from the heap. 
	 * This element should be at the root of the heap tree (or index 0).
	 * 
	 * @return removed node
	 */
	@SuppressWarnings("unchecked")
	private HeapNode<Asphalt> removeMinAsphalt() {
		if (next == 0) {
			return null;
		}
		HeapNode<Asphalt> root = (HeapNode<Asphalt>) tree[0];
		if (next == 1) {
			tree[0] = null;
		} else {
			// Need to duplicate the array unless we implement
			// some type of rolling buffer in the array
			Object[] newTree = new Object[tree.length];
			newTree[0] = tree[next - 1]; // Set last element to root
			for (int i = 1; i < next - 1; i++) { // add those other nodes
				newTree[i] = (HeapNode<Asphalt>) tree[i];
			}
			tree = newTree;
		}
		next--; //reduce the "next" to insert location
		//Whatever the new root is, shift it downward if it is lower priority
		bubbleDownAsphalt(0);
//		printLevelOrder();
		return root;
	}
	
	/**
	 * Removes the highest priority element from the heap. 
	 * This element should be at the root of the heap tree (or index 0).
	 * 
	 * @return removed node
	 */
	@SuppressWarnings("unchecked")
	private HeapNode<Cost> removeMinCost() {
		if (next == 0) {
			return null;
		}
		HeapNode<Cost> root = (HeapNode<Cost>) tree[0];
		if (next == 1) {
			tree[0] = null;
		} else {
			// Need to duplicate the array unless we implement
			// some type of rolling buffer in the array
			Object[] newTree = new Object[tree.length];
			newTree[0] = tree[next - 1]; // Set last element to root
			for (int i = 1; i < next - 1; i++) { // add those other nodes
				newTree[i] = (HeapNode<Cost>) tree[i];
			}
			tree = newTree;
		}
		next--; //reduce the "next" to insert location
		//Whatever the new root is, shift it downward if it is lower priority
		bubbleDownCost(0);
//		printLevelOrder();
		return root;
	}
	
	/**
	 * Prints the tree in level order.
	 * 
	 * @return level order of the tree
	 */
	public String printLevelOrder() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size(); i++) {
			sb.append("   " + tree[i].toString());
			if (i != size() - 1) {
				sb.append(",");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	/**
	 * Similar to bubbleUp, if the order property is violated due to a node being a lower 
	 * priority than its children, the elements must be swapped. The biggest issue pertains to 
	 * determining if the node needs to be swapped with its left or its right child.
	 * 
	 * @param i level of the tree
	 */
	@SuppressWarnings("unchecked")
	private void bubbleDownAsphalt(int i) {
		while ((2 * i) + 1 < next) {
			// while right child's index is not larger than the actual array
			int j = (2 * i) + 1; // left child
			HeapNode<Asphalt> self = (HeapNode<Asphalt>) tree[i];
			HeapNode<Asphalt> leftChild = (HeapNode<Asphalt>) tree[j];
			HeapNode<Asphalt> rightChild = (HeapNode<Asphalt>) tree[j + 1];
			// left child is bigger than right child
			if ((j < next - 1) && (leftChild.compareTo(rightChild) > 0)) { // smaller than array
				j++;
			}
			HeapNode<Asphalt> childToCompare = (HeapNode<Asphalt>) tree[j];
			// stop if self is smaller than smallest child
			if (self.compareTo(childToCompare) < 0) {
				break;
			}
			HeapNode<Asphalt> temp = (HeapNode<Asphalt>) tree[j];
			tree[j] = tree[i];
			tree[i] = temp;
			i = j;
		}
	}
	
	/**
	 * Similar to bubbleUp, if the order property is violated due to a node being a lower 
	 * priority than its children, the elements must be swapped. The biggest issue pertains to 
	 * determining if the node needs to be swapped with its left or its right child.
	 * 
	 * @param i level of the tree
	 */
	@SuppressWarnings("unchecked")
	private void bubbleDownCost(int i) {
		while ((2 * i) + 1 < next) {
			// while right child's index is not larger than the actual array
			int j = (2 * i) + 1; // left child
			HeapNode<Cost> self = (HeapNode<Cost>) tree[i];
			HeapNode<Cost> leftChild = (HeapNode<Cost>) tree[j];
			HeapNode<Cost> rightChild = (HeapNode<Cost>) tree[j + 1];
			// left child is bigger than right child
			if ((j < next - 1) && (leftChild.compareTo(rightChild) > 0)) { // smaller than array
				j++;
			}
			HeapNode<Cost> childToCompare = (HeapNode<Cost>) tree[j];
			// stop if self is smaller than smallest child
			if (self.compareTo(childToCompare) < 0) {
				break;
			}
			HeapNode<Cost> temp = (HeapNode<Cost>) tree[j];
			tree[j] = tree[i];
			tree[i] = temp;
			i = j;
		}
	}

	/**
	 * Returns a string representation of the level-order traversal 
	 * of the heap in the following format:
	 * 
	 * Heap[
	 *    Highway[city1=X, city2=X, cost=X.X, asphalt=X.X],
	 *    Highway[city1=X, city2=X, cost=X.X, asphalt=X.X],
	 *    Highway[city1=X, city2=X, cost=X.X, asphalt=X.X]
	 * ]
	 * Required method.
	 *
	 * @return the string representation of the minheap
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Heap[\n");
		sb.append(printLevelOrder());
		sb.append("]");
		return sb.toString();
	}
}
