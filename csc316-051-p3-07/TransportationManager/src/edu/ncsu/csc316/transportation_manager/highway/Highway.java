/**
 * 
 */
package edu.ncsu.csc316.transportation_manager.highway;

import edu.ncsu.csc316.transportation_manager.datastructure.Vertex;

/**
 * Defines a highway.
 * Required class for Jenkins.
 * 
 * @author John Choi
 * @version 07232018
 */
public class Highway implements Comparable<Highway> {
	
	private int city1;
	private int city2;
	private Cost cost;
	private Asphalt asphalt;
	private Vertex<Integer> c1;
	private Vertex<Integer> c2;

	/**
	 * Constructs a Highway with the given information.
	 * Required method.
	 * 
	 * @param city1 city1 of the highway
	 * @param city2 city2 of the highway
	 * @param cost cost of building the highway
	 * @param asphalt amount (in miles) of asphalt needed to build the highway
	 */
	public Highway(int city1, int city2, double cost, double asphalt) {
		this.city1 = city1;
		this.city2 = city2;
		this.cost = new Cost(cost);
		this.asphalt = new Asphalt(asphalt);
		c1 = new Vertex<>(city1);
		c2 = new Vertex<>(city2);
	}
	
	/**
	 * Getter for cost.
	 * 
	 * @return cost
	 */
	public Cost getCost() {
		return cost;
	}
	
	/**
	 * Getter for asphalt.
	 * 
	 * @return asphalt
	 */
	public Asphalt getAsphalt() {
		return asphalt;
	}
	
	/**
	 * Getter for vertex 1.
	 * 
	 * @return the c1
	 */
	public Vertex<Integer> getC1() {
		return c1;
	}
	
	/**
	 * Getter for vertex 2.
	 * 
	 * @return the c2
	 */
	public Vertex<Integer> getC2() {
		return c2;
	}

	/**
	 * Returns true if two highways are same.
	 * 
	 * @param obj highway to compare
	 * @return true if same
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Highway other = (Highway) obj;
		if (city1 != other.city1)
			return false;
		if (city2 != other.city2)
			return false;
		return true;
	}
	
	/**
	 * Returns a string representation of the Highway
	 * in the format:
	 * 
	 * Highway[city1=X, city2=X, cost=X.X, asphalt=X.X]
	 * Required method.
	 * 
	 * @return the string representation of the highway
	 */
	@Override
	public String toString() {
		return String.format("Highway[city1=%d, city2=%d, cost=%.1f, asphalt=%.1f]", city1, city2, cost.getCost(), asphalt.getDistance());
	}

	/**
	 * Used to compare two highway objects.
	 * 
	 * @param o highway object to compare with
	 * @return difference between two highways
	 */
	@Override
	public int compareTo(Highway o) {
		if (city1 < o.city1) {
			return -1;
		} else if (city1 > o.city1) {
			return 1;
		} else {
			if (city2 < o.city2) {
				return -1;
			} else if (city2 > o.city2) {
				return 1;
			} else {
				return 0;
			}
		}
	} 
}
