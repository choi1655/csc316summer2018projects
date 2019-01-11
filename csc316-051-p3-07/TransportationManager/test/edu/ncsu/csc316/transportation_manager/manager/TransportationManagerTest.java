/**
 * 
 */
package edu.ncsu.csc316.transportation_manager.manager;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests functionality for {@link edu.ncsu.csc316.transportation_manager.manager.TransportationManager}.
 * 
 * @author John Choi
 * @version 07232018
 */
public class TransportationManagerTest {

	/**
	 * Test method for {@link edu.ncsu.csc316.transportation_manager.manager.TransportationManager#getAdjacencyList()}.
	 */
	@Test
	public void testGetAdjacencyList() {
		TransportationManager tm = new TransportationManager("input/highways_small.txt");
		assertEquals("AdjacencyList[\n" + 
				"   City 0: -> Highway[city1=0, city2=3, cost=14.0, asphalt=144.0] -> Highway[city1=1, city2=0, cost=5.0, asphalt=101.0] -> Highway[city1=2, city2=0, cost=7.0, asphalt=77.0]\n" + 
				"   City 1: -> Highway[city1=1, city2=0, cost=5.0, asphalt=101.0] -> Highway[city1=1, city2=2, cost=6.0, asphalt=55.0] -> Highway[city1=3, city2=1, cost=10.0, asphalt=66.0]\n" + 
				"   City 2: -> Highway[city1=1, city2=2, cost=6.0, asphalt=55.0] -> Highway[city1=2, city2=0, cost=7.0, asphalt=77.0] -> Highway[city1=3, city2=2, cost=12.0, asphalt=122.0]\n" + 
				"   City 3: -> Highway[city1=0, city2=3, cost=14.0, asphalt=144.0] -> Highway[city1=3, city2=1, cost=10.0, asphalt=66.0] -> Highway[city1=3, city2=2, cost=12.0, asphalt=122.0]\n" + 
				"]", tm.getAdjacencyList());
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.transportation_manager.manager.TransportationManager#getMinimumHighways(java.lang.String)}.
	 */
	@Test
	public void testGetMinimumHighways() {
		TransportationManager tm = new TransportationManager("input/highways_small.txt");
		assertEquals("List[\n" + 
				"   Highway[city1=1, city2=0, cost=5.0, asphalt=101.0],\n" + 
				"   Highway[city1=1, city2=2, cost=6.0, asphalt=55.0],\n" + 
				"   Highway[city1=3, city2=1, cost=10.0, asphalt=66.0]\n" + 
				"]", tm.getMinimumHighways("COST"));
		
//		tm = new TransportationManager("input/highways_large.txt");
//		System.out.println(tm.getMinimumHighways("COST"));
//		System.out.println("\n\n" + tm.getMinimumHighways("ASPHALT"));
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.transportation_manager.manager.TransportationManager#readFile(java.lang.String)}.
	 */
	@SuppressWarnings("unused")
	@Test
	public void testReadFile() {
		TransportationManager tm = new TransportationManager("input/highways_small.txt");
		try {
			tm = new TransportationManager("thisdoesnotexist.txt");
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(e.getMessage());
		}
	}

}
