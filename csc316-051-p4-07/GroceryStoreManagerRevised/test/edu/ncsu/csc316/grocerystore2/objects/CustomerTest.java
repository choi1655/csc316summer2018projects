/**
 * 
 */
package edu.ncsu.csc316.grocerystore2.objects;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc316.grocerystore2.objects.Customer;

/**
 * Tests the functionality of Customer.java.
 * 
 * @author John Choi
 * @version 07262018
 */
public class CustomerTest {

	/**
	 * Test method for {@link edu.ncsu.csc316.grocerystore2.objects.Customer#getID()}.
	 */
	@Test
	public void testGetID() {
		Customer customer = new Customer("ID", "Company", "NC", "27695");
		assertEquals("ID", customer.getID());
		customer = new Customer("RandomID", "Company", "NC", "27695");
		assertEquals("RandomID", customer.getID());
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.grocerystore2.objects.Customer#getCompanyName()}.
	 */
	@Test
	public void testGetCompanyName() {
		Customer customer = new Customer("ID", "Company", "NC", "27695");
		assertEquals("Company", customer.getCompanyName());
		customer = new Customer("RandomID", "Random Company", "NC", "27695");
		assertEquals("Random Company", customer.getCompanyName());
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.grocerystore2.objects.Customer#getState()}.
	 */
	@Test
	public void testGetState() {
		Customer customer = new Customer("ID", "Company", "NC", "27695");
		assertEquals("NC", customer.getState());
		customer = new Customer("RandomID", "Company", "CA", "27695");
		assertEquals("CA", customer.getState());
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.grocerystore2.objects.Customer#getZipcode()}.
	 */
	@Test
	public void testGetZipcode() {
		Customer customer = new Customer("ID", "Company", "NC", "27695");
		assertEquals("27695", customer.getZipcode());
		customer = new Customer("RandomID", "Company", "NC", "27606");
		assertEquals("27606", customer.getZipcode());
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.grocerystore2.objects.Customer#toString()}.
	 */
	@Test
	public void testToString() {
		Customer customer = new Customer("ID", "Company", "NC", "27695");
		assertEquals("Customer [id=ID, company=Company, state=NC, zipcode=27695]", customer.toString());
	}
	
	/**
	 * Test method for {@link edu.ncsu.csc316.grocerystore2.objects.Customer#equals(Object)}.
	 */
	@Test
	public void testEquals() {
		Customer customer = new Customer("ID", "Company", "NC", "27695");
		Customer customer1 = new Customer("RandomID", "Company", "NC", "27606");
		assertFalse(customer.equalCustomer(customer1));
		Customer customer2 = new Customer("ID", "Company", "NC", "27695");
		assertTrue(customer2.equalCustomer(customer));
	}
}
