/**
 * 
 */
package edu.ncsu.csc316.grocerystore2.datastructure.hashmap;

import edu.ncsu.csc316.grocerystore2.datastructure.CustomArrayList;
import edu.ncsu.csc316.grocerystore2.datastructure.ProductNode;
import edu.ncsu.csc316.grocerystore2.objects.Product;

/**
 * Class that defines hash table for product nodes.
 * 
 * @author John Choi
 * @version 07262018
 */
public class ProductHashTable {
	
	private int size;
	private ProductNode[] products;
	private int capacity;

	/**
	 * Constructs a hash table for this software.
	 */
	public ProductHashTable() {
		size = 0;
		capacity = 1993;
		products = new ProductNode[capacity];
	}
	
	/**
	 * Inserts a product node with the brand and description
	 * 
	 * @param p product node to insert
	 */
	public void insert(Product p) {
		ProductNode pn = new ProductNode(p);
		int hashcode = compressHashcode(pn.generateHashCode());
		if (products[hashcode] != null) {
			/* Handles potential collision */
			if (!products[hashcode].equalProducts(pn)) {
				/* confirmed collision */
				// check to see if there's same product
				boolean found = false;
				for (int i = 0; i < products[hashcode].getDuplicates().size(); i++) {
					if (pn.equalProducts(products[hashcode].getDuplicates().get(i))) {
						found = true;
						products[hashcode].getDuplicates().get(i).increaseFrequency();
						break;
					}
				}
				// not found
				if (!found)
					products[hashcode].getDuplicates().add(pn);
			} else {
				/* just a duplicate so simply increase frequency */
				products[hashcode].increaseFrequency();
			}
		} else {
			products[hashcode] = pn;
		}
		size++;
	}
	
	/**
	 * Returns a product node with passed in key.
	 * 
	 * @param brand of the product
	 * @param description of the product
	 * @return found product node, null if product is not found
	 */
	public ProductNode get(String brand, String description) {
		Product product = new Product(brand, description);
		ProductNode productNode = new ProductNode(product);
		int hashcode = compressHashcode(productNode.generateHashCode());
		if (products[hashcode] == null) {
			return null;
		}
		if (products[hashcode].equalProducts(productNode)) {
			return products[hashcode];
		} else {
			for (int i = 0; i < products[hashcode].getDuplicates().size(); i++) {
				if (products[hashcode].getDuplicates().get(i).equalProducts(productNode)) {
					return products[hashcode].getDuplicates().get(i);
				}
			}
			return null;
		}
	}

	/**
	 * Returns true if the hash table is empty.
	 * 
	 * @return true if hash table is empty
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * Returns the current size of the hash table.
	 * 
	 * @return size of the table
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Returns the array list version of the hash table.
	 * 
	 * @return array list version of the table
	 */
	public CustomArrayList<ProductNode> toList() {
		CustomArrayList<ProductNode> result = new CustomArrayList<>();
		for (int i = 0; i < products.length; i++) {
			if (products[i] != null) {
				result.add(products[i]);
				for (int j = 0; j < products[i].getDuplicates().size(); j++) {
					result.add(products[i].getDuplicates().get(j));
				}
			}
		}
		return result;
	}
	
	/**
	 * Compresses hash code to fit in the capacity.
	 * 
	 * @param hashcode to compress
	 * @return compressed hash code
	 */
	private int compressHashcode(int hashcode) {
		int returnVal = (31 * hashcode + 51) % capacity;
		return Math.abs(returnVal);
	}
}
