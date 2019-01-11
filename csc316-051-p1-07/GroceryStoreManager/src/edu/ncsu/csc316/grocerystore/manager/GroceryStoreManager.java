/**
 * 
 */
package edu.ncsu.csc316.grocerystore.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.ncsu.csc316.grocerystore.datastructure.CustomArrayList;
import edu.ncsu.csc316.grocerystore.datastructure.UnsortedProductList;
import edu.ncsu.csc316.grocerystore.datastructure.UnsortedProductList.ProductNode;
import edu.ncsu.csc316.grocerystore.objects.Customer;
import edu.ncsu.csc316.grocerystore.ui.AlertBoxUI;

/**
 * The main controller for this software.
 * Handles file IO and printing the list of customers and products.
 * 
 * @author John Choi
 * @version 06112018
 */
public class GroceryStoreManager {

	private CustomArrayList<Customer> customers;
	private UnsortedProductList upl;
	
	/**
	 * Constructs a new GroceryStoreManager object using
	 * the two input files of customers and products
	 * 
	 * @param pathToProductFile - the path to the product file
	 * @param pathToCustomerFile - the path to the customer files
	 */
	public GroceryStoreManager(String pathToProductFile, String pathToCustomerFile) {
		customers = new CustomArrayList<Customer>();
		upl = new UnsortedProductList();
		try {
			fileCustomerIO(pathToCustomerFile);
		} catch (FileNotFoundException e) {
			@SuppressWarnings("unused")
			AlertBoxUI fileNotFoundAlert = new AlertBoxUI("Error", "Customer file cannot be opened");
			throw new IllegalArgumentException();
		}
		try {
			fileProductIO(pathToProductFile);
		} catch (FileNotFoundException e) {
			@SuppressWarnings("unused")
			AlertBoxUI fileNotFoundAlert = new AlertBoxUI("Error", "Product file cannot be opened");
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Returns the list of customers sorted in ascending order by customer ID 
	 * as a String in the following format:
	 * 
	 * Customer [id=C0000473, company=Wigmann's, state=DE, zipcode=45272]
	 * Customer [id=C0000646, company=Super Food, state=CA, zipcode=22962]
	 * Customer [id=C0000679, company=Martino's, state=SD, zipcode=05989]
	 * ... and so on
	 * 
	 * @return the sorted list of customers
	 */
	public String getCustomers() {
		sortCustomerByID();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < customers.size(); i++) {
			sb.append(customers.get(i).toString());
		}
		return sb.toString();
	}

	/**
	 * Returns the list of customers unsorted to start with.
	 * 
	 * @return String unsorted list of customers
	 */
	public String getUnsortedCustomers() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < customers.size(); i++) {
			sb.append(customers.get(i).toString());
		}
		return sb.toString();
	}
	
	/**
	 * Returns the product as a String in the following format:
	 * Product [brand=Wolf, description=soda, frequency=698]
	 * 
	 * @param brand - the brand of the product to lookup
	 * @param description - the description of the product to lookup
	 * @return the full product information
	 */
	public String getProduct(String brand, String description) {
		int size = upl.getKnownProducts().size();
		String compareBrand;
		String compareDesc;
		for (int i = 0; i < size; i++) {
			ProductNode temp = upl.getKnownProducts().get(i);
			compareBrand = temp.getBrand();
			compareDesc = temp.getDescription();
			if (brand.equals(compareBrand) && description.equals(compareDesc)) {
				return temp.toString();
			}
		}
		//if not found
		return String.format("Product not found\n");
	}
	
	/**
	 * Reads the customer file and adds each customer to the customers list.
	 * 
	 * @param filename to read
	 * @throws FileNotFoundException thrown if the file does not exist
	 */
	public void fileCustomerIO(String filename) throws FileNotFoundException {
		File myFile = new File(filename);
		Scanner readFile = new Scanner(myFile);
		while (readFile.hasNextLine()) {
//			String singleLine = readFile.nextLine();
//			Scanner scanner = new Scanner(singleLine);
//			scanner.useDelimiter(",");
//			String id = scanner.next().trim();
//			String company = scanner.next().trim();
//			String state = scanner.next().trim();
//			String zipcode = scanner.next().trim();
//			Customer customer = new Customer(id, company, state, zipcode);
//			customers.add(customer);
////			readFile.nextLine();
//			scanner.close();
			String singleLine = readFile.nextLine();
			String[] temp = singleLine.split(",\\s*");
			Customer customer = new Customer(temp[0], temp[1], temp[2], temp[3]);
			customers.add(customer);
		}
		readFile.close();
	}
	
	/**
	 * Sorts the list of customers by their IDs in ascending order.
	 */
	public void sortCustomerByID() {
		customers = mergeSort(customers);
	}
	
	/**
	 * Merge sorts the list of customers by IDs in ascending order.
	 * 
	 * @param customers list of customers to sort
	 * @return CustomArrayList sorted list
	 */
	public CustomArrayList<Customer> mergeSort(CustomArrayList<Customer> customers) {
		int totalElements = customers.size();
		if (totalElements < 2) {
			return customers;
		} else {
			int midpoint = totalElements / 2;
			CustomArrayList<Customer> left = new CustomArrayList<>();
			CustomArrayList<Customer> right = new CustomArrayList<>();
			for (int i = 0; i < midpoint; i++) {
				left.add(customers.get(i));
			}
			for (int i = midpoint; i < totalElements; i++) {
				right.add(customers.get(i));
			}
			left = mergeSort(left);
			right = mergeSort(right);
			return merge(left, right, customers);
		}
	}
	
	/**
	 * Helper method for merge sorting that takes in left half, right half, and the full list.
	 * 
	 * @param left half of the list
	 * @param right half of the list
	 * @param whole list
	 * @return whole sorted list
	 */
	private CustomArrayList<Customer> merge(CustomArrayList<Customer> left, CustomArrayList<Customer> right, CustomArrayList<Customer> whole) {
		CustomArrayList<Customer> remaining;
		int leftIdx = 0, rightIdx = 0, wholeIdx = 0;
		while (leftIdx < left.size() && rightIdx < right.size()) {
			if (left.get(leftIdx).compareTo(right.get(rightIdx)) < 1) {
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
	 * Default product list when software starts.
	 * Gets cleared if user searches for a product.
	 * 
	 * @return String list of products sorted by the frequency
	 */
	public String printProductList() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < upl.getSortedKnownProducts().size(); i++) {
			sb.append(upl.getSortedKnownProducts().get(i).toString() + "\n");
		}
		return sb.toString();
	}
	
	/**
	 * Handles the product file IO.
	 * 
	 * @param filename product file to read
	 * @throws FileNotFoundException thrown if the product file cannot be found
	 */
	public void fileProductIO(String filename) throws FileNotFoundException {
		upl.productFileIO(filename);
		upl.generateKnownProducts();
		upl.getSortedKnownProducts();
	}
}
