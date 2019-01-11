/**
 * 
 */
package edu.ncsu.csc316.grocerystore.datastructure;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

import edu.ncsu.csc316.grocerystore.objects.Product;

/**
 * Tests functionality of UnsortedProductList.java.
 * 
 * @author John Choi
 * @version 06032018
 */
public class UnsortedProductListTest {

	/**
	 * Test method for {@link edu.ncsu.csc316.grocerystore.datastructure.UnsortedProductList#getKnownProducts()}.
	 * @throws FileNotFoundException thrown if the file is not found
	 */
	@Test
	public void testGetKnownProducts() throws FileNotFoundException {
		UnsortedProductList u = new UnsortedProductList();
		u.productFileIO("input/order_small.txt");
		u.generateKnownProducts();
		assertFalse(u.getKnownProducts().isEmpty());
//		System.out.println(u.getKnownProducts().size());
//		System.out.println(u.getProducts().get(1761).getBrand() + " " + u.getProducts().get(1761).getDescription());
//		System.out.println(u.getProducts().get(2238).getBrand() + " " + u.getProducts().get(2238).getDescription());
//		System.out.println(u.getProducts().get(2238).getBrand() + " " + u.getProducts().get(2238).getDescription());
//		assertTrue(u.getProducts().get(1761).getBrand().equals(u.getProducts().get(2238).getBrand()));
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.grocerystore.datastructure.UnsortedProductList#getSortedKnownProducts()}.
	 * @throws FileNotFoundException thrown if file is not found
	 */
	@Test
	public void testGetSortedKnownProducts() throws FileNotFoundException {
		UnsortedProductList u = new UnsortedProductList();
		u.productFileIO("input/order_small.txt");
		u.generateKnownProducts();
		u.getSortedKnownProducts();
		assertFalse(u.getSortedKnownProducts().isEmpty());
//		for (int i = 0; i < u.getSortedKnownProducts().size(); i++) {
//			System.out.print(u.getSortedKnownProducts().get(i).toString());
//		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.grocerystore.datastructure.UnsortedProductList#productFileIO(java.lang.String)}.
	 * @throws FileNotFoundException thrown if file cannot be found
	 */
	@Test
	public void testProductFileIO() throws FileNotFoundException {
		UnsortedProductList u = new UnsortedProductList();
		u.productFileIO("input/order_small.txt");
		assertEquals(50000, u.getProducts().size());
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.grocerystore.datastructure.UnsortedProductList#generateKnownProducts()}.
	 */
	@Test
	public void testGenerateKnownProducts() {
		UnsortedProductList u = new UnsortedProductList();
		u.insertProduct(new Product("a", "b"));
		u.insertProduct(new Product("a", "c"));
		u.insertProduct(new Product("a", "d"));
		u.insertProduct(new Product("b", "z"));
		u.insertProduct(new Product("c", "x"));
		u.insertProduct(new Product("a", "b"));
		u.generateKnownProducts();
		assertEquals(5, u.getKnownProducts().size());
		assertEquals(2, u.getKnownProducts().get(0).getFrequency());
		assertEquals(5, u.getKnownProducts().size());
	}
}
