/**
 * 
 */
package edu.ncsu.csc316.grocerystore.objects;

/**
 * Class representing single Customer.
 * 
 * @author John Choi
 * @version 06052018
 */
public class Customer implements Comparable<Customer> {

	private String id;
	private String companyName;
	private String state;
	private String zipcode;
	
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
	 * Outputs customer information in format.
	 * 
	 * @return String formatted information
	 */
	@Override
	public String toString() {
		return String.format("Customer [id=%s, company=%s, state=%s, zipcode=%s]\n", id, companyName, state, zipcode);
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
