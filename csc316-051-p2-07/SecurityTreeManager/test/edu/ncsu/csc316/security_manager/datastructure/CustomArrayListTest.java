/**
 * 
 */
package edu.ncsu.csc316.security_manager.datastructure;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the functionality of {@link edu.ncsu.csc316.security_manager.datastructure.CustomArrayList}.
 * 
 * @author John Choi
 * @version 07012018
 */
public class CustomArrayListTest {

	/**
	 * Test method for {@link edu.ncsu.csc316.security_manager.datastructure.CustomArrayList#add(java.lang.Object)}.
	 */
	@Test
	public void testAddE() {
		CustomArrayList<String> a = new CustomArrayList<>();
		assertTrue(a.isEmpty());
		a.add("alpha");
		assertFalse(a.isEmpty());
		assertEquals(1, a.size());
		a.add("beta");
		a.add("charlie");
		a.add("delta");
		a.add("echo");
		a.add("foxtrot");
		a.add("golf");
		a.add("hotel");
		a.add("india");
		a.add("juliet");
		assertEquals(10, a.size());
		a.add("kilo");
		assertEquals(11, a.size());
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.security_manager.datastructure.CustomArrayList#add(java.lang.Object, int)}.
	 */
	@Test
	public void testAddEInt() {
		CustomArrayList<String> a = new CustomArrayList<>();
		assertTrue(a.isEmpty());
		a.add("alpha");
		assertFalse(a.isEmpty());
		assertEquals(1, a.size());
		a.add("beta");
		a.add("charlie");
		a.add("delta");
		a.add("echo");
		a.add("foxtrot");
		a.add("golf");
		a.add("hotel");
		a.add("india");
		a.add("juliet");
		assertEquals(10, a.size());
		a.add("kilo");

		assertEquals(11, a.size());
		a.add("hello", 2);
		assertEquals(12, a.size());
		assertEquals("hello", a.get(2));
		
		//try adding to the front of the list
		a.add("world", 0);
		
		//try adding to the end of the list
		a.add("John", 12);
		assertEquals("John", a.get(12));
		assertEquals(14, a.size());
		a.add("Choi", 14);
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.security_manager.datastructure.CustomArrayList#remove(int)}.
	 */
	@Test
	public void testRemoveInt() {
		CustomArrayList<String> a = new CustomArrayList<>();
		assertTrue(a.isEmpty());
		a.add("alpha");
		assertFalse(a.isEmpty());
		assertEquals(1, a.size());
		a.add("beta");
		a.add("charlie");
		a.add("delta");
		a.add("echo");
		a.add("foxtrot");
		a.add("golf");
		a.add("hotel");
		a.add("india");
		a.add("juliet");
		a.add("kilo");
		
		assertEquals("charlie", a.remove(2));
		assertEquals(10, a.size());
		
		assertEquals("beta", a.remove(1));
		assertEquals(9, a.size());
		
		//try deleting the first element
		assertEquals("alpha", a.remove(0));
		assertEquals(8, a.size());
		
		//try deleting the last element
		assertEquals("kilo", a.remove(7));
		assertEquals(7, a.size());
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.security_manager.datastructure.CustomArrayList#remove}.
	 */
	@Test
	public void testRemove() {
		CustomArrayList<String> a = new CustomArrayList<>();
		assertTrue(a.isEmpty());
		a.add("alpha");
		assertFalse(a.isEmpty());
		assertEquals(1, a.size());
		a.add("beta");
		a.add("charlie");
		a.add("delta");
		a.add("echo");
		a.add("foxtrot");
		a.add("golf");
		a.add("hotel");
		a.add("india");
		a.add("juliet");
		a.add("kilo");
		
		assertEquals("kilo", a.get(10));
		assertEquals(11, a.size());
		assertEquals("kilo", a.remove());
		assertEquals(10, a.size());
		assertEquals("juliet", a.remove());
		assertEquals(9, a.size());
		
		//try removing until everything is gone
		a.remove();
		a.remove();
		a.remove();
		a.remove();
		a.remove();
		a.remove();
		a.remove();
		a.remove();
		assertEquals("alpha", a.remove());
		assertEquals(0, a.size());
		assertNull(a.remove());
	}
	
	/**
	 * Test method for {@link edu.ncsu.csc316.security_manager.datastructure.CustomArrayList#set(java.lang.Object, int)}.
	 */
	@Test
	public void testSet() {
		CustomArrayList<String> a = new CustomArrayList<>();
		assertTrue(a.isEmpty());
		a.add("alpha");
		assertFalse(a.isEmpty());
		assertEquals(1, a.size());
		a.add("beta");
		a.add("charlie");
		a.add("delta");
		a.add("echo");
		a.add("foxtrot");
		a.add("golf");
		a.add("hotel");
		a.add("india");
		a.add("juliet");
		a.add("kilo");
		
		a.set("John", 3);
		assertEquals("John", a.get(3));
		assertEquals(11, a.size());
		
		//try setting the first element
		a.set("Hello", 0);
		assertEquals("Hello", a.get(0));
		assertEquals(11, a.size());
		
		//try setting the last element
		a.set("World", 10);
		assertEquals("World", a.get(10));
		assertEquals(11, a.size());
	}
}
