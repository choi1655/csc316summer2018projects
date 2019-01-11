/**
 * 
 */
package edu.ncsu.csc316.transportation_manager.datastructure;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests functionality of {@link edu.ncsu.csc316.transportation_manager.datastructure.CustomQueue}.
 * 
 * @author John Choi
 * @version 07172018
 */
public class CustomQueueTest {

	/**
	 * Test method for {@link edu.ncsu.csc316.transportation_manager.datastructure.CustomQueue#enqueue(java.lang.Object)}.
	 */
	@Test
	public void testEnqueue() {
		CustomQueue<String> a = new CustomQueue<>();
		assertTrue(a.isEmpty());
		assertEquals(0, a.size());
		
		a.enqueue("alpha");
		a.enqueue("beta");
		a.enqueue("charlie");
		assertEquals(3, a.size());
		assertFalse(a.isEmpty());
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.transportation_manager.datastructure.CustomQueue#dequeue()}.
	 */
	@Test
	public void testDequeue() {
		CustomQueue<String> a = new CustomQueue<>();
		a.enqueue("alpha");
		a.enqueue("beta");
		a.enqueue("charlie");
		assertEquals(3, a.size());
		
		assertEquals("alpha", a.dequeue());
		assertEquals(2, a.size());
		
		assertEquals("beta", a.dequeue());
		assertEquals("charlie", a.dequeue());
		assertNull(a.dequeue());
		assertEquals(0, a.size());
		assertTrue(a.isEmpty());
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.transportation_manager.datastructure.CustomQueue#peek()}.
	 */
	@Test
	public void testPeek() {
		CustomQueue<String> a = new CustomQueue<>();
		a.enqueue("alpha");
		a.enqueue("beta");
		a.enqueue("charlie");
		assertEquals(3, a.size());
		
		assertEquals("alpha", a.peek());
		assertEquals(3, a.size());
	}
}
