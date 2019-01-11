/**
 * 
 */
package edu.ncsu.csc316.security_manager.datastructure;

/**
 * Custom made array list for this software.
 * 
 * @author John Choi
 * @version 07012018
 * 
 * @param <E> generic type
 */
public class CustomArrayList<E> {
	
	private Object[] data;
	private int size;
	private int capacity;

	/**
	 * Constructs this array list.
	 */
	public CustomArrayList() {
		size = 0;
		capacity = 10;
		data = new Object[capacity];
	}
	
	/**
	 * Adds an element to the end of list.
	 * 
	 * @param element to add
	 */
	public void add(E element) {
		if (size >= capacity) {
			growArray();
		}
		data[size] = element;
		size++;
	}
	
	/**
	 * Adds an element to the given index of the list.
	 * 
	 * @param element to add
	 * @param idx of the list to add
	 */
	public void add(E element, int idx) {
		if (idx < 0 || idx > size) {
			throw new IndexOutOfBoundsException();
		}
		if (size >= capacity) {
			growArray();
		}
		if (idx == size) {
			data[idx] = element;
			size++;
			return;
		}
		for (int i = size; i > idx; i--) {
			data[i] = data[i - 1];
		}
		data[idx] = element;
		size++;
	}
	
	/**
	 * Helper method that grows array when the size has reached its capacity.
	 */
	private void growArray() {
		capacity += 20;
		Object[] tempList = new Object[capacity];
		for (int i = 0; i < data.length; i++) {
			tempList[i] = data[i];
		}
		data = tempList;
	}
	
	/**
	 * Removes an element from the list index passed in.
	 * 
	 * @param idx - index of the list to remove
	 * @return removed element
	 */
	public E remove(int idx) {
		if (idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException();
		}
		@SuppressWarnings("unchecked")
		E returnVal = (E) data[idx];
		for (int i = idx; i < size; i++) {
			data[i] = data[i + 1];
		}
		size--;
		return returnVal;
	}
	
	/**
	 * Removes and returns the element at the very end of the list.
	 * 
	 * @return removed element
	 */
	public E remove() {
		if (size == 0) {
			return null;
		}
		@SuppressWarnings("unchecked")
		E returnVal = (E) data[size - 1];
		size--;
		data[size] = null;
		return returnVal;
	}
	
	/**
	 * Replaces the element at given index with element passed in.
	 * 
	 * @param element to replace with
	 * @param idx of the list to replace
	 */
	public void set(E element, int idx) {
		if (idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException();
		}
		data[idx] = element;
	}
	
	/**
	 * Returns the element at the given index of the list.
	 * 
	 * @param idx of the list
	 * @return element at the given index
	 */
	@SuppressWarnings("unchecked")
	public E get(int idx) {
		if (idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException();
		}
		return (E) data[idx];
	}
	
	/**
	 * Returns the current size of the list.
	 * 
	 * @return the size of the list
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Returns true if the list is currently empty.
	 * 
	 * @return true if list is empty
	 */
	public boolean isEmpty() {
		return size == 0;
	}
}
