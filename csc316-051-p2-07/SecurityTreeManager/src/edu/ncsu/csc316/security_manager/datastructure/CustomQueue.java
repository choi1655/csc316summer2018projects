/**
 * 
 */
package edu.ncsu.csc316.security_manager.datastructure;

/**
 * Custom made queue list for this software.
 * 
 * @author John Choi
 * @version 07012018
 * 
 * @param <E> generic type
 */
public class CustomQueue<E> {

	private CustomArrayList<E> list;
	private int size;
	
	/**
	 * Constructs a queue.
	 */
	public CustomQueue() {
		list = new CustomArrayList<>();
		size = 0;
	}

	/**
	 * Adds an element to the front of the list.
	 * 
	 * @param element to add
	 */
	public void enqueue(E element) {
		list.add(element, 0);
		size++;
	}
	
	/**
	 * Retrieves but does not remove the last element in the list.
	 * 
	 * @return last element in the list
	 */
	public E peek() {
		return list.get(size - 1);
	}
	
	/**
	 * Removes and returns the last element in the list.
	 * 
	 * @return removed last element in the list
	 */
	public E dequeue() {
		E returnval = list.remove();
		size--;
		return returnval;
	}
	
	/**
	 * Returns the size of the queue.
	 * 
	 * @return size of the queue
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Returns true if the queue is empty.
	 * 
	 * @return true if queue is empty
	 */
	public boolean isEmpty() {
		return size == 0;
	}
}
