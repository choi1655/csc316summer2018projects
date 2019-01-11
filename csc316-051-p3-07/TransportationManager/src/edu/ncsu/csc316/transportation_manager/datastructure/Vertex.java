/**
 * 
 */
package edu.ncsu.csc316.transportation_manager.datastructure;

import edu.ncsu.csc316.transportation_manager.highway.Highway;

/**
 * Class that defines the vertex of the graph.
 * Source code taken from Mr. Gaweda's TYPOS research platform.
 * Modified to fit this software's needs.
 * 
 * @author Adam Gaweda
 * @author John Choi
 * @version 07232018
 * 
 * @param <E> generic type
 */
public class Vertex<E extends Comparable<E>> implements Comparable<Vertex<E>> {
	
	/** value of the vertex */
	protected E value;
	/** neighbor vertices of this vertex */
	protected CustomArrayList<Vertex<E>> neighbors;
	/** sorted list of neighbors */
	protected CustomArrayList<Vertex<E>> sortedNeighbors;
	/** connected highways */
	protected CustomArrayList<Highway> connectedHighways;
	/** sorted list of highways */
	protected CustomArrayList<Highway> sortedConnectedHighways;
	
	/**
	 * Constructs a vertex with a value passed in.
	 * 
	 * @param value of the vertex
	 */
	public Vertex(E value) {
		this.value = value;
		neighbors = new CustomArrayList<>();
		connectedHighways = new CustomArrayList<>();
	}
	
	/**
	 * Getter for value.
	 * 
	 * @return the value
	 */
	public E getValue() {
		return value;
	}

	/**
	 * Getter for neighbors.
	 * 
	 * @return the neighbors
	 */
	public CustomArrayList<Vertex<E>> getNeighbors() {
		return neighbors;
	}
	
	/**
	 * Returns the list of sorted neighbors.
	 * 
	 * @return list of sorted neighbors
	 */
	public CustomArrayList<Vertex<E>> getSortedNeighbors() {
		return sortedNeighbors;
	}
	
	/**
	 * Returns the list of connected highways.
	 * 
	 * @return the list of connected highways
	 */
	public CustomArrayList<Highway> getConnectedHighway() {
		return connectedHighways;
	}
	
	/**
	 * Sorts the highways by their city names.
	 */
	public void sortNeighbors() {
		sortedNeighbors = mergeSort(neighbors);
	}
	
	/**
	 * Sorts the connected highways by their city names.
	 */
	public void sortConnectedHighways() {
		sortedConnectedHighways = mergeSortHighway(connectedHighways);
	}
	
	/**
	 * Merge sorts the list passed in.
	 * 
	 * @param highways to be sorted
	 * @return sorted list by highway
	 */
	private CustomArrayList<Highway> mergeSortHighway(CustomArrayList<Highway> connectedHighways) {
		int total = connectedHighways.size();
		if (total < 2) {
			return connectedHighways;
		} else {
			int midpoint = total / 2;
			CustomArrayList<Highway> left = new CustomArrayList<>();
			CustomArrayList<Highway> right = new CustomArrayList<>();
			for (int i = 0; i < midpoint; i++) {
				left.add(connectedHighways.get(i));
			}
			for (int i = midpoint; i < total; i++) {
				right.add(connectedHighways.get(i));
			}
			left = mergeSortHighway(left);
			right = mergeSortHighway(right);
			return mergeHighway(left, right, connectedHighways);
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
	private CustomArrayList<Highway> mergeHighway(CustomArrayList<Highway> left, CustomArrayList<Highway> right, CustomArrayList<Highway> whole) {
		int leftIndex = 0, rightIndex = 0, wholeIndex = 0;
		while (leftIndex < left.size() && rightIndex < right.size()) {
			if (left.get(leftIndex).compareTo(right.get(rightIndex)) < 0) {
				whole.set(left.get(leftIndex), wholeIndex);
				leftIndex++;
			} else {
				whole.set(right.get(rightIndex), wholeIndex);
				rightIndex++;
			}
			wholeIndex++;
		}
		CustomArrayList<Highway> rest;
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
	
	/**
	 * Merge sorts the list passed in.
	 * 
	 * @param cities to be sorted
	 * @return sorted list by city name
	 */
	private CustomArrayList<Vertex<E>> mergeSort(CustomArrayList<Vertex<E>> cities) {
		int total = cities.size();
		if (total < 2) {
			return cities;
		} else {
			int midpoint = total / 2;
			CustomArrayList<Vertex<E>> left = new CustomArrayList<>();
			CustomArrayList<Vertex<E>> right = new CustomArrayList<>();
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
	private CustomArrayList<Vertex<E>> merge(CustomArrayList<Vertex<E>> left, CustomArrayList<Vertex<E>> right, CustomArrayList<Vertex<E>> whole) {
		int leftIndex = 0, rightIndex = 0, wholeIndex = 0;
		while (leftIndex < left.size() && rightIndex < right.size()) {
			if (left.get(leftIndex).compareTo(right.get(rightIndex)) < 0) {
				whole.set(left.get(leftIndex), wholeIndex);
				leftIndex++;
			} else {
				whole.set(right.get(rightIndex), wholeIndex);
				rightIndex++;
			}
			wholeIndex++;
		}
		CustomArrayList<Vertex<E>> rest;
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

	/**
	 * Used to calculate differences between two vertices.
	 * 
	 * @param object to compare
	 * @return difference between two objects
	 */
	@Override
	public int compareTo(Vertex<E> object) {
		return value.compareTo(object.value);
	}
	
	/**
	 * Returns true if the two vertices are equal.
	 * 
	 * @param o vertex to compare with
	 * @return true if the two vertices are equal
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object o) {
		if (o != null && o instanceof Vertex) {
			return this.value.compareTo(((Vertex<E>) o).value) == 0;
		}
		return false;
	}
}
