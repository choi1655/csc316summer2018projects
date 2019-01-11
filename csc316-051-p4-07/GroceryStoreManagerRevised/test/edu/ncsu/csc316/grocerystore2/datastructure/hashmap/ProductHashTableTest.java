/**
 * 
 */
package edu.ncsu.csc316.grocerystore2.datastructure.hashmap;

import static org.junit.Assert.*;

import java.io.File;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.junit.Test;

import edu.ncsu.csc316.grocerystore2.datastructure.CustomArrayList;
import edu.ncsu.csc316.grocerystore2.datastructure.ProductNode;
import edu.ncsu.csc316.grocerystore2.objects.Product;

/**
 * Tests functionality for {@link edu.ncsu.csc316.grocerystore2.datastructure.hashmap.ProductHashTable}.
 * 
 * @author John Choi
 * @version 07262018
 */
public class ProductHashTableTest {

	/**
	 * Test method for {@link edu.ncsu.csc316.grocerystore2.datastructure.hashmap.ProductHashTable#insert(java.lang.String, java.lang.String, edu.ncsu.csc316.grocerystore2.datastructure.ProductNode)}.
	 * @throws Exception thrown if file cannot be opened or collision happens
	 */
	@Test
	public void testInsert() throws Exception {
		ProductHashTable c = new ProductHashTable();
		File myFile = new File("input/order_small.txt");
		Scanner readFile = new Scanner(myFile);
		while (readFile.hasNextLine()) {
			try {
				Product product = new Product(readFile.next().trim(), readFile.nextLine().trim());
				c.insert(product);
			} catch (NoSuchElementException e) {
				break;
			}
		}
		readFile.close();
		assertEquals(50000, c.size());
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.grocerystore2.datastructure.hashmap.ProductHashTable#get(java.lang.String, java.lang.String)}.
	 * @throws Exception thrown if file cannot be opened or collision happens
	 */
	@Test
	public void testGet() throws Exception {
		ProductHashTable c = new ProductHashTable();
		File myFile = new File("input/order_small.txt");
		Scanner readFile = new Scanner(myFile);
		while (readFile.hasNextLine()) {
			try {
				Product product = new Product(readFile.next().trim(), readFile.nextLine().trim());
//				if (product.getBrand().equals("Pack") && product.getDescription().equals("ice cream")) {
//					System.out.println(product.hashCode()); // set breakpoint here for debugging
//				}
				c.insert(product);
			} catch (NoSuchElementException e) {
				break;
			}
		}
		readFile.close();
		
//		assertEquals("Wolf", c.get("Wolf", "ice cream").getBrand());
//		assertEquals("ice cream", c.get("Wolf", "ice cream").getDescription());
		System.out.println(c.get("Wolf", "ice cream"));
		
//		assertEquals("Pack", c.get("Pack", "chocolate milk").getBrand());
//		assertEquals("chocolate milk", c.get("Pack", "chocolate milk").getDescription());
		assertEquals(18, c.get("Pack", "chocolate milk").getFrequency());
		System.out.println(c.get("Pack", "chocolate milk"));
		assertNull(c.get("nope", "Choi"));
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.grocerystore2.datastructure.hashmap.ProductHashTable#toList()}.
	 * @throws Exception if file cannot be opened
	 */
	@Test
	public void testToList() throws Exception {
		ProductHashTable c = new ProductHashTable();
		assertTrue(c.isEmpty());
		File myFile = new File("input/order_small.txt");
		Scanner readFile = new Scanner(myFile);
		while (readFile.hasNextLine()) {
			try {
				Product product = new Product(readFile.next().trim(), readFile.nextLine().trim());
				c.insert(product);
			} catch (NoSuchElementException e) {
				break;
			}
		}
		readFile.close();
		
		CustomArrayList<ProductNode> temp = c.toList();
		for (int i = 0; i < temp.size(); i++) {
			System.out.println(temp.get(i));
		}
		System.out.println(temp.size());
		assertEquals(2078, temp.size());
	}
}
