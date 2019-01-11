/**
 * 
 */
package edu.ncsu.csc316.transportation_manager.datastructure;

/**
 * UpTree Node implementation taken from Mr. Gaweda's TYPOS research platform.
 * 
 * @author Adam Gaweda
 * @author John Choi
 * @version 07222018
 *
 * @param <K> generic type key
 * @param <E> generic type element
 */
public class UptreeNode<K, E> {
	private K key;
	private int count;
	private UptreeNode<K, E> parent;

	/**
	 * Constructs one node with key and data.
	 * 
	 * @param key of this node
	 * @param data of this node
	 */
	public UptreeNode(K key, E data) {
		this.key = key;
		this.setCount(1);
	}

	/**
	 * Getter for key.
	 * 
	 * @return the key
	 */
	public K getKey() {
		return key;
	}

	/**
	 * Getter for parent.
	 * 
	 * @return the parent
	 */
	public UptreeNode<K, E> getParent() {
		return parent;
	}
	
	/**
	 * Setter for parent.
	 * 
	 * @param newParent to set
	 */
	public void setParent(UptreeNode<K, E> newParent) {
		this.parent = newParent;
	}

	/**
	 * Getter for count.
	 * 
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * Setter for count.
	 * 
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}
}
