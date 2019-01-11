/**
 * 
 */
package edu.ncsu.csc316.transportation_manager.datastructure;

/**
 * Class that defines array based queue.
 * 
 * @author John Choi
 * @version 07182018
 * 
 * @param <E> generic type
 */
public class CustomQueue<E> {
	
	private CustomArrayList<E> data;

	/**
	 * Constructs a custom made queue.
	 */
	public CustomQueue() {
		data = new CustomArrayList<E>();
	}

	/**
	 * Returns true if the queue is empty.
	 * 
	 * @return true if the queue is empty, false if it is not
	 */
	public boolean isEmpty() {
		return data.isEmpty();
	}
	
	/**
	 * Returns the size of the queue.
	 * 
	 * @return size of the queue
	 */
	public int size() {
		return data.size();
	}
	
	/**
	 * Adds the element to the queue with FILO rule.
	 * 
	 * @param element to enqueue
	 */
	public void enqueue(E element) {
		data.add(element, 0);
	}
	
	/**
	 * Removes the element from the queue with FILO rule.
	 * 
	 * @return removed element - returns null if the list is empty
	 */
	public E dequeue() {
		if (isEmpty()) {
			return null;
		}
		return data.remove();
	}
	
	/**
	 * Returns but does not remove the last element in the queue.
	 * 
	 * @return last element in the queue - returns null if the list is empty
	 */
	public E peek() {
		if (isEmpty()) {
			return null;
		}
		return data.get(size() - 1);
	}
}
