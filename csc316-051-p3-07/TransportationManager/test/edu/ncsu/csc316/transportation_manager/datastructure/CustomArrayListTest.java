/**
 * 
 */
package edu.ncsu.csc316.transportation_manager.datastructure;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests {@link edu.ncsu.csc316.transportation_manager.datastructure.CustomArrayList}.
 *  
 * @author John Choi
 * @version 07222018
 */
public class CustomArrayListTest {

	/**
	 * Test method for {@link edu.ncsu.csc316.transportation_manager.datastructure.CustomArrayList#add(java.lang.Object)}.
	 */
	@Test
	public void testAddE() {
		CustomArrayList<String> a = new CustomArrayList<>();
		assertEquals(0, a.size());
		assertTrue(a.isEmpty());
		
		//add
		a.add("alpha");
		assertEquals(1, a.size());
		assertFalse(a.isEmpty());
		a.add("bravo");
		a.add("charlie");
		a.add("delta");
		assertEquals(4, a.size());
		a.add("echo");
		a.add("foxtrot");
		a.add("golf");
		a.add("hotel");
		a.add("india");
		a.add("juliet");
		a.add("yolo");
		a.add("zebra");
		
		try {
			a.add(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(e.getMessage());
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.transportation_manager.datastructure.CustomArrayList#add(java.lang.Object, int)}.
	 */
	@Test
	public void testAddEInt() {
		CustomArrayList<String> a = new CustomArrayList<>();
		
		//add
		a.add("alpha");
		a.add("bravo");
		a.add("charlie");
		a.add("delta");
		
		//add at index
		a.add("John", 0);
		assertEquals(5, a.size());
		a.add("Choi", 2);
		assertEquals(6, a.size());
		try {
			a.add("shouldnt work", 10);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertNull(e.getMessage());
		}
		
		//add at the end
		a.add("Hello", 6);
		assertEquals(7, a.size());
		for (int i = 0; i < a.size(); i++) {
			System.out.println(a.get(i));
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.transportation_manager.datastructure.CustomArrayList#remove(int)}.
	 */
	@Test
	public void testRemoveInt() {
		CustomArrayList<String> a = new CustomArrayList<>();
		
		//add
		a.add("alpha");
		a.add("bravo");
		a.add("charlie");
		a.add("delta");
		assertEquals(4, a.size());
		
		//remove
		a.remove(3);
		assertEquals(3, a.size());
		
		//remove all
		a.remove(2);
		a.remove(1);
		a.remove(0);
		assertEquals(0, a.size());
		
		try {
			a.remove(0);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertNull(e.getMessage());
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.transportation_manager.datastructure.CustomArrayList#remove()}.
	 */
	@Test
	public void testRemove() {
		CustomArrayList<String> a = new CustomArrayList<>();
		
		//add
		a.add("alpha");
		a.add("bravo");
		a.add("charlie");
		a.add("delta");
		assertEquals(4, a.size());
		
		//remove
		a.remove();
		assertEquals(3, a.size());
		
		//remove all
		a.remove();
		a.remove();
		a.remove();
		assertEquals(0, a.size());
		
		try {
			a.remove();
			fail();
		} catch (UnsupportedOperationException e) {
			assertNull(e.getMessage());
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.transportation_manager.datastructure.CustomArrayList#set(java.lang.Object, int)}.
	 */
	@Test
	public void testSet() {
		CustomArrayList<String> a = new CustomArrayList<>();
		
		//add
		a.add("alpha");
		a.add("bravo");
		a.add("charlie");
		a.add("delta");
		
		assertEquals("bravo", a.get(1));
		a.set("John", 1);
		
		assertEquals("John", a.get(1));
		try {
			a.set("Choi", 10);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertNull(e.getMessage());
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.transportation_manager.datastructure.CustomArrayList#get(int)}.
	 */
	@Test
	public void testGet() {
		CustomArrayList<String> a = new CustomArrayList<>();
		
		//add
		a.add("alpha");
		a.add("bravo");
		a.add("charlie");
		a.add("delta");
		
		assertEquals("alpha", a.get(0));
		assertEquals("bravo", a.get(1));
		assertEquals("charlie", a.get(2));
		assertEquals("delta", a.get(3));
		try {
			a.get(4);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertNull(e.getMessage());
		}
	}
	
	/**
	 * Test method for {@link edu.ncsu.csc316.transportation_manager.datastructure.CustomArrayList#contains(Object)}.
	 */
	@Test
	public void testContains() {
		CustomArrayList<String> a = new CustomArrayList<>();
		
		//add
		a.add("alpha");
		a.add("bravo");
		a.add("charlie");
		a.add("delta");
		
		assertEquals(1, a.contains("bravo"));
		assertEquals(2, a.contains("charlie"));
		assertEquals(3, a.contains("delta"));
		
		assertEquals(-1, a.contains("John"));
		assertEquals(-1, a.contains("alhpa"));
	}
}
