/**
 * 
 */
package edu.ncsu.csc316.security_manager.objects;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests functionality for {@link edu.ncsu.csc316.security_manager.objects.AttackGoal}.
 * 
 * @author John Choi
 * @version 07022018
 */
public class AttackGoalTest {

	/**
	 * Test method for {@link edu.ncsu.csc316.security_manager.objects.AttackGoal#getType()}.
	 */
	@Test
	public void testGetType() {
		AttackGoal test = new AttackGoal("O", 0.5, 4, 35000, "description of this node");
		assertEquals("OR", test.getType());
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.security_manager.objects.AttackGoal#getProbability()}.
	 */
	@Test
	public void testGetProbability() {
		AttackGoal test = new AttackGoal("O", 0.5, 4, 35000, "description of this node");
		assertEquals(0.5, test.getProbability(), 0.1);
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.security_manager.objects.AttackGoal#getScore()}.
	 */
	@Test
	public void testGetScore() {
		AttackGoal test = new AttackGoal("O", 0.5, 4, 35000, "description of this node");
		assertEquals(4, test.getScore(), 0.1);
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.security_manager.objects.AttackGoal#getCost()}.
	 */
	@Test
	public void testGetCost() {
		AttackGoal test = new AttackGoal("O", 0.5, 4, 35000, "description of this node");
		assertEquals(35000, test.getCost(), 0.1);
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.security_manager.objects.AttackGoal#getDescription()}.
	 */
	@Test
	public void testGetDescription() {
		AttackGoal test = new AttackGoal("O", 0.5, 4, 35000, "description of this node");
		assertEquals("description of this node", test.getDescription());
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.security_manager.objects.AttackGoal#equals(Object)}.
	 */
	@Test
	public void testEquals() {
		AttackGoal test = new AttackGoal("O", 0.5, 4, 35000, "description of this node");
		AttackGoal test1 = new AttackGoal("O", 0.5, 4, 35000, "description of this node");
		AttackGoal test2 = new AttackGoal("A", 0.1, 2, 35000, "description of this node");
		assertTrue(test.equals(test1));
		assertTrue(test1.equals(test));
		assertFalse(test.equals(test2));
		assertFalse(test1.equals(test2));
		assertFalse(test2.equals(test));
		assertFalse(test2.equals(test1));
//		assertFalse(test2.equals(null));
		AttackGoal test3 = new AttackGoal(null, 0.1, 2, 35000, "description of this node");
		assertFalse(test3.equals(test2));
	}
	
	/**
	 * Test method for {@link edu.ncsu.csc316.security_manager.objects.AttackGoal#toString()}.
	 */
	@Test
	public void testToString() {
		AttackGoal test = new AttackGoal("O", 0.5, 4, 35000, "description of this node");
		AttackGoal test1 = new AttackGoal("GOAL", "description of this node");
		assertEquals("OR Step[description of this node, C=35000.00, P=0.500, I=4.00]", test.toString());
		assertEquals("GOAL Step[description of this node, C=0.00, P=0.000, I=0.00]", test1.toString());
	}
}
