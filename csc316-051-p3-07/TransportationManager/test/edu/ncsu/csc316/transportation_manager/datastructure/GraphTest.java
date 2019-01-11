/**
 * 
 */
package edu.ncsu.csc316.transportation_manager.datastructure;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc316.transportation_manager.city.City;
import edu.ncsu.csc316.transportation_manager.highway.Highway;

/**
 * Tests functionality for {@link edu.ncsu.csc316.transportation_manager.datastructure.Graph}.
 * 
 * @author John Choi
 * @version 07232018
 */
public class GraphTest {

	/**
	 * Test method for {@link edu.ncsu.csc316.transportation_manager.datastructure.Graph#insertEdge(edu.ncsu.csc316.transportation_manager.datastructure.WeightedEdge)}.
	 */
	@Test
	public void testInsertEdge() {
		Graph<City, Double> graph = new Graph<>();
		Highway a = new Highway(2, 0, 7.0, 77.0);
		Highway b = new Highway(3, 2, 12.0, 122.0);
		Highway c = new Highway(0, 3, 14, 144);
		Highway d = new Highway(1, 0, 5, 101);
		Highway e = new Highway(3, 1, 10, 66);
		Highway f = new Highway(1, 2, 6, 55);
		Highway duplicate = new Highway(3, 2, 11, 15); //duplicate highway
		
		graph.insertHighway(a);
		graph.insertHighway(b);
		graph.insertHighway(c);
		graph.insertHighway(d);
		graph.insertHighway(e);
		graph.insertHighway(f);
		assertEquals(4, graph.getCities().size());
		
		graph.insertHighway(duplicate);
		assertEquals(4, graph.getCities().size());
	}
	
	/**
	 * Test method for {@link edu.ncsu.csc316.transportation_manager.datastructure.Graph#sortCities()}.
	 */
	@Test
	public void testSortCities() {
		Graph<City, Double> graph = new Graph<>();
		Highway a = new Highway(2, 0, 7.0, 77.0);
		Highway b = new Highway(3, 2, 12.0, 122.0);
		Highway c = new Highway(0, 3, 14, 144);
		Highway d = new Highway(1, 0, 5, 101);
		Highway e = new Highway(3, 1, 10, 66);
		Highway f = new Highway(1, 2, 6, 55);
		
		graph.insertHighway(a);
		graph.insertHighway(b);
		graph.insertHighway(c);
		graph.insertHighway(d);
		graph.insertHighway(e);
		graph.insertHighway(f);
		
		graph.getCities().get(1).getNeighbors();
		graph.sortCities();
		graph.sortNeighbors();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < graph.getSortedCities().size(); i++) {
			Vertex<Integer> v = graph.getSortedCities().get(i);
			sb.append(v.getValue() + " <-> ");
			for (int j = 0; j < v.getSortedNeighbors().size(); j++) {
				sb.append(v.getSortedNeighbors().get(j).getValue() + " <-> ");
			}
			sb.append("null\n");
		}
		assertEquals("0 <-> 1 <-> 2 <-> 3 <-> null\n" + 
				"1 <-> 0 <-> 2 <-> 3 <-> null\n" + 
				"2 <-> 0 <-> 1 <-> 3 <-> null\n" + 
				"3 <-> 0 <-> 1 <-> 2 <-> null\n", sb.toString());
	}
}
