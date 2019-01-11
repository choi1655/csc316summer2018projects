/**
 * 
 */
package edu.ncsu.csc316.transportation_manager.highway;

/**
 * Defines cost of highway.
 * 
 * @author John Choi
 * @version 07112018
 */
public class Cost implements Comparable<Cost> {

	private double cost;
	
	/**
	 * Constructs a cost.
	 * 
	 * @param cost of highway
	 */
	public Cost(double cost) {
		this.cost = cost;
	}
	
	/**
	 * Getter for cost.
	 * 
	 * @return the cost
	 */
	public double getCost() {
		return cost;
	}

	/**
	 * Returns difference between two costs.
	 * 
	 * @param o cost to compare
	 * @return difference between two highway costs
	 */
	@Override
	public int compareTo(Cost o) {
		if (cost - o.getCost() > 0) {
			return 1;
		} else if (cost - o.getCost() < 0) {
			return -1;
		} else {
			return 0;
		}
	}
}
