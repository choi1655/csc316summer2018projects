package edu.ncsu.csc316.transportation_manager.ui;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.TextArea;
import javax.swing.LayoutStyle.ComponentPlacement;

import edu.ncsu.csc316.transportation_manager.manager.TransportationManager;

import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;

/**
 * Main screen for Transportation Manager software.
 * 
 * @author John Choi
 * @version 07232018
 */
public class ManagerUI extends JFrame {
	
	private JRadioButton rdbtnAdjacencyList;
	private JRadioButton rdbtnMinimumTraversal;
	private TextArea textArea;
	private TransportationManager tm;
	private boolean typeIsAsphalt;
	private String adjacencyList;
	private String traversalList;
	
	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -2232319754584622891L;
	private final Action action = new AdjacencyListButtonUI();
	private final Action action1 = new MinimumTraversalUI();
	private final Action action2 = new QuitButtonUI();
	private JButton btnBackToMain;
	private final Action action3 = new BackToMainButtonUI();

	/**
	 * Constructs the GUI.
	 * 
	 * @param tm main manager of this software
	 * @param type true if weight is asphalt, false if weight is cost
	 */
	public ManagerUI(TransportationManager tm, boolean type) {
		this.tm = tm;
		typeIsAsphalt = type;
		if (type) {
			setTitle("Transportation Manager - Minimum Asphalt");
		} else {
			setTitle("Transportation Manager - Minimum Cost");
		}
		setResizable(false);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 700, 400);
		
		JButton btnQuitProgram = new JButton("Quit Program");
		btnQuitProgram.setAction(action2);
		btnQuitProgram.setToolTipText("Pressing this button will quit the program completely");
		
		JScrollPane scrollPane = new JScrollPane();
		
		rdbtnAdjacencyList = new JRadioButton("Adjacency List");
		rdbtnAdjacencyList.setAction(action);
		rdbtnAdjacencyList.setSelected(true);
		
		rdbtnMinimumTraversal = new JRadioButton("Minimum Traversal");
		rdbtnMinimumTraversal.setAction(action1);
		
		btnBackToMain = new JButton("Back To Main Menu");
		btnBackToMain.setAction(action3);
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(408, Short.MAX_VALUE)
					.addComponent(btnBackToMain)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnQuitProgram)
					.addGap(36))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(45)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 609, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(46, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(136)
					.addComponent(rdbtnAdjacencyList)
					.addGap(143)
					.addComponent(rdbtnMinimumTraversal)
					.addContainerGap(119, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdbtnAdjacencyList)
						.addComponent(rdbtnMinimumTraversal))
					.addGap(43)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnQuitProgram)
						.addComponent(btnBackToMain))
					.addGap(24))
		);
		
		textArea = new TextArea();
		adjacencyList = tm.getAdjacencyList();
		textArea.setText(adjacencyList);
		scrollPane.setViewportView(textArea);
		getContentPane().setLayout(groupLayout);
		super.setLocationRelativeTo(null); //sets the position of the window to center by default
		this.setVisible(true);
		if (typeIsAsphalt) {
			traversalList = tm.getMinimumHighways("ASPHALT");
		} else {
			traversalList = tm.getMinimumHighways("COST");
		}
	}
	
	/**
	 * Getter for the transportation manager.
	 * 
	 * @return tm - the manager
	 */
	public TransportationManager getManager() {
		return tm;
	}
	
	/**
	 * Defines the behavior of this radio button.
	 * 
	 * @author John Choi
	 * @version 07232018
	 */
	private class AdjacencyListButtonUI extends AbstractAction {
		
		/**
		 * Generated serial.
		 */
		private static final long serialVersionUID = -2200175628210482427L;
		
		/**
		 * Constructs this radio button.
		 */
		public AdjacencyListButtonUI() {
			putValue(NAME, "Adjacency List");
			putValue(SHORT_DESCRIPTION, "Outputs adjacency list");
		}
		
		/**
		 * Defines the behavior of this button.
		 * 
		 * @param e action
		 */
		public void actionPerformed(ActionEvent e) {
			rdbtnAdjacencyList.setSelected(true);
			rdbtnMinimumTraversal.setSelected(false);
			textArea.setText(adjacencyList);
		}
	}
	
	/**
	 * Defines the behavior of minimum traversal radio button.
	 * 
	 * @author John Choi
	 * @version 07232018
	 */
	private class MinimumTraversalUI extends AbstractAction {
		
		/**
		 * Generated serial.
		 */
		private static final long serialVersionUID = 88410815322359655L;
		
		/**
		 * Constructs this radio button.
		 */
		public MinimumTraversalUI() {
			putValue(NAME, "Minimum Traversal List");
			if (typeIsAsphalt) {
				putValue(SHORT_DESCRIPTION, "Outputs minimum traversal list of asphalt");
			} else {
				putValue(SHORT_DESCRIPTION, "Outputs minimum traversal list of cost");
			}
		}
		
		/**
		 * Defines the behavior of this radio button.
		 * 
		 * @param e action
		 */
		public void actionPerformed(ActionEvent e) {
			rdbtnAdjacencyList.setSelected(false);
			rdbtnMinimumTraversal.setSelected(true);
			textArea.setText(traversalList);
		}
	}
	
	/**
	 * Defines the behavior of this quit button.
	 * 
	 * @author John Choi
	 * @version 07232018
	 */
	private class QuitButtonUI extends AbstractAction {
		
		/**
		 * Generated serial.
		 */
		private static final long serialVersionUID = -5688112426537703066L;
		
		/**
		 * Constructs this quit button.
		 */
		public QuitButtonUI() {
			putValue(NAME, "Quit Program");
			putValue(SHORT_DESCRIPTION, "Pressing this button will terminate the program completely");
		}
		
		/**
		 * Defines the behavior of this button.
		 * 
		 * @param e action
		 */
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
	
	/**
	 * Defines the behavior of this back to main button.
	 * 
	 * @author John Choi
	 * @version 07232018
	 */
	private class BackToMainButtonUI extends AbstractAction {
		
		/**
		 * Generated serial.
		 */
		private static final long serialVersionUID = -3625128927725075439L;
		
		/**
		 * Constructs this back to main button.
		 */
		public BackToMainButtonUI() {
			putValue(NAME, "Go Back to Main Menu");
			putValue(SHORT_DESCRIPTION, "Pressing this button will close the current screen and go back to main menu");
		}
		
		/**
		 * Defines the behavior of this button.
		 * 
		 * @param e action
		 */
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}
}
