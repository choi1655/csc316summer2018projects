/**
 * 
 */
package edu.ncsu.csc316.security_manager.datastructure;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests functionality for {@link edu.ncsu.csc316.security_manager.datastructure.CustomQueue}.
 * 
 * @author John Choi
 * @version 07012018
 *
 */
public class CustomQueueTest {

	/**
	 * Test method for {@link edu.ncsu.csc316.security_manager.datastructure.CustomQueue#enqueue(java.lang.Object)}.
	 */
	@Test
	public void testEnqueue() {
		CustomQueue<String> a = new CustomQueue<>();
		assertTrue(a.isEmpty());
		assertEquals(0, a.size());
		a.enqueue("alpha");
		assertFalse(a.isEmpty());
		assertEquals(1, a.size());
		a.enqueue("bravo");
		a.enqueue("charlie");
		assertEquals(3, a.size());
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.security_manager.datastructure.CustomQueue#peek()}.
	 */
	@Test
	public void testPeek() {
		CustomQueue<String> a = new CustomQueue<>();
		a.enqueue("alpha");
		a.enqueue("bravo");
		a.enqueue("charlie");
		assertEquals(3, a.size());
		assertEquals("alpha", a.peek());
		assertEquals(3, a.size());
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.security_manager.datastructure.CustomQueue#dequeue()}.
	 */
	@Test
	public void testDequeue() {
		CustomQueue<String> a = new CustomQueue<>();
		a.enqueue("alpha");
		a.enqueue("bravo");
		a.enqueue("charlie");
		assertEquals(3, a.size());
		assertEquals("alpha", a.peek());
		assertEquals(3, a.size());
		
		assertEquals("alpha", a.dequeue());
		assertEquals(2, a.size());
		
		//dequeue everything
		a.dequeue();
		a.dequeue();
		assertNull(a.dequeue());
	}

}
