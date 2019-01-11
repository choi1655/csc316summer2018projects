/**
 * 
 */
package edu.ncsu.csc316.grocerystore.datastructure;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.ncsu.csc316.grocerystore.objects.Product;

/**
 * Array based list of products.
 * 
 * @author John Choi
 * @version 06082018
 */
public class UnsortedProductList {

	private CustomArrayList<ProductNode> knownProducts;
	private CustomArrayList<Product> products;
//	private CustomLinkedList<ProductNode> knownProducts;
//	private CustomLinkedList<Product> products;
//	
	/**
	 * Inner class that defines a type of product with frequency information.
	 * 
	 * @author John Choi
	 * @version 06042018
	 */
	public class ProductNode {
		private String brand;
		private String description;
		private int frequency;
		
		/**
		 * Constructs single node of product with frequency information.
		 * 
		 * @param brand of the product
		 * @param description of the product
		 */
		public ProductNode(String brand, String description) {
			this.brand = brand;
			this.description = description;
			frequency = 1;
		}
		
		/**
		 * Increases frequency of this product.
		 */
		public void increaseFrequency() {
			frequency++;
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
		 * Getter for the brand of this product.
		 * 
		 * @return brand of this product
		 */
		public String getBrand() {
			return brand;
		}
		
		/**
		 * Getter for the description field.
		 * 
		 * @return description of the product
		 */
		public String getDescription() {
			return description;
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
	}
	
	/**
	 * Constructs unsorted product list.
	 */
	public UnsortedProductList() {
//		knownProducts = new CustomArrayList<>();
//		products = new CustomArrayList<>();
		knownProducts = new CustomArrayList<>();
		products = new CustomArrayList<>();
	}
	
	/**
	 * Getter for the known products list.
	 * 
	 * @return knownProducts list of products with frequency info
	 */
	public CustomList<ProductNode> getKnownProducts() {
		return knownProducts;
	}
	
	/**
	 * Getter for the products list.
	 * 
	 * @return products list of products with possible duplicates
	 */
	public CustomList<Product> getProducts() {
		return products;
	}
	
	/**
	 * Inserts the product into the list of products.
	 * 
	 * @param p product to add
	 */
	public void insertProduct(Product p) {
		products.add(p);
	}
	
	/**
	 * List sorted based on the frequency (highest frequency first, lowest last).
	 * 
	 * @return knownProducts sorted list by frequency
	 */
	public CustomList<ProductNode> getSortedKnownProducts() {
		knownProducts = (CustomArrayList<ProductNode>) mergeSort(knownProducts);
		return knownProducts;
	}
	
	/**
	 * Merge sorts the list passed in.
	 * 
	 * @param knownProducts to be sorted in descending order
	 * @return CustomArrayList sorted products list
	 */
	public CustomList<ProductNode> mergeSort(CustomList<ProductNode> knownProducts) {
		int listSize = knownProducts.size();
		if (listSize < 2) {
			return knownProducts;
		} else {
			int midpoint = listSize / 2;
			CustomList<ProductNode> left = new CustomArrayList<>();
			CustomList<ProductNode> right = new CustomArrayList<>();
			for (int i = 0; i < midpoint; i++) {
				left.add(knownProducts.get(i));
			}
			for (int i = midpoint; i < listSize; i++) {
				right.add(knownProducts.get(i));
			}
			left = (CustomArrayList<ProductNode>) mergeSort(left);
			right = (CustomArrayList<ProductNode>) mergeSort(right);
			return merge(left, right, knownProducts);
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
	private CustomList<ProductNode> merge(CustomList<ProductNode> left, CustomList<ProductNode> right, CustomList<ProductNode> whole) {
		CustomList<ProductNode> remaining;
		int leftIdx = 0, rightIdx = 0, wholeIdx = 0;
		while (leftIdx < left.size() && rightIdx < right.size()) {
			if (left.get(leftIdx).getFrequency() > right.get(rightIdx).getFrequency()) {
				whole.set(left.get(leftIdx), wholeIdx);
				leftIdx++;
			} else {
				whole.set(right.get(rightIdx), wholeIdx);
				rightIdx++;
			}
			wholeIdx++;
		}
		int restIndex;
		if (leftIdx >= left.size()) {
			remaining = right;
			restIndex = rightIdx;
		} else {
			remaining = left;
			restIndex = leftIdx;
		}
		
		for (int i = restIndex; i < remaining.size(); i++) {
			whole.set(remaining.get(i), wholeIdx);
			wholeIdx++;
		}
		
		return whole;
	}
	
	/**
	 * Takes in the product file and generates the products list.
	 * 
	 * @param filename product file
	 * @throws FileNotFoundException thrown if the product file cannot be found
	 */
	public void productFileIO(String filename) throws FileNotFoundException {
		File myFile = new File(filename);
		Scanner readFile = new Scanner(myFile);
		while (readFile.hasNextLine()) {
			String brand = readFile.next().trim();
			String description = readFile.nextLine().trim();
			Product p = new Product(brand, description);
			products.add(p);
//			readFile.nextLine();// probably dont need this
		}
		readFile.close();
	}
	
	/**
	 * Generates the known products based on the current products list.
	 */
	public void generateKnownProducts() {
		boolean found;
		for (int i = 0; i < products.size(); i++) {
			found = false;
			Product tempP = products.get(i);
			for (int j = 0; j < knownProducts.size(); j++) {
				ProductNode tempN = knownProducts.get(j);
				
				//if the product is known
				if (tempP.getBrand().equals(tempN.getBrand()) && tempP.getDescription().equals(tempN.getDescription())) {
					knownProducts.get(j).increaseFrequency();
					found = true;
				}
			} //end of inner loop
			if (!found) {
				ProductNode newest = new ProductNode(tempP.getBrand(), tempP.getDescription());
				knownProducts.add(newest);
			}
		} //end of outer loop
	}
}
