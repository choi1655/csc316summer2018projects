package edu.ncsu.csc316.grocerystore2.ui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import edu.ncsu.csc316.grocerystore2.manager.GroceryStoreManager;

import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;

import javax.swing.Action;
import javax.swing.JScrollPane;
import java.awt.TextArea;

/**
 * Swing version main GUI for the Grocery Store Manager software.
 * 
 * @author John Choi
 * @version 07202018
 */
public class GroceryStoreManagerUI extends JFrame {
	
	private GroceryStoreManager gsm;
	
	/**
	 * Generated serial ID.
	 */
	private static final long serialVersionUID = 2035644161673544187L;
	private JTextField customerFilePath;
	private JTextField productFilePath;
	private JTextField productBrandSearch;
	private JTextField productDescSearch;
	private TextArea customersList;
	private TextArea productsList;
	private JButton btnLoadFiles;
	private JButton btnSortCustomersBy;
	private final Action action = new LoadButtonUI();
	private final Action action1 = new SortCustomersByIDButtonUI();
	private final Action action2 = new ProductSearchButtonUI();

	/**
	 * Launch the application.
	 * 
	 * @param args command-line argument
	 */
	public static void main(String[] args) {
		try {
			GroceryStoreManagerUI frame = new GroceryStoreManagerUI();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Constructs the GUI.
	 */
	public GroceryStoreManagerUI() {
		setTitle("Grocery Store Manager - Revised");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 600);
		
		JLabel lblCustomerFile = new JLabel("Customer File: ");
		
		customerFilePath = new JTextField();
		customerFilePath.setColumns(10);
		
		JLabel lblProductFile = new JLabel("Product File: ");
		
		productFilePath = new JTextField();
		productFilePath.setColumns(10);
		
		btnLoadFiles = new JButton("Load Files");
		btnLoadFiles.setAction(action);

		JLabel lblProductSearch = new JLabel("Product Search: ");
		
		JLabel lblBrand = new JLabel("Brand: ");
		
		JLabel lblDescription = new JLabel("Description: ");
		
		productBrandSearch = new JTextField();
		productBrandSearch.setColumns(10);
		
		productDescSearch = new JTextField();
		productDescSearch.setColumns(10);
		
		JButton btnProductSearch = new JButton("Search!");
		btnProductSearch.setAction(action2);
		
		JLabel lblCustomers = new JLabel("Customers");
		
		JLabel lblProducts = new JLabel("Products");
		
		btnSortCustomersBy = new JButton("Sort Customers By ID");
		btnSortCustomersBy.setAction(action1);
		
		JScrollPane customersScrollPane = new JScrollPane();
		JScrollPane productsScrollPane = new JScrollPane();
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(189)
					.addComponent(lblCustomers)
					.addPreferredGap(ComponentPlacement.RELATED, 558, Short.MAX_VALUE)
					.addComponent(lblProducts)
					.addGap(256))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(344, Short.MAX_VALUE)
					.addComponent(btnSortCustomersBy)
					.addGap(88)
					.addComponent(lblProductSearch)
					.addGap(416))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnProductSearch)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(customersScrollPane, GroupLayout.PREFERRED_SIZE, 553, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createSequentialGroup()
										.addGap(116)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(lblDescription)
												.addGap(18)
												.addComponent(productDescSearch, 310, 310, 310))
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(lblBrand)
												.addGap(56)
												.addComponent(productBrandSearch, GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE))))
									.addGroup(groupLayout.createSequentialGroup()
										.addGap(18)
										.addComponent(productsScrollPane, GroupLayout.PREFERRED_SIZE, 507, GroupLayout.PREFERRED_SIZE))))
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblCustomerFile)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(customerFilePath))
								.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblProductFile, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(productFilePath, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE))
								.addComponent(btnLoadFiles))))
					.addGap(42))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCustomerFile)
						.addComponent(customerFilePath, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblProductFile)
						.addComponent(productFilePath, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnLoadFiles)
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCustomers)
						.addComponent(lblProducts))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(customersScrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnSortCustomersBy))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(productsScrollPane, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblProductSearch)
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblBrand)
								.addComponent(productBrandSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDescription)
								.addComponent(productDescSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addGap(22)
					.addComponent(btnProductSearch)
					.addGap(75))
		);
		
		productsList = new TextArea();
		productsList.setEditable(false);
		productsScrollPane.setColumnHeaderView(productsList);
		
		customersList = new TextArea();
		customersList.getScrollbarVisibility();
		customersList.setEditable(false);
		customersList.setSize(100, 100);
		customersScrollPane.setColumnHeaderView(customersList);
		getContentPane().setLayout(groupLayout);
	}
	
	/**
	 * Inner class that defines the Load button's behavior.
	 * 
	 * @author John Choi
	 * @version 06112018
	 */
	private class LoadButtonUI extends AbstractAction {
		/**
		 * Generated serial ID.
		 */
		private static final long serialVersionUID = -5125991464295841963L;
		
		/**
		 * Constructs the load button.
		 */
		public LoadButtonUI() {
			putValue(NAME, "Load Files");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		
		/**
		 * Defines the behavior of the load button.
		 * 
		 * @param e action received
		 */
		public void actionPerformed(ActionEvent e) {
			if (customerFilePath.getText().equals("") || productFilePath.getText().equals("")) {
				@SuppressWarnings("unused")
				AlertBoxUI alert = new AlertBoxUI("Error", "Please specify both files");
			} else {
				try {
					gsm = new GroceryStoreManager(productFilePath.getText(), customerFilePath.getText());
				} catch (IllegalArgumentException ex) {
					return;
				}
				String products = gsm.printProductList();
				String customers = gsm.getUnsortedCustomers();
				customersList.setText(customers);
				productsList.setText(products);
				customersList.setCaretPosition(0);
				productsList.setCaretPosition(0);
				productFilePath.setEditable(false);
				customerFilePath.setEditable(false);
				btnLoadFiles.setEnabled(false);
			}
		}
	}
	
	/**
	 * Inner class that defines the Sort button for customers behavior.
	 * 
	 * @author John Choi
	 * @version 06112018
	 */
	private class SortCustomersByIDButtonUI extends AbstractAction {
		/**
		 * Generated serial ID.
		 */
		private static final long serialVersionUID = -5356645753291898295L;
		
		/**
		 * Constructs the button.
		 */
		public SortCustomersByIDButtonUI() {
			putValue(NAME, "Sort Customers By ID");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		
		/**
		 * Defines the behavior of this button.
		 * 
		 * @param e action received
		 */
		public void actionPerformed(ActionEvent e) {
			if (customersList.getText().equals("")) {
				@SuppressWarnings("unused")
				AlertBoxUI alertBox = new AlertBoxUI("Error", "Customers are not loaded yet");
			} else {
				String sortedCustomers = gsm.getCustomers();
				customersList.setText(sortedCustomers);
				customersList.setCaretPosition(0);
				btnSortCustomersBy.setEnabled(false);
			}
		}
	}
	
	/**
	 * Inner class that defines the product search button's behavior.
	 * 
	 * @author John Choi
	 * @version 06112018
	 */
	private class ProductSearchButtonUI extends AbstractAction {
		
		/**
		 * Generated serial ID.
		 */
		private static final long serialVersionUID = 2160901635072685867L;
		
		/**
		 * Constructs the button.
		 */
		public ProductSearchButtonUI() {
			putValue(NAME, "Search!");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		
		/**
		 * Defines the behavior of this button.
		 * 
		 * @param e action received
		 */
		public void actionPerformed(ActionEvent e) {
			if (productBrandSearch.getText().equals("") || productDescSearch.getText().equals("")) {
				@SuppressWarnings("unused")
				AlertBoxUI alert = new AlertBoxUI("Error!", "Please enter both brand and description.");
			} else if (productsList.getText().equals("")) {
				@SuppressWarnings("unused")
				AlertBoxUI notRunning = new AlertBoxUI("Error!", "Products are not loaded yet");
			} else {
				productsList.setText(gsm.getProduct(productBrandSearch.getText().trim(), productDescSearch.getText().trim()));
				productBrandSearch.setText("");
				productDescSearch.setText("");
			}
		}
	}
}
