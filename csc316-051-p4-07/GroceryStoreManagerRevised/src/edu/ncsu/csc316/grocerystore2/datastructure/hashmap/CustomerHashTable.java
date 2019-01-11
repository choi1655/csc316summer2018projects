/**
 * 
 */
package edu.ncsu.csc316.grocerystore2.datastructure.hashmap;

import edu.ncsu.csc316.grocerystore2.datastructure.CustomArrayList;
import edu.ncsu.csc316.grocerystore2.objects.Customer;

/**
 * Class that defines hash table for customers.
 * 
 * @author John Choi
 * @version 07262018
 */
public class CustomerHashTable {

	private int size;
	private Customer[] customers;
	private int capacity;

	/**
	 * Constructs a hash table for this software.
	 */
	public CustomerHashTable() {
		size = 0;
		capacity = (int) 1993;
		customers = new Customer[capacity];
	}
	
	/**
	 * Inserts a customer with the key passed in.
	 * 
	 * @param id of the customer
	 * @param c customer to insert
	 */
	public void insert(String id, Customer c) {
		int hashcode = compressHashcode(c.generateHashCode());
		if (customers[hashcode] != null) {
			/* Handles potential collision */
			if (!customers[hashcode].equalCustomer(c)) {
				/* confirmed collision */
				customers[hashcode].getDuplicates().add(c);
			}
		} else {
			customers[hashcode] = c;
		}
		size++;
	}

	/**
	 * Compresses the passed in hash code.
	 * 
	 * @param hashCode to compress
	 * @return compressed hash code
	 */
	private int compressHashcode(int hashCode) {
		int returnVal = (31 * hashCode + 51) % capacity;
		return Math.abs(returnVal);
	}

	/**
	 * Returns true if the hash table is empty.
	 * 
	 * @return true if hash table is empty
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * Returns the current size of the hash table.
	 * 
	 * @return size of the table
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Returns the array list version of the table.
	 * 
	 * @return the array list version of the table
	 */
	public CustomArrayList<Customer> toList() {
		CustomArrayList<Customer> result = new CustomArrayList<>();
		for (int i = 0; i < customers.length; i++) {
			if (customers[i] != null) {
				for (int j = 0; j < customers[i].getDuplicates().size(); j++) {
					result.add(customers[i].getDuplicates().get(j));
				}
				result.add(customers[i]);
			}
		}
		return result;
	}
}
