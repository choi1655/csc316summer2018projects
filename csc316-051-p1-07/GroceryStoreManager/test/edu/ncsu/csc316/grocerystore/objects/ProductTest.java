/**
 * 
 */
package edu.ncsu.csc316.grocerystore.objects;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the functionality of Product.java.
 * 
 * @author John Choi
 * @version 05312018
 */
public class ProductTest {

	/**
	 * Test method for {@link edu.ncsu.csc316.grocerystore.objects.Product#getBrand()}.
	 */
	@Test
	public void testGetBrand() {
		Product product = new Product("Brand", "Description");
		assertEquals("Brand", product.getBrand());
		product = new Product("Apple", "Description");
		assertEquals("Apple", product.getBrand());
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.grocerystore.objects.Product#getDescription()}.
	 */
	@Test
	public void testGetDescription() {
		Product product = new Product("Brand", "Description");
		assertEquals("Description", product.getDescription());
		product = new Product("Apple", "MacBook");
		assertEquals("MacBook", product.getDescription());
	}

}
