/**
 * 
 */
package edu.ncsu.csc316.grocerystore2.manager;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
//import java.io.PrintWriter;
//import java.io.File;

import org.junit.Test;

import edu.ncsu.csc316.grocerystore2.manager.GroceryStoreManager;

/**
 * Tests the functionality of {@link edu.ncsu.csc316.grocerystore2.manager.GroceryStoreManager}.
 * 
 * @author John Choi
 * @version 07262018
 */
public class GroceryStoreManagerTest {

	/**
	 * Test method for {@link edu.ncsu.csc316.grocerystore2.manager.GroceryStoreManager#getProduct(java.lang.String, java.lang.String)}.
	 * @throws FileNotFoundException thrown if file cannot be found
	 */
	@Test
	public void testGetProduct() throws FileNotFoundException {
		GroceryStoreManager gsm = new GroceryStoreManager("input/order_small.txt", "input/customers_small.txt");
		assertEquals("Product [brand=Pack, description=chocolate milk, frequency=18]", gsm.getProduct("Pack", "chocolate milk"));
//		gsm = new GroceryStoreManager("input/order_large.txt", "input/customers_small.txt");
		assertEquals("Product [brand=Wolf, description=soda, frequency=326]", gsm.getProduct("Wolf", "soda"));
		assertEquals("Product [brand=Pack, description=mustard, frequency=314]", gsm.getProduct("Pack", "mustard"));
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.grocerystore2.manager.GroceryStoreManager#fileCustomerIO(java.lang.String)}.
	 * @throws FileNotFoundException thrown if the file cannot be found
	 */
	@Test
	public void testFileCustomerIO() throws FileNotFoundException {
		GroceryStoreManager gsm = new GroceryStoreManager("input/order_small.txt", "input/customers_small.txt");
//		System.out.println(gsm.getCustomers());
		assertNotNull(gsm.getCustomers());
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.grocerystore2.manager.GroceryStoreManager#getUnsortedCustomers()}.
	 * @throws FileNotFoundException thrown if file cannot be found
	 */
	@Test
	public void testGetUnsortedCustomers() throws FileNotFoundException {
		GroceryStoreManager gsm = new GroceryStoreManager("input/order_small.txt", "input/customers_small.txt");
//		System.out.println(gsm.getCustomers());
//		File myFile = new File("output.txt");
//		PrintWriter pw = new PrintWriter(myFile);
//		pw.println(gsm.getCustomers());
//		pw.println();
//		pw.println();
		gsm.sortCustomerByID();
		assertNotNull(gsm.getUnsortedCustomers());
//		pw.println(gsm.getCustomers());
//		pw.close();
	}
	
	/**
	 * Test method for {@link edu.ncsu.csc316.grocerystore2.manager.GroceryStoreManager#sortCustomerByID()}.
	 * @throws FileNotFoundException thrown if file cannot be found
	 */
	@Test
	public void testSortCustomerByID() throws FileNotFoundException {
		GroceryStoreManager gsm = new GroceryStoreManager("input/order_small.txt", "input/customers_small.txt");
//		gsm.sortCustomerByID();
//		System.out.println(gsm.getCustomers());
//		File myFile = new File("output.txt");
//		PrintWriter pw = new PrintWriter(myFile);
//		pw.println(gsm.getCustomers());
//		pw.println();
//		pw.println();
		gsm.sortCustomerByID();
		assertNotNull(gsm.getCustomers());
//		pw.println(gsm.getCustomers());
//		pw.close();
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.grocerystore2.manager.GroceryStoreManager#printProductList()}.
	 * @throws FileNotFoundException thrown if file cannot be found
	 */
	@Test
	public void testPrintProductList() throws FileNotFoundException {
		GroceryStoreManager gsm = new GroceryStoreManager("input/order_small.txt", "input/customers_small.txt");
//		System.out.println(gsm.printProductList());
		assertNotNull(gsm.printProductList());
//		File newFile = new File("output.txt");
//		PrintWriter pw = new PrintWriter(newFile);
//		pw.println(gsm.printProductList());
//		pw.close();
	}
}
