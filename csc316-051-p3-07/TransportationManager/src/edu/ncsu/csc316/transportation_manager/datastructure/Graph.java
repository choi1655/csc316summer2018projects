/**
 * 
 */
package edu.ncsu.csc316.transportation_manager.datastructure;

import edu.ncsu.csc316.transportation_manager.highway.Highway;

/**
 * Class that defines the graph.
 * Code taken from Mr. Gaweda's TYPOS research platform.
 * Modified to fit the project's needs.
 * 
 * @author Adam Gaweda
 * @author John Choi
 * @version 07232018
 * 
 * @param <V> generic type vertex
 * @param <E> generic type element
 */
public class Graph<V extends Comparable<V>, E extends Comparable<E>> {
	/** List of cities that represents vertices*/
	protected CustomArrayList<Vertex<Integer>> cities;
	/** List of highways */
	protected CustomArrayList<Highway> highways;
	/** List of cities sorted by the cities */
	protected CustomArrayList<Vertex<Integer>> sortedCities;
	
	/**
	 * Constructs the graph.
	 */
	public Graph() {
		highways = new CustomArrayList<>();
		cities = new CustomArrayList<>();
	}
	
	/**
	 * Inserts the edge with weight and vertices defined.
	 * 
	 * @param edge to add
	 */
	public void insertHighway(Highway edge) {
		Vertex<Integer> v1 = edge.getC1();
		Vertex<Integer> v2 = edge.getC2();
		
		int foundIdx = cities.contains(v1);
		boolean city1New = true;
		boolean city2New = true;
		if (foundIdx != -1) {
			v1 = cities.get(foundIdx);
			city1New = false;
		}
		foundIdx = cities.contains(v2);
		if (foundIdx != -1) {
			v2 = cities.get(foundIdx);
			city2New = false;
		}
		
		if (highways.contains(edge) == -1) { // highway does not exist
			highways.add(edge);
			if (city1New) { // if city 1 is new
				v1.neighbors.add(v2);
				cities.add(v1);
			} else { // if city is not new
				if (v1.neighbors.contains(v2) == -1) {
					v1.neighbors.add(v2);
				}
			}
			if (city2New) { // if city 2 is new
				v2.neighbors.add(v1);
				cities.add(v2);
			} else {
				if (v2.neighbors.contains(v1) == -1) {
					v2.neighbors.add(v1);
				}
			}
		}
		
		if (v1.getConnectedHighway().contains(edge) == -1) {
			v1.getConnectedHighway().add(edge);
		}
		if (v2.getConnectedHighway().contains(edge) == -1) {
			v2.getConnectedHighway().add(edge);
		}
	}
	
	/**
	 * Getter for cities.
	 * 
	 * @return the vertices
	 */
	public CustomArrayList<Vertex<Integer>> getCities() {
		return cities;
	}
	
	/**
	 * Getter for the list of sorted cities.
	 * 
	 * @return sortedCities - sorted list of cities
	 */
	public CustomArrayList<Vertex<Integer>> getSortedCities() {
		return sortedCities;
	}
	
	/**
	 * Getter for highways in the graph.
	 * 
	 * @return highways in the graph
	 */
	public CustomArrayList<Highway> getHighway() {
		return highways;
	}
	
	/**
	 * Sorts the cities by the city name.
	 */
	public void sortCities() {
		sortedCities = mergeSort(cities);
	}
	
	/**
	 * Sorts each city's neighbors.
	 */
	public void sortNeighbors() {
		for (int i = 0; i < sortedCities.size(); i++) {
			sortedCities.get(i).sortNeighbors();
		}
	}

	/**
	 * Prints the adjacency list of all the cities sorted in order.
	 * 
	 * @return string representation of sorted list of highways of cities
	 */
	public String getAdjacency() {
		for (int i = 0; i < sortedCities.size(); i++) {
			sortedCities.get(i).sortConnectedHighways();
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < sortedCities.size(); i++) {
			sb.append(String.format("   City %d: -> ", i));
			Vertex<Integer> v = sortedCities.get(i);
//			System.out.print(v.getValue() + " <-> ");
			for (int j = 0; j < v.getConnectedHighway().size(); j++) {
				sb.append(v.getConnectedHighway().get(j).toString());
				if (j != v.getConnectedHighway().size() - 1) {
					sb.append(" -> ");
				}
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
	/**
	 * Merge sorts the list passed in.
	 * 
	 * @param cities to be sorted
	 * @return sorted list by city name
	 */
	private CustomArrayList<Vertex<Integer>> mergeSort(CustomArrayList<Vertex<Integer>> cities) {
		int total = cities.size();
		if (total < 2) {
			return cities;
		} else {
			int midpoint = total / 2;
			CustomArrayList<Vertex<Integer>> left = new CustomArrayList<>();
			CustomArrayList<Vertex<Integer>> right = new CustomArrayList<>();
			for (int i = 0; i < midpoint; i++) {
				left.add(cities.get(i));
			}
			for (int i = midpoint; i < total; i++) {
				right.add(cities.get(i));
			}
			left = mergeSort(left);
			right = mergeSort(right);
			return merge(left, right, cities);
		}
	}
	
	/**
	 * Private helper method for merge sorting that takes in left half and right half of the list as well as the 
	 * full list.
	 * 
	 * @param left half of the list
	 * @param right half of the list
	 * @param whole list
	 * @return whole sorted list
	 */
	private CustomArrayList<Vertex<Integer>> merge(CustomArrayList<Vertex<Integer>> left, CustomArrayList<Vertex<Integer>> right, CustomArrayList<Vertex<Integer>> whole) {
		int leftIndex = 0, rightIndex = 0, wholeIndex = 0;
		while (leftIndex < left.size() && rightIndex < right.size()) {
			if (left.get(leftIndex).getValue() < right.get(rightIndex).getValue()) {
				whole.set(left.get(leftIndex), wholeIndex);
				leftIndex++;
			} else {
				whole.set(right.get(rightIndex), wholeIndex);
				rightIndex++;
			}
			wholeIndex++;
		}
		CustomArrayList<Vertex<Integer>> rest;
		int restIndex;
		if (leftIndex >= left.size()) {
			rest = right;
			restIndex = rightIndex;
		} else {
			rest = left;
			restIndex = leftIndex;
		}
		
		for (int i = restIndex; i < rest.size(); i++) {
			whole.set(rest.get(i), wholeIndex);
			wholeIndex++;
		}
		
		return whole;
	}
}
