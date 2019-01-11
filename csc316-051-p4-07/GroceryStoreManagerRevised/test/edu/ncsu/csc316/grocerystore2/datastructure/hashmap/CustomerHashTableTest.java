/**
 * 
 */
package edu.ncsu.csc316.grocerystore2.datastructure.hashmap;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Scanner;

import org.junit.Test;

import edu.ncsu.csc316.grocerystore2.datastructure.CustomArrayList;
import edu.ncsu.csc316.grocerystore2.objects.Customer;

/**
 * Tests functionality for {@link edu.ncsu.csc316.grocerystore2.datastructure.hashmap.CustomerHashTable}.
 * 
 * @author John Choi
 * @version 07262018
 */
public class CustomerHashTableTest {

	/**
	 * Test method for {@link edu.ncsu.csc316.grocerystore2.datastructure.hashmap.CustomerHashTable#insert(java.lang.String, edu.ncsu.csc316.grocerystore2.objects.Customer)}.
	 * @throws Exception thrown if file cannot be opened or collision happens in hash table
	 */
	@Test
	public void testInsert() throws Exception {
		CustomerHashTable c = new CustomerHashTable();
		assertTrue(c.isEmpty());
		File myFile = new File("input/customers_small.txt");
		Scanner readFile = new Scanner(myFile);
		while (readFile.hasNextLine()) {
			String[] temp = readFile.nextLine().split(",");
			String id = temp[0].trim();
			String company = temp[1].trim();
			String state = temp[2].trim();
			String zip = temp[3].trim();
			Customer customer = new Customer(id, company, state, zip);
			c.insert(id, customer);
		}
		readFile.close();
		System.out.println(c.size());
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.grocerystore2.datastructure.hashmap.CustomerHashTable#toList()}.
	 * @throws Exception if file cannot be opened
	 */
	@Test
	public void testToList() throws Exception {
		CustomerHashTable c = new CustomerHashTable();
		File myFile = new File("input/customers_small.txt");
		Scanner readFile = new Scanner(myFile);
		while (readFile.hasNextLine()) {
			String[] temp = readFile.nextLine().split(",");
			String id = temp[0].trim();
			String company = temp[1].trim();
			String state = temp[2].trim();
			String zip = temp[3].trim();
			Customer customer = new Customer(id, company, state, zip);
			c.insert(id, customer);
		}
		readFile.close();
		
		CustomArrayList<Customer> temp = c.toList();
		for (int i = 0; i < temp.size(); i++) {
			System.out.println(temp.get(i));
		}
		System.out.println(temp.size());
		assertEquals(50000, temp.size());
	}
}
