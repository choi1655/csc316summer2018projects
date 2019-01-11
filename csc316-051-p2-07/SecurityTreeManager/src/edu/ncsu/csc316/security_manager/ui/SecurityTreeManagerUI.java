package edu.ncsu.csc316.security_manager.ui;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;

import javax.swing.Action;
import javax.swing.JLabel;

/**
 * Main GUI for the Security Tree Manager software.
 * 
 * @author John Choi
 * @version 07022018
 */
public class SecurityTreeManagerUI extends JFrame {
	
	private JButton btnBuildAttackTree;
	private JButton btnFilterLogs;
	
	/**
	 * Generated serial ID.
	 */
	private static final long serialVersionUID = 2035644161673544187L;
	private final Action action = new LoadAttackTreeGUI();
	private final Action action1 = new FilterLogsButtonGUI();

	/**
	 * Launch the application.
	 * 
	 * @param args command-line argument
	 */
	public static void main(String[] args) {
		try {
			SecurityTreeManagerUI frame = new SecurityTreeManagerUI();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Constructs the GUI.
	 */
	public SecurityTreeManagerUI() {
		setTitle("Security Tree Manager - Menu");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 250);
		
		btnBuildAttackTree = new JButton("Build An Attack Tree");
		btnBuildAttackTree.setAction(action);
		
		btnFilterLogs = new JButton("Filter Log Files By Date");
		btnFilterLogs.setAction(action1);
		
		JLabel lblMainMenu = new JLabel("Main Menu");
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(50)
							.addComponent(btnBuildAttackTree)
							.addGap(37)
							.addComponent(btnFilterLogs))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(186)
							.addComponent(lblMainMenu)))
					.addContainerGap(93, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(27)
					.addComponent(lblMainMenu)
					.addGap(46)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnBuildAttackTree, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnFilterLogs, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(89, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		super.setLocationRelativeTo(null); // centers screen
	}
	
	/**
	 * Defines behavior of the load attack tree button.
	 * 
	 * @author John Choi
	 * @version 07022018
	 */
	private class LoadAttackTreeGUI extends AbstractAction {
		/**
		 * Generated serial.
		 */
		private static final long serialVersionUID = 5369438959988771023L;
		
		/**
		 * Constructs this button.
		 */
		public LoadAttackTreeGUI() {
			putValue(NAME, "Build Attack Tree");
			putValue(SHORT_DESCRIPTION, "Build Attack Tree");
		}
		
		/**
		 * Defines this button's behavior.
		 * 
		 * @param e action
		 */
		public void actionPerformed(ActionEvent e) {
			@SuppressWarnings("unused")
			AttackTreeGUI atg = new AttackTreeGUI();
		}
	}
	
	/**
	 * Defines filter logs button's behavior.
	 * 
	 * @author John Choi
	 * @version 07022018
	 */
	private class FilterLogsButtonGUI extends AbstractAction {
		/**
		 * Generated serial.
		 */
		private static final long serialVersionUID = -2263981131262859816L;
		
		/**
		 * Constructs this button.
		 */
		public FilterLogsButtonGUI() {
			putValue(NAME, "Build Log Entry Tree");
			putValue(SHORT_DESCRIPTION, "builds log entry tree");
		}
		
		/**
		 * Defines this button's behavior.
		 * 
		 * @param e action
		 */
		public void actionPerformed(ActionEvent e) {
			@SuppressWarnings("unused")
			LogsGUI lg = new LogsGUI();
		}
	}
}
