/**
 * 
 */
package edu.ncsu.csc316.transportation_manager.heap;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc316.transportation_manager.highway.Highway;

/**
 * Tests functionality of {@link edu.ncsu.csc316.transportation_manager.heap.MinHighwayHeap}.
 * 
 * @author John Choi
 * @version 07232018
 */
public class MinHighwayHeapTest {

	/**
	 * Test method for {@link edu.ncsu.csc316.transportation_manager.heap.MinHighwayHeap#insert(edu.ncsu.csc316.transportation_manager.highway.Highway)}.
	 */
	@SuppressWarnings("static-access")
	@Test
	public void testInsert() {
		Type type = Type.ASPHALT;
		Type type1 = Type.ASPHALT;
		assertEquals(type1.valueOf("ASPHALT"), type.valueOf("ASPHALT"));
		MinHighwayHeap heap = new MinHighwayHeap("COST");
		Highway a = new Highway(2, 0, 7.0, 77.0);
		Highway b = new Highway(3, 2, 12.0, 122.0);
		Highway c = new Highway(0, 3, 14, 144);
		Highway d = new Highway(1, 0, 5, 101);
		Highway e = new Highway(3, 1, 10, 66);
		Highway f = new Highway(1, 2, 6, 55);
		
		heap.insert(a);
		heap.insert(b);
		heap.insert(c);
		heap.insert(d);
		heap.insert(e);
		heap.insert(f);
		assertTrue(heap.size() == 6);
		
		heap = new MinHighwayHeap("ASPHALT");
		a = new Highway(2, 0, 7.0, 159.0);
		b = new Highway(3, 2, 12.0, 212.0);
		c = new Highway(0, 3, 14, 415);
		d = new Highway(1, 0, 5, 99);
		e = new Highway(3, 1, 10, 112);
		f = new Highway(1, 2, 6, 72);
		
		heap.insert(a);
		heap.insert(b);
		heap.insert(c);
		heap.insert(d);
		heap.insert(e);
		heap.insert(f);
		assertTrue(heap.size() == 6);
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.transportation_manager.heap.MinHighwayHeap#deleteMin()}.
	 */
	@Test
	public void testDeleteMin() {
		MinHighwayHeap heap = new MinHighwayHeap("COST");
		Highway a = new Highway(2, 0, 7.0, 77.0);
		Highway b = new Highway(3, 2, 12.0, 122.0);
		Highway c = new Highway(0, 3, 14, 144);
		Highway d = new Highway(1, 0, 5, 101);
		Highway e = new Highway(3, 1, 10, 66);
		Highway f = new Highway(1, 2, 6, 55);
		
		heap.insert(a);
		heap.insert(b);
		heap.insert(c);
		heap.insert(d);
		heap.insert(e);
		heap.insert(f);
		
		heap.deleteMin();
		heap.deleteMin();
		heap.deleteMin();
		heap.deleteMin();
		heap.deleteMin();
		heap.deleteMin();
		
		heap = new MinHighwayHeap("ASPHALT");
		a = new Highway(2, 0, 7.0, 159.0);
		b = new Highway(3, 2, 12.0, 212.0);
		c = new Highway(0, 3, 14, 415);
		d = new Highway(1, 0, 5, 99);
		e = new Highway(3, 1, 10, 112);
		f = new Highway(1, 2, 6, 72);
		
		heap.insert(a);
		heap.insert(b);
		heap.insert(c);
		heap.insert(d);
		heap.insert(e);
		heap.insert(f);
		
		heap.deleteMin();
		heap.deleteMin();
		heap.deleteMin();
		heap.deleteMin();
		heap.deleteMin();
		heap.deleteMin();
		assertTrue(heap.size() == 0);
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.transportation_manager.heap.MinHighwayHeap#toString()}.
	 */
	@Test
	public void testToString() {
		MinHighwayHeap heap = new MinHighwayHeap("COST");
		Highway a = new Highway(2, 0, 7.0, 77.0);
		Highway b = new Highway(3, 2, 12.0, 122.0);
		Highway c = new Highway(0, 3, 14, 144);
		Highway d = new Highway(1, 0, 5, 101);
		Highway e = new Highway(3, 1, 10, 66);
		Highway f = new Highway(1, 2, 6, 55);
		
		heap.insert(a);
		heap.insert(b);
		heap.insert(c);
		heap.insert(d);
		heap.insert(e);
		heap.insert(f);
		assertEquals("Heap[\n" + 
				"   Highway[city1=1, city2=0, cost=5.0, asphalt=101.0],\n" + 
				"   Highway[city1=2, city2=0, cost=7.0, asphalt=77.0],\n" + 
				"   Highway[city1=1, city2=2, cost=6.0, asphalt=55.0],\n" + 
				"   Highway[city1=3, city2=2, cost=12.0, asphalt=122.0],\n" + 
				"   Highway[city1=3, city2=1, cost=10.0, asphalt=66.0],\n" + 
				"   Highway[city1=0, city2=3, cost=14.0, asphalt=144.0]\n" + 
				"]", heap.toString());

		heap = new MinHighwayHeap("ASPHALT");
		a = new Highway(2, 0, 7.0, 159.0);
		b = new Highway(3, 2, 12.0, 212.0);
		c = new Highway(0, 3, 14, 415);
		d = new Highway(1, 0, 5, 99);
		e = new Highway(3, 1, 10, 112);
		f = new Highway(1, 2, 6, 72);

		heap.insert(a);
		heap.insert(b);
		heap.insert(c);
		heap.insert(d);
		heap.insert(e);
		heap.insert(f);
		System.out.println(heap.toString());
		assertEquals("Heap[\n" + 
				"   Highway[city1=1, city2=2, cost=6.0, asphalt=72.0],\n" + 
				"   Highway[city1=3, city2=1, cost=10.0, asphalt=112.0],\n" + 
				"   Highway[city1=1, city2=0, cost=5.0, asphalt=99.0],\n" + 
				"   Highway[city1=3, city2=2, cost=12.0, asphalt=212.0],\n" + 
				"   Highway[city1=2, city2=0, cost=7.0, asphalt=159.0],\n" + 
				"   Highway[city1=0, city2=3, cost=14.0, asphalt=415.0]\n" + 
				"]", heap.toString());
	}
}
