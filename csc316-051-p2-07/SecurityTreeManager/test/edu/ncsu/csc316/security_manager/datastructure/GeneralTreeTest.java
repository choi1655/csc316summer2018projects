/**
 * 
 */
package edu.ncsu.csc316.security_manager.datastructure;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc316.security_manager.manager.SecurityTreeManager;

/**
 * Tests functionality for {@link edu.ncsu.csc316.security_manager.datastructure.GeneralTree}.
 * Currently contains meaningless tests for coverage.
 * 
 * @author John Choi
 * @version 07032018
 */
public class GeneralTreeTest {

	/**
	 * Test method for {@link edu.ncsu.csc316.security_manager.datastructure.GeneralTree#buildAttackTree(CustomArrayList, CustomArrayList)}.
	 */
	@Test
	public void testBuildAttackTree() {
		SecurityTreeManager stm = new SecurityTreeManager("input/ddos-preorder.txt", "input/ddos-postorder.txt");
		System.out.println(stm.getAttackTreeLevelOrder());
		assertEquals("LevelOrder[\n" + 
				"   GOAL Step[Use DDoS Attack to Disrupt All Users, C=0.00, P=0.000, I=0.00]\n" + 
				"   OR Step[Attack Servers, C=0.00, P=0.000, I=0.00]\n" + 
				"   OR Step[Attack Comm Infrastructure, C=0.00, P=0.000, I=0.00]\n" + 
				"   OR Step[Attack All Clients, C=0.00, P=0.000, I=0.00]\n" + 
				"   OR Step[Direct BOTNET against key Servers, C=0.00, P=0.000, I=0.00]\n" + 
				"   OR Step[Infect Servers with Worm/Virus, C=30000.00, P=0.300, I=7.00]\n" + 
				"   OR Step[Attack Switches, C=30000.00, P=0.300, I=7.00]\n" + 
				"   OR Step[Attack Routers, C=30000.00, P=0.300, I=7.00]\n" + 
				"   OR Step[Attack DNS, C=50000.00, P=0.100, I=8.00]\n" + 
				"   OR Step[Infect Clients with Worm/Virus, C=10000.00, P=0.100, I=5.00]\n" + 
				"   OR Step[\"Rent\" Existing BOTNET, C=5000.00, P=0.500, I=6.00]\n" + 
				"   OR Step[Build a BOTNET, C=0.00, P=0.000, I=0.00]\n" + 
				"   AND Step[Find Vulnerable Computers, C=2000.00, P=0.500, I=3.00]\n" + 
				"   AND Step[Infect Computer with BOT, C=5000.00, P=0.400, I=4.00]\n" + 
				"   AND Step[Remain Undetected, C=1000.00, P=0.600, I=4.00]\n" + 
				"]", stm.getAttackTreeLevelOrder());
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.security_manager.datastructure.GeneralTree#printLevelOrder()}.
	 */
	@Test
	public void testPrintLevelOrder() {
		SecurityTreeManager stm = new SecurityTreeManager("input/ddos-preorder.txt", "input/ddos-postorder.txt");
		assertEquals("LevelOrder[\n" + 
				"   GOAL Step[Use DDoS Attack to Disrupt All Users, C=0.00, P=0.000, I=0.00]\n" + 
				"   OR Step[Attack Servers, C=0.00, P=0.000, I=0.00]\n" + 
				"   OR Step[Attack Comm Infrastructure, C=0.00, P=0.000, I=0.00]\n" + 
				"   OR Step[Attack All Clients, C=0.00, P=0.000, I=0.00]\n" + 
				"   OR Step[Direct BOTNET against key Servers, C=0.00, P=0.000, I=0.00]\n" + 
				"   OR Step[Infect Servers with Worm/Virus, C=30000.00, P=0.300, I=7.00]\n" + 
				"   OR Step[Attack Switches, C=30000.00, P=0.300, I=7.00]\n" + 
				"   OR Step[Attack Routers, C=30000.00, P=0.300, I=7.00]\n" + 
				"   OR Step[Attack DNS, C=50000.00, P=0.100, I=8.00]\n" + 
				"   OR Step[Infect Clients with Worm/Virus, C=10000.00, P=0.100, I=5.00]\n" + 
				"   OR Step[\"Rent\" Existing BOTNET, C=5000.00, P=0.500, I=6.00]\n" + 
				"   OR Step[Build a BOTNET, C=0.00, P=0.000, I=0.00]\n" + 
				"   AND Step[Find Vulnerable Computers, C=2000.00, P=0.500, I=3.00]\n" + 
				"   AND Step[Infect Computer with BOT, C=5000.00, P=0.400, I=4.00]\n" + 
				"   AND Step[Remain Undetected, C=1000.00, P=0.600, I=4.00]\n" + 
				"]", stm.getAttackTreeLevelOrder());
	}
	
	/**
	 * Test method for {@link edu.ncsu.csc316.security_manager.datastructure.GeneralTree#setRoot(Object)}.
	 */
	@Test
	public void testSetRoot() {
		GeneralTree<String> a = new GeneralTree<>();
		assertNull(a.getRoot());
		a.setRoot("alpha");
		assertEquals("alpha", a.getRoot().getData());
	}
}
