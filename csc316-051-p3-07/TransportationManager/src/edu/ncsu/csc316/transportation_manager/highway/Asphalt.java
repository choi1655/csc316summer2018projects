/**
 * 
 */
package edu.ncsu.csc316.transportation_manager.highway;

/**
 * Defines the asphalt with its distance.
 * 
 * @author John Choi
 * @version 07112018
 */
public class Asphalt implements Comparable<Asphalt> {

	private double distance;
	
	/**
	 * Constructs asphalt.
	 * 
	 * @param distance of the asphalt.
	 */
	public Asphalt(double distance) {
		this.distance = distance;
	}
	
	/**
	 * Getter for distance.
	 * 
	 * @return distance of asphalt
	 */
	public double getDistance() {
		return distance;
	}

	/**
	 * Compares the difference between two asphalt distances.
	 * 
	 * @param o asphalt object to compare
	 * @return the difference
	 */
	@Override
	public int compareTo(Asphalt o) {
		if (distance - o.getDistance() > 0) {
			return 1;
		} else if (distance - o.getDistance() < 0) {
			return -1;
		} else {
			return 0;
		}
	}
}
