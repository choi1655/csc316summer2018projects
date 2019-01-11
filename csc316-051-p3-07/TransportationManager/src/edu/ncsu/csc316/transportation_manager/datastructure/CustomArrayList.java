/**
 * 
 */
package edu.ncsu.csc316.transportation_manager.datastructure;

/**
 * Custom made array list for this software.
 * 
 * @author John Choi
 * @version 07222018
 * 
 * @param <E> generic type E
 */
public class CustomArrayList<E> {
	
	private Object[] data;
	private int size;
	private int capacity;

	/**
	 * Constructs the array list.
	 */
	public CustomArrayList() {
		size = 0;
		capacity = 10;
		data = new Object[capacity];
	}
	
	/**
	 * Adds an object to the end of the list.
	 * 
	 * @param element to add
	 */
	public void add(E element) {
		if (element == null) {
			throw new IllegalArgumentException();
		}
		if (size >= capacity) {
			growArray();
		}
		data[size] = element;
		size++;
	}
	
	/**
	 * Adds an object at the specified index of the list.
	 * 
	 * @param element to add
	 * @param idx of the list to add to
	 */
	public void add(E element, int idx) {
		if (element == null) {
			throw new IllegalArgumentException();
		}
		if (idx < 0 || idx > size) {
			throw new IndexOutOfBoundsException();
		}
		if (size >= capacity) {
			growArray();
		}
		for (int i = size(); i > idx; i--) {
			data[i] = data[i - 1];
		}
		data[idx] = element;
		size++;
	}
	
	/**
	 * Helper method that grows the data when the size exceeds the capacity.
	 */
	private void growArray() {
		capacity += 50;
		Object[] temp = new Object[capacity];
		for (int i = 0; i < size(); i++) {
			temp[i] = data[i];
		}
		data = temp;
	}
	
	/**
	 * Removes the element at the given index.
	 * 
	 * @param idx of the list to remove
	 * @return removed element
	 */
	public E remove(int idx) {
		if (idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException();
		}
		@SuppressWarnings("unchecked")
		E temp = (E) data[idx];
		for (int i = idx; i < size; i++) {
			data[i] = data[i + 1];
		}
		size--;
		return temp;
	}
	
	/**
	 * Removes the element at the end of the list.
	 * 
	 * @return removed element of the list
	 */
	public E remove() {
		if (size == 0) {
			throw new UnsupportedOperationException();
		}
		@SuppressWarnings("unchecked")
		E temp = (E) data[size - 1];
		size--;
		return temp;
	}
	
	/**
	 * Replaces the element at given index with element passed in.
	 * 
	 * @param element to replace with
	 * @param idx of the list to replace from
	 */
	public void set(E element, int idx) {
		if (idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException();
		}
		data[idx] = element;
	}
	
	/**
	 * Retrives the element at given index.
	 * 
	 * @param idx of the list
	 * @return element at given index
	 */
	@SuppressWarnings("unchecked")
	public E get(int idx) {
		if (idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException();
		}
		return (E) data[idx];
	}
	
	/**
	 * Returns the index of the element if the list contains the element passed in.
	 * 
	 * @param element to search
	 * @return index of the found element
	 */
	public int contains(E element) {
		for (int i = 0; i < size; i++) {
			if (element.equals(data[i])) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Returns the size of the list.
	 * 
	 * @return size of the list
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
