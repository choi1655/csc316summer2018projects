package edu.ncsu.csc316.security_manager.ui;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import edu.ncsu.csc316.security_manager.manager.SecurityTreeManager;

import javax.swing.JScrollPane;
import java.awt.TextArea;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;

/**
 * Logs View for Security Tree Manager.
 * 
 * @author John Choi
 * @version 07032018
 */
public class LogsGUI extends JFrame {
	
	private SecurityTreeManager stm;
	
	/**
	 * Generated serial ID.
	 */
	private static final long serialVersionUID = -5324510339533350750L;
	private JTextField logFileInput;
	private JTextField dateSearchTextField;
	private JButton btnLoad;
	private JButton btnSearchLog;
	private TextArea searchResult;
	private TextArea traversal;
	private final Action action = new LoadButtonGUI();
	private final Action action1 = new SearchButtonGUI();
	private boolean fileLoaded = false;

	/**
	 * Launch the application.
	 * 
	 * @param args command-line argument
	 */
//	public static void main(String[] args) {
//		try {
//			LogsGUI frame = new LogsGUI();
//			frame.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Constructs the GUI.
	 */
	public LogsGUI() {
		setTitle("Security Tree Manager - Log Entries");
		setResizable(false);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 1300, 600);
		
		JLabel lblEnterUnsortedLog = new JLabel("Enter Unsorted Log File: ");
		
		logFileInput = new JTextField();
		logFileInput.setColumns(10);
		
		btnLoad = new JButton("Load");
		btnLoad.setAction(action);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblLevelorderTraversalOf = new JLabel("Level-Order Traversal of The Log File");
		
		JScrollPane scrollPane1 = new JScrollPane();
		
		JLabel lblSearchResult = new JLabel("Search Result");
		
		JLabel lblEnterDateIn = new JLabel("Enter Date in MM-DD-YYYY format: ");
		
		dateSearchTextField = new JTextField();
		dateSearchTextField.setColumns(10);
		
		btnSearchLog = new JButton("Search Log");
		btnSearchLog.setEnabled(false);
		btnSearchLog.setAction(action1);
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(25)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnLoad)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblEnterUnsortedLog)
									.addGap(18)
									.addComponent(logFileInput, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED))))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 603, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(218)
							.addComponent(lblLevelorderTraversalOf)))
					.addGap(38)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblEnterDateIn)
								.addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 581, GroupLayout.PREFERRED_SIZE))
							.addGap(39))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGap(193)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnSearchLog)
								.addComponent(dateSearchTextField, GroupLayout.PREFERRED_SIZE, 254, GroupLayout.PREFERRED_SIZE))
							.addGap(179))))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(926, Short.MAX_VALUE)
					.addComponent(lblSearchResult)
					.addGap(290))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblSearchResult)
					.addGap(5)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(7)
							.addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE)
							.addGap(17)
							.addComponent(lblEnterDateIn)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(dateSearchTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnSearchLog))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblEnterUnsortedLog)
								.addComponent(logFileInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnLoad)
							.addGap(31)
							.addComponent(lblLevelorderTraversalOf)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 408, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(17, Short.MAX_VALUE))
		);
		
		searchResult = new TextArea();
		searchResult.setEditable(false);
		scrollPane1.setViewportView(searchResult);
		
		traversal = new TextArea();
		traversal.setEditable(false);
		scrollPane.setViewportView(traversal);
		getContentPane().setLayout(groupLayout);
		super.setLocationRelativeTo(null); //centers screen
		super.setVisible(true);
	}
	
	/**
	 * Inner class that defines behavior of load button.
	 * 
	 * @author John Choi
	 * @version 07022018
	 */
	private class LoadButtonGUI extends AbstractAction {
		/**
		 * Generated serial.
		 */
		private static final long serialVersionUID = 8138117572214998327L;
		
		/**
		 * Constructs this button.
		 */
		public LoadButtonGUI() {
			putValue(NAME, "Load");
			putValue(SHORT_DESCRIPTION, "loads file");
		}
		
		/**
		 * Defines the behavior of this button.
		 * 
		 * @param e action
		 */
		public void actionPerformed(ActionEvent e) {
			if (logFileInput.getText().equals("")) {
				@SuppressWarnings("unused")
				AlertBoxUI a = new AlertBoxUI("Error", "File input cannot be empty!");
				return;
			}
			try {
				stm = new SecurityTreeManager(logFileInput.getText());
			} catch (IllegalArgumentException iae) {
				@SuppressWarnings("unused")
				AlertBoxUI a = new AlertBoxUI("Error", "Cannot load this file");
				return;
			}
			btnLoad.setEnabled(false);
			btnSearchLog.setEnabled(true);
			fileLoaded = true;
			traversal.setText(stm.getLogTreeLevelOrder());
		}
	}
	
	/**
	 * Inner class that defines the behavior of search button.
	 * 
	 * @author John Choi
	 * @version 07022018
	 */
	private class SearchButtonGUI extends AbstractAction {
		/**
		 * Generated serial.
		 */
		private static final long serialVersionUID = -4070482709254020424L;
		
		/**
		 * Constructs search button.
		 */
		public SearchButtonGUI() {
			putValue(NAME, "Search Log");
			putValue(SHORT_DESCRIPTION, "searches log by the date");
		}
		
		/**
		 * Defines the behavior of this search button.
		 * 
		 * @param e action
		 */
		public void actionPerformed(ActionEvent e) {
			if (!fileLoaded) {
				@SuppressWarnings("unused")
				AlertBoxUI a = new AlertBoxUI("Error", "File is not loaded");
				return;
			}
			if (dateSearchTextField.getText().equals("")) {
				@SuppressWarnings("unused")
				AlertBoxUI a = new AlertBoxUI("Error", "Please enter the date.");
				return;
			}
			try {
				String result = stm.getLogEntriesForDate(dateSearchTextField.getText());
				if (result.equals("")) {
					searchResult.setText("Not Found!");
				} else {
					searchResult.setText(result);
				}
			} catch (Exception ex) {
				@SuppressWarnings("unused")
				AlertBoxUI a = new AlertBoxUI("Error", "The date must be in MM-DD-YYYY format");
				return;
			}
		}
	}
}
