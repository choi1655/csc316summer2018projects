/**
 * 
 */
package edu.ncsu.csc316.grocerystore.datastructure;

/**
 * Custom ArrayList uniquely designed for this software.
 * 
 * @author John Choi
 * @version 06082018
 * 
 * @param <E> generic type
 */
public class CustomArrayList<E> implements CustomList<E> {

	private Object[] data;
	private int size;
	private int capacity;
	
	/**
	 * Constructs this array list.
	 */
	public CustomArrayList() {
		capacity = 1000;
		size = 0;
		data = new Object[capacity];
	}
	
	/**
	 * Adds the passed in element to the end of the list.
	 * 
	 * @param element to add to the list
	 */
	@Override
	public void add(E element) {
		if (size() >= capacity) {
			growArray();
		}
		data[size] = element;
		size++;
	}

	/**
	 * Helper method that grows the array if more space needs to be allocated than 
	 * the current capacity set.
	 */
	private void growArray() {
		capacity += 10;
		Object[] temp = new Object[capacity];
		for (int i = 0; i < size(); i++) {
			temp[i] = data[i];
		}
		data = temp;
	}
	
	/**
	 * Retrieves the element at the given index.
	 * 
	 * @param idx index of the list to get element from
	 * @return E element retrieved
	 */
	@SuppressWarnings("unchecked")
	@Override
	public E get(int idx) {
		if (idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException();
		}
		return (E) data[idx];
	}

	/**
	 * Replaces the element at given index with the passed in element.
	 * 
	 * @param element to replace with
	 * @param idx of the list
	 */
	@Override
	public void set(E element, int idx) {
		if (idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException();
		}
		data[idx] = element;
	}
	
	/**
	 * Returns true if the list is empty.
	 * 
	 * @return true if the list is empty
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Returns the size of the list.
	 * 
	 * @return size of the list
	 */
	@Override
	public int size() {
		if (size >= Integer.MAX_VALUE) {
			return Integer.MAX_VALUE;
		}
		return size;
	}
}
