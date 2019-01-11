/**
 * 
 */
package edu.ncsu.csc316.transportation_manager.highway;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests functionality for {@link edu.ncsu.csc316.transportation_manager.highway.Highway}.
 * 
 * @author John Choi
 * @version 07172018
 */
public class HighwayTest {
	
	/**
	 * Test method for {@link edu.ncsu.csc316.transportation_manager.highway.Asphalt#compareTo(Asphalt)}.
	 */
	@Test
	public void testCompareAsphalt() {
		Highway h1 = new Highway(2, 0, 7.0, 77.0);
		Highway h2 = new Highway(3, 2, 12.0, 212.0);
		Highway h3 = new Highway(2, 0, 7.0, 77.0);
		Highway h4 = new Highway(2, 3, 18.0, 77.0);
		assertTrue(h1.getAsphalt().compareTo(h2.getAsphalt()) < 0);
		assertTrue(h2.getAsphalt().compareTo(h1.getAsphalt()) > 0);
		assertTrue(h1.getAsphalt().compareTo(h3.getAsphalt()) == 0);
		assertTrue(h1.getAsphalt().compareTo(h4.getAsphalt()) == 0);
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.transportation_manager.highway.Cost#compareTo(Cost)}.
	 */
	@Test
	public void testCompareCost() {
		Highway h1 = new Highway(2, 0, 7.0, 77.0);
		Highway h2 = new Highway(3, 2, 12.0, 212.0);
		Highway h3 = new Highway(2, 0, 7.0, 77.0);
		Highway h4 = new Highway(2, 3, 7.0, 948.0);
		assertTrue(h1.getCost().compareTo(h2.getCost()) < 0);
		assertTrue(h2.getCost().compareTo(h1.getCost()) > 0);
		assertTrue(h1.getCost().compareTo(h3.getCost()) == 0);
		assertTrue(h1.getCost().compareTo(h4.getCost()) == 0);
	}
	
	/**
	 * Test method for {@link edu.ncsu.csc316.transportation_manager.highway.Highway#toString()}.
	 */
	@Test
	public void testToString() {
		Highway newHighway = new Highway(2, 0, 7.0, 77.0);
		assertEquals("Highway[city1=2, city2=0, cost=7.0, asphalt=77.0]", newHighway.toString());
	}

}
