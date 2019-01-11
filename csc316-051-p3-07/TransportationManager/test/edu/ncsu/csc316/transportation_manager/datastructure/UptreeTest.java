/**
 * 
 */
package edu.ncsu.csc316.transportation_manager.datastructure;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests functionality for {@link edu.ncsu.csc316.transportation_manager.datastructure.Uptree}.
 * and {@link edu.ncsu.csc316.transportation_manager.datastructure.UptreeNode}.
 * 
 * @author John Choi
 * @version 07232018
 */
public class UptreeTest {

	/**
	 * Test method for {@link edu.ncsu.csc316.transportation_manager.datastructure.Uptree#union(edu.ncsu.csc316.transportation_manager.datastructure.Uptree.UptreeNode, edu.ncsu.csc316.transportation_manager.datastructure.Uptree.UptreeNode)}.
	 */
	@Test
	public void testUnion() {
		Uptree<Character, Integer> upTree = new Uptree<>();

		UptreeNode<Character, Integer> nodeA = new UptreeNode<>('A', 100);
		UptreeNode<Character, Integer> nodeB = new UptreeNode<>('B', 100);
		UptreeNode<Character, Integer> nodeC = new UptreeNode<>('C', 100);
		UptreeNode<Character, Integer> nodeD = new UptreeNode<>('D', 100);
		UptreeNode<Character, Integer> nodeE = new UptreeNode<>('E', 100);
		
		upTree.union(nodeA, nodeB); // A becomes B's parent
		assertEquals("B A", (nodeB.getKey() + " " + upTree.find(nodeB).getKey()));
		upTree.union(nodeC, nodeD); // C becomes D's parent
		assertEquals("D C", (nodeD.getKey() + " " + upTree.find(nodeD).getKey()));
		upTree.union(nodeC, nodeE); // C becomes E's parent
		assertEquals("E C", (nodeE.getKey() + " " + upTree.find(nodeE).getKey()));
		upTree.union(nodeB, nodeE); // B becomes E's parent
		assertEquals("E A", (nodeE.getKey() + " " + upTree.find(nodeE).getKey()));
	}
}
