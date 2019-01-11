/**
 * 
 */
package edu.ncsu.csc316.transportation_manager.datastructure;

/**
 * UpTree implementation taken from Mr. Gaweda's TYPOS research platform.
 * 
 * @author Adam Gaweda
 * @author John Choi
 * @version 07232018
 * 
 * @param <K> key
 * @param <E> generic type element
 */
public class Uptree<K, E> {

	/**
	 * Returns the root/parent of the node passed in.
	 * 
	 * @param node - node's parent to find
	 * @return found parent
	 */
	public UptreeNode<K, E> find(UptreeNode<K, E> node) {
		while (node.getParent() != null) {
			node = node.getParent();
		}
		return node;
	}
	
	/**
	 * Given two nodes, merge them together.
	 * Since these nodes only care about their parents, the only change needed is to update a former root node
	 * to have a parent.
	 * 
	 * @param n1 node one
	 * @param n2 node two
	 * @return new set
	 */
	public UptreeNode<K, E> union(UptreeNode<K, E> n1, UptreeNode<K, E> n2) {
		if (n1.getCount() >= n2.getCount()) {
			n1.setCount(n1.getCount() + n2.getCount());
			n2.setParent(n1);
			return n1;
		} else {
			n2.setCount(n2.getCount() + n1.getCount());
			n1.setParent(n2);
			return n2;
		}
	}
}
