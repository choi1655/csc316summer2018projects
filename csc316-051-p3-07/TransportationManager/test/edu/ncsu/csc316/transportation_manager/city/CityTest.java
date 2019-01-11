/**
 * 
 */
package edu.ncsu.csc316.transportation_manager.city;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests functionality for {@link edu.ncsu.csc316.transportation_manager.city.City}.
 * 
 * @author John Choi
 * @version 07232018
 */
public class CityTest {

	/**
	 * Test method for {@link edu.ncsu.csc316.transportation_manager.city.City#getName()}.
	 */
	@Test
	public void testGetName() {
		City c1 = new City(3);
		City c2 = new City(5);
		assertEquals(3, c1.getName());
		assertEquals(5, c2.getName());
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.transportation_manager.city.City#compareTo(edu.ncsu.csc316.transportation_manager.city.City)}.
	 */
	@Test
	public void testCompareTo() {
		City c1 = new City(3);
		City c2 = new City(5);
		City c3 = new City(1);
		
		assertTrue(c1.compareTo(c2) < 0);
		assertTrue(c1.compareTo(c3) > 0);
		assertTrue(c1.compareTo(c1) == 0);
	}
}
