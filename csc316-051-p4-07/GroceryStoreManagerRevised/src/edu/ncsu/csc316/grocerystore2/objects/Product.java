/**
 * 
 */
package edu.ncsu.csc316.grocerystore2.objects;

/**
 * Represents a single product.
 * 
 * @author John Choi
 * @version 05302018
 */
public class Product {

	private String brand;
	private String description;
	
	/**
	 * Constructs one product with brand and description passed in.
	 * 
	 * @param brand of the product
	 * @param description of the product
	 */
	public Product(String brand, String description) {
		this.brand = brand;
		this.description = description;
	}

	/**
	 * Getter for the brand field.
	 * 
	 * @return the brand of the product
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * Getter for the description field.
	 * 
	 * @return the description of the product
	 */
	public String getDescription() {
		return description;
	}
}
