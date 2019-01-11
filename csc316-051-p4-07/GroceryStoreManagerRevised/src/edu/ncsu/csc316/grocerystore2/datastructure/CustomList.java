/**
 * 
 */
package edu.ncsu.csc316.grocerystore2.datastructure;

/**
 * Custom list interface uniquely designed for this software.
 * 
 * @author John Choi
 * @version 06082018
 * 
 * @param <E> generic type
 */
public interface CustomList<E> {

	/**
	 * Adds the passed in element to the end of the list.
	 * 
	 * @param element to add
	 */
	void add(E element);
	
	/**
	 * Retrieves the element at the passed in index of the list.
	 * 
	 * @param idx of the list to retrieve element from
	 * @return E element retrieved
	 */
	E get(int idx);
	
	/**
	 * Replaces the element at given index with the passed in element.
	 * 
	 * @param element to replace with
	 * @param idx index of the list to replace element from
	 */
	void set(E element, int idx);
	
	/**
	 * Returns true if the list is empty.
	 * 
	 * @return true if the list is empty
	 */
	boolean isEmpty();
	
	/**
	 * Returns the size of the list.
	 * 
	 * @return int size of the list
	 */
	int size();
}
