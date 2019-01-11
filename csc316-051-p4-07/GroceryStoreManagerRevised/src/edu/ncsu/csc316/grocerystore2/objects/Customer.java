/**
 * 
 */
package edu.ncsu.csc316.grocerystore2.objects;

import edu.ncsu.csc316.grocerystore2.datastructure.CustomArrayList;

/**
 * Class representing single Customer.
 * 
 * @author John Choi
 * @version 07262018
 */
public class Customer implements Comparable<Customer> {

	private String id;
	private String companyName;
	private String state;
	private String zipcode;
	private CustomArrayList<Customer> duplicateCustomers;
	
	/**
	 * Constructs one customer.
	 * 
	 * @param id of the customer
	 * @param companyName of the customer
	 * @param state of the customer
	 * @param zipcode of the customer
	 */
	public Customer(String id, String companyName, String state, String zipcode) {
		this.id = id;
		this.companyName = companyName;
		this.state = state;
		this.zipcode = zipcode;
		duplicateCustomers = new CustomArrayList<>();
	}

	/**
	 * Getter for the ID field.
	 * 
	 * @return the ID of the customer
	 */
	public String getID() {
		return id;
	}

	/**
	 * Getter for the companyName field.
	 * 
	 * @return the companyName of the customer
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * Getter for the state field.
	 * 
	 * @return the state of the customer
	 */
	public String getState() {
		return state;
	}

	/**
	 * Getter for the zipcode field.
	 * 
	 * @return the zipcode of the customer
	 */
	public String getZipcode() {
		return zipcode;
	}
	
	/**
	 * Returns the list of customers with same hash code.
	 * 
	 * @return list of customers with duplicate hash code
	 */
	public CustomArrayList<Customer> getDuplicates() {
		return duplicateCustomers;
	}
	
	/**
	 * Generates a hash code for this object.
	 * *** Code taken from Mr. Gaweda's TYPOS research platform. ***
	 * 
	 * @return hash code for this object
	 */
	public int generateHashCode() {
		// bit shifting
		int hashCode = 0;
		for (int i = 0; i < id.length(); i++) {
			// shift the current hash code 5 bits to the left
			hashCode = hashCode << 5;
			// convert the char into an integer an add to hashCode
			hashCode += (int) id.charAt(i);
		}
		return hashCode;
	}

	/**
	 * Returns true if two customer objects are equal.
	 * 
	 * @param otherCustomer customer to compare with
	 * @return true if two customers carry exactly the same credentials
	 */
	public boolean equalCustomer(Customer otherCustomer) {
		if (id.equals(otherCustomer.id) && companyName.equals(otherCustomer.companyName) && state.equals(otherCustomer.state) && zipcode.equals(otherCustomer.zipcode)) {
			return true;
		}
		return false;
	}

	/**
	 * Outputs customer information in format.
	 * 
	 * @return String formatted information
	 */
	@Override
	public String toString() {
		return String.format("Customer [id=%s, company=%s, state=%s, zipcode=%s]", id, companyName, state, zipcode);
	}

	/**
	 * Used to sort the customers by ID in ascending order.
	 * 
	 * @param o customer to compare with
	 * @return int difference
	 */
	@Override
	public int compareTo(Customer o) {
		return this.id.compareTo(o.getID());
	}
}
