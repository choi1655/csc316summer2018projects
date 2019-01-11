/**
 * 
 */
package edu.ncsu.csc316.grocerystore2.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc316.grocerystore2.datastructure.CustomArrayList;
import edu.ncsu.csc316.grocerystore2.datastructure.hashmap.CustomerHashTable;
import edu.ncsu.csc316.grocerystore2.datastructure.hashmap.ProductHashTable;
import edu.ncsu.csc316.grocerystore2.datastructure.ProductNode;
import edu.ncsu.csc316.grocerystore2.objects.Customer;
import edu.ncsu.csc316.grocerystore2.objects.Product;
import edu.ncsu.csc316.grocerystore2.ui.AlertBoxUI;

/**
 * The main controller for this software.
 * Handles file IO and printing the list of customers and products.
 * 
 * @author John Choi
 * @version 07262018
 */
public class GroceryStoreManager {

	private ProductHashTable productHash;
	private CustomerHashTable customerHash;
	private CustomArrayList<Customer> customers;
	private CustomArrayList<ProductNode> products;
	
	/**
	 * Constructs a new GroceryStoreManager object using
	 * the two input files of customers and products
	 * Required method.
	 * 
	 * @param pathToProductFile - the path to the product file
	 * @param pathToCustomerFile - the path to the customer files
	 */
	public GroceryStoreManager(String pathToProductFile, String pathToCustomerFile) {
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
	 * Required method.
	 * 
	 * @return the sorted list of customers
	 */
	public String getCustomers() {
		sortCustomerByID();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < customers.size(); i++) {
			sb.append(customers.get(i).toString());
			if (i != customers.size() - 1) {
				sb.append("\n");
			}
		}
//		System.out.println(sb.toString());
		return sb.toString();
	}

	/**
	 * Returns the list of customers unsorted to start with.
	 * 
	 * @return String unsorted list of customers
	 */
	public String getUnsortedCustomers() {
		customers = customerHash.toList();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < customers.size(); i++) {
			sb.append(customers.get(i));
			if (i != customers.size() - 1) {
				sb.append("\n");
			}
		}
		return sb.toString();
	}
	
	/**
	 * Returns the product as a String in the following format:
	 * Product [brand=Wolf, description=soda, frequency=698]
	 * Required method.
	 * 
	 * @param brand - the brand of the product to lookup
	 * @param description - the description of the product to lookup
	 * @return the full product information
	 */
	public String getProduct(String brand, String description) {
		ProductNode pn = productHash.get(brand, description);
		if (pn == null) {
			return String.format("Product not found!");
		}
		return pn.toString();
	}
	
	/**
	 * Sorts the list of customers by their IDs in ascending order.
	 */
	public void sortCustomerByID() {
		customers = mergeSortCustomers(customers);
	}
	
	/**
	 * Merge sorts the list of products by frequencies in descending order.
	 * 
	 * @param products list of customers to sort
	 * @return CustomArrayList sorted list
	 */
	private CustomArrayList<ProductNode> mergeSortProducts(CustomArrayList<ProductNode> products) {
		int totalElements = products.size();
		if (totalElements < 2) {
			return products;
		} else {
			int midpoint = totalElements / 2;
			CustomArrayList<ProductNode> left = new CustomArrayList<>();
			CustomArrayList<ProductNode> right = new CustomArrayList<>();
			for (int i = 0; i < midpoint; i++) {
				left.add(products.get(i));
			}
			for (int i = midpoint; i < totalElements; i++) {
				right.add(products.get(i));
			}
			left = mergeSortProducts(left);
			right = mergeSortProducts(right);
			return mergeProducts(left, right, products);
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
	private CustomArrayList<ProductNode> mergeProducts(CustomArrayList<ProductNode> left, CustomArrayList<ProductNode> right, CustomArrayList<ProductNode> whole) {
		CustomArrayList<ProductNode> remaining;
		int leftIdx = 0, rightIdx = 0, wholeIdx = 0;
		while (leftIdx < left.size() && rightIdx < right.size()) {
			if (left.get(leftIdx).compareTo(right.get(rightIdx)) > 0) {
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
	 * Merge sorts the list of customers by IDs in ascending order.
	 * 
	 * @param customers list of customers to sort
	 * @return CustomArrayList sorted list
	 */
	private CustomArrayList<Customer> mergeSortCustomers(CustomArrayList<Customer> customers) {
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
			left = mergeSortCustomers(left);
			right = mergeSortCustomers(right);
			return mergeCustomers(left, right, customers);
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
	private CustomArrayList<Customer> mergeCustomers(CustomArrayList<Customer> left, CustomArrayList<Customer> right, CustomArrayList<Customer> whole) {
		CustomArrayList<Customer> remaining;
		int leftIdx = 0, rightIdx = 0, wholeIdx = 0;
		while (leftIdx < left.size() && rightIdx < right.size()) {
			if (left.get(leftIdx).compareTo(right.get(rightIdx)) < 0) {
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
		products = productHash.toList();
		products = mergeSortProducts(products);
		for (int i = 0; i < products.size(); i++) {
			sb.append(products.get(i).toString() + "\n");
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
		productHash = new ProductHashTable();
		File myFile = new File(filename);
		Scanner readFile = new Scanner(myFile);
		while (readFile.hasNextLine()) {
			try {
				String brand = readFile.next();
				String desc = readFile.nextLine().trim();
				Product product = new Product(brand, desc);
				//System.out.println(String.format("%s %s", product.getBrand(), product.getDescription()));
				productHash.insert(product);
			} catch (NoSuchElementException e) {
				break;
			}
		}
		readFile.close();
	}
	
	/**
	 * Reads the customer file and adds each customer to the customers list.
	 * 
	 * @param filename to read
	 * @throws FileNotFoundException thrown if the file does not exist
	 */
	public void fileCustomerIO(String filename) throws FileNotFoundException {
		customerHash = new CustomerHashTable();
		File myFile = new File(filename);
		Scanner readFile = new Scanner(myFile);
		while (readFile.hasNextLine()) {
			String singleLine = readFile.nextLine();
//			System.out.println(singleLine);
			String[] temp = singleLine.split(","); //causing OutOfMemoryError - not anymore
			String id = temp[0].trim();
			String name = temp[1].trim();
			String state = temp[2].trim();
			String zip = temp[3].trim();
			Customer customer = new Customer(id, name, state, zip);
			customerHash.insert(id, customer);
			singleLine = null;
		}
		readFile.close();
		customers = customerHash.toList();
	}
}
