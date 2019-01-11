/**
 * 
 */
package edu.ncsu.csc316.grocerystore2.datastructure;

import edu.ncsu.csc316.grocerystore2.objects.Product;

/**
 * Defines a type of product with frequency information.
 * 
 * @author John Choi
 * @version 07262018
 */
public class ProductNode implements Comparable<ProductNode> {
	private String brand;
	private String description;
	private int frequency;
	private CustomArrayList<ProductNode> duplicateHashcode;
	
	/**
	 * Constructs single node of product based on product object passed in.
	 * 
	 * @param p - product object
	 */
	public ProductNode(Product p) {
		this.brand = p.getBrand();
		this.description = p.getDescription();
		frequency = 1;
		duplicateHashcode = new CustomArrayList<>();
	}
	
	/**
	 * Increases frequency of this product.
	 */
	public void increaseFrequency() {
		frequency++;
	}
	
	/**
	 * Getter for duplicate nodes with same hashcode.
	 * 
	 * @return duplicates list of duplicate products
	 */
	public CustomArrayList<ProductNode> getDuplicates() {
		return duplicateHashcode;
	}
	
	/**
	 * Getter for the frequency.
	 * 
	 * @return frequency of this product
	 */
	public int getFrequency() {
		return frequency;
	}

	/** 
	 * Returns true if two product nodes are same.
	 * 
	 * @param otherProduct product node to compare with
	 * @return true if two products are equal
	 */
	public boolean equalProducts(ProductNode otherProduct) {
		if (brand.equals(otherProduct.brand) && description.equals(otherProduct.description)) {
			return true;
		}
		return false;
	}

	/**
	 * Generates a hash code for this object.
	 * *** Code taken from Mr. Gaweda's TYPOS research platform. ***
	 * 
	 * @return hash code for this object
	 */
	public int generateHashCode() {
		String s = brand + description;
		// bit shifting
		int hashCode = 0;
		for (int i = 0; i < s.length(); i++) {
			// shift the current hash code 5 bits to the left
			hashCode = hashCode << 5;
			// convert the char into an integer an add to hashCode
			hashCode += (int) s.charAt(i);
		}
		return hashCode;
	}

	/**
	 * Prints this node's information.
	 * 
	 * @return String information in format
	 */
	@Override
	public String toString() {
		return String.format("Product [brand=%s, description=%s, frequency=%d]", brand, description, frequency);
	}

	/**
	 * Used to compare two frequencies.
	 * 
	 * @param o product node to compare with
	 * @return difference between two objects
	 */
	@Override
	public int compareTo(ProductNode o) {
		if (frequency < o.frequency) {
			return -1;
		} else if (frequency > o.frequency) {
			return 1;
		} else {
			return 0;
		}
	}
}
