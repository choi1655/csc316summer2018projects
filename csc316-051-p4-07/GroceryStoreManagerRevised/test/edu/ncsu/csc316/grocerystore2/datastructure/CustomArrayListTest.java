/**
 * 
 */
package edu.ncsu.csc316.grocerystore2.datastructure;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc316.grocerystore2.datastructure.CustomArrayList;



/**
 * Tests the functionality of CustomArrayList.java.
 * 
 * @author John Choi
 * @version 06032018
 */
public class CustomArrayListTest {

	/**
	 * Test method for {@link edu.ncsu.csc316.grocerystore2.datastructure.CustomArrayList#add(java.lang.Object)}.
	 */
	@Test
	public void testAdd() {
		CustomArrayList<Integer> a = new CustomArrayList<>();
		assertTrue(a.isEmpty());
		a.add(0);
		a.add(1);
		a.add(2);
		a.add(3);
		a.add(4);
		a.add(5);
		a.add(6);
		a.add(7);
		a.add(8);
		a.add(9);
		assertEquals(10, a.size());
		a.add(10);
		assertEquals(11, a.size());
		a.add(11);
		a.add(12);
		a.add(13);
		a.add(14);
		assertEquals(15, a.size());
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.grocerystore2.datastructure.CustomArrayList#get(int)}.
	 */
	@Test (timeout = 100)
	public void testGet() {
		CustomArrayList<Integer> a = new CustomArrayList<>();
		assertEquals(0, a.size());
		a.add(0);
		a.add(1);
		a.add(2);
		a.add(3);
		a.add(4);
		a.add(5);
		a.add(6);
		a.add(7);
		a.add(8);
		a.add(9);
		a.add(10);
		a.add(11);
		a.add(12);
		a.add(13);
		a.add(14);
		assertEquals(15, a.size());
		assertEquals(new Integer(1), a.get(1));
		assertEquals(new Integer(14), a.get(14));
		
		try {
			a.get(16);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(null, e.getMessage());
		}
		try {
			a.get(-2);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(null, e.getMessage());
		}
		
		for (int i = 15; i < 1500; i++) {
			a.add(i);
		}
		assertEquals(1500, a.size());
		assertEquals(new Integer(1499), a.get(1499));
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.grocerystore2.datastructure.CustomArrayList#set(java.lang.Object, int)}.
	 */
	@Test
	public void testSet() {
		CustomArrayList<Integer> a = new CustomArrayList<>();
		assertEquals(0, a.size());
		a.add(0);
		a.add(1);
		a.add(2);
		a.add(3);
		a.add(4);
		a.add(5);
		a.add(6);
		a.add(7);
		a.add(8);
		a.add(9);
		a.add(10);
		a.add(11);
		a.add(12);
		a.add(13);
		a.add(14);
		assertEquals(15, a.size());
		assertEquals(new Integer(1), a.get(1));
		a.set(100, 1);
		assertEquals(new Integer(100), a.get(1));
		a.set(100, 0);
		assertEquals(new Integer(100), a.get(0));
		try {
			a.set(100, -1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(null, e.getMessage());
		}
		try {
			a.set(100, 100);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(null, e.getMessage());
		}
	}
}
