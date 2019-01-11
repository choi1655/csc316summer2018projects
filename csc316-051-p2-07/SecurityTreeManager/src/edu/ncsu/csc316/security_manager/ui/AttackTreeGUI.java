package edu.ncsu.csc316.security_manager.ui;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import edu.ncsu.csc316.security_manager.manager.SecurityTreeManager;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.TextArea;
import javax.swing.JTextArea;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;

/**
 * Attack Tree Build View for Security Tree Manager.
 * 
 * @author John Choi
 * @version 07032018
 */
public class AttackTreeGUI extends JFrame {
	
	private SecurityTreeManager stm;
	
	/**
	 * Generated serial ID.
	 */
	private static final long serialVersionUID = 2035644161673544187L;
	private JTextField preOrderFileInput;
	private JTextField postOrderFileInput;
	private JLabel lblPreorderFile;
	private JLabel lblPostorderFile;
	private JButton btnLoadFiles;
	private JButton btnViewSummary;
	private JTextArea summaryTextArea;
	private TextArea traversal;
	private final Action action = new LoadButtonGUI();
	private final Action action1 = new ViewSummaryButtonGUI();

	/**
	 * Launch the application.
	 * 
	 * @param args command-line argument
	 */
//	public static void main(String[] args) {
//		try {
//			AttackTreeGUI frame = new AttackTreeGUI();
//			frame.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Constructs the GUI.
	 */
	public AttackTreeGUI() {
		setTitle("Security Tree Manager - Attack Tree");
		setResizable(false);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 1300, 500);
		
		lblPreorderFile = new JLabel("Pre-Order File: ");
		
		lblPostorderFile = new JLabel("Post-Order File: ");
		
		preOrderFileInput = new JTextField();
		preOrderFileInput.setColumns(10);
		
		postOrderFileInput = new JTextField();
		postOrderFileInput.setColumns(10);
		
		btnLoadFiles = new JButton("Load Files");
		btnLoadFiles.setAction(action);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblLevelorderTraversal = new JLabel("Level-Order Traversal");
		
		btnViewSummary = new JButton("View Summary");
		btnViewSummary.setAction(action1);
		btnViewSummary.setEnabled(false);
		
		summaryTextArea = new JTextArea();
		summaryTextArea.setEditable(false);
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 629, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(summaryTextArea, GroupLayout.PREFERRED_SIZE, 589, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnViewSummary, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
							.addGap(223))))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(27)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPreorderFile)
						.addComponent(lblPostorderFile))
					.addGap(22)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnLoadFiles, Alignment.TRAILING)
						.addComponent(preOrderFileInput, GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
						.addComponent(postOrderFileInput))
					.addContainerGap(867, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(255)
					.addComponent(lblLevelorderTraversal)
					.addContainerGap(909, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(21)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPreorderFile)
						.addComponent(preOrderFileInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPostorderFile)
						.addComponent(postOrderFileInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnLoadFiles)
					.addGap(20)
					.addComponent(lblLevelorderTraversal)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 273, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(summaryTextArea, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
							.addGap(45)
							.addComponent(btnViewSummary, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(19, Short.MAX_VALUE))
		);
		
		traversal = new TextArea();
		traversal.setEditable(false);
		scrollPane.setViewportView(traversal);
		getContentPane().setLayout(groupLayout);
		super.setLocationRelativeTo(null); // centers screen
		super.setVisible(true);
	}
	
	/**
	 * Defines the behavior of load files button.
	 * 
	 * @author John Choi
	 * @version 07022018
	 */
	private class LoadButtonGUI extends AbstractAction {
		/**
		 * Generated serial.
		 */
		private static final long serialVersionUID = -6818354852605733369L;
		
		/**
		 * Constructs this button.
		 */
		public LoadButtonGUI() {
			putValue(NAME, "Load Files");
			putValue(SHORT_DESCRIPTION, "loads both files");
		}
		
		/**
		 * Defines the behavior of this button
		 * 
		 * @param e action
		 */
		public void actionPerformed(ActionEvent e) {
			if (preOrderFileInput.getText().equals("") || postOrderFileInput.getText().equals("")) {
				@SuppressWarnings("unused")
				AlertBoxUI a = new AlertBoxUI("Error", "File names cannot be blank!");
				return;
			}
			try {
				stm = new SecurityTreeManager(preOrderFileInput.getText(), postOrderFileInput.getText());
			} catch (IllegalArgumentException iae) {
				@SuppressWarnings("unused")
				AlertBoxUI a = new AlertBoxUI("Error", "File(s) cannot be loaded!");
				return;
			}
			btnLoadFiles.setEnabled(false);
			btnViewSummary.setEnabled(true);
			traversal.setText(stm.getAttackTreeLevelOrder());
		}
	}
	
	/**
	 * Defines the behavior of View Summary button.
	 * 
	 * @author John Choi
	 * @version 07022018
	 */
	private class ViewSummaryButtonGUI extends AbstractAction {
		/**
		 * Generated serial.
		 */
		private static final long serialVersionUID = 5945261034021181593L;
		
		/**
		 * Constructs this button.
		 */
		public ViewSummaryButtonGUI() {
			putValue(NAME, "View Summary");
			putValue(SHORT_DESCRIPTION, "loads summary of GOAL node");
		}
		
		/**
		 * Defines the behavior of this button.
		 * 
		 * @param e action
		 */
		public void actionPerformed(ActionEvent e) {
			summaryTextArea.setText(stm.propagateValues());
		}
	}
}
