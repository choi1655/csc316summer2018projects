/**
 * 
 */
package edu.ncsu.csc316.transportation_manager.city;

/**
 * Defines a city.
 * 
 * @author John Choi
 * @version 07232018
 */
public class City implements Comparable<City> {
	
	private int name;

	/**
	 * Constructs one city.
	 * 
	 * @param name of the city
	 */
	public City(int name) {
		this.name = name;
	}

	/**
	 * Getter for city name.
	 * 
	 * @return the name of the city
	 */
	public int getName() {
		return name;
	}

	/**
	 * Returns the difference between two cities.
	 * 
	 * @param o city to compare
	 * @return difference
	 */
	@Override
	public int compareTo(City o) {
		if (name - o.getName() < 0) {
			return -1;
		} else if (name - o.getName() > 0) {
			return 1;
		} else {
			return 0;
		}
	}
}
