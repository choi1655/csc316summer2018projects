package edu.ncsu.csc316.transportation_manager.ui;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import edu.ncsu.csc316.transportation_manager.manager.TransportationManager;

import javax.swing.JRadioButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;

/**
 * Main GUI for the Transportation Manager software.
 * 
 * @author John Choi
 * @version 07232018
 */
public class TransportationManagerUI extends JFrame {
	
	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 8039790162202425675L;
	private JTextField textFieldFile;
	private JButton btnLoadButton;
	private JRadioButton rdbtnMinimumAsphalt;
	private JRadioButton rdbtnMinimumCost;
	private final Action action = new RadioButtonMinAsphaltUI();
	private final Action action1 = new RadioButtonMinCostUI();
	private final Action action2 = new LoadButtonUI();
	private TransportationManager tm;
	private JLabel lblType;

	/**
	 * Launch the application.
	 * 
	 * @param args command-line argument
	 */
	public static void main(String[] args) {
		try {
			TransportationManagerUI frame = new TransportationManagerUI();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Constructs the GUI.
	 */
	public TransportationManagerUI() {
		setTitle("Transportation Manager - Main Menu");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 250);
		
		JLabel lblHighwayInformationFile = new JLabel("Highway Information File: ");
		
		textFieldFile = new JTextField();
		textFieldFile.setColumns(10);
		
		btnLoadButton = new JButton("Load!");
		btnLoadButton.setAction(action2);
		
		rdbtnMinimumAsphalt = new JRadioButton("Minimum Asphalt");
		rdbtnMinimumAsphalt.setAction(action);
		
		rdbtnMinimumCost = new JRadioButton("Minimum Cost");
		rdbtnMinimumCost.setAction(action1);
		
		lblType = new JLabel("Type:");
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(24)
					.addComponent(lblHighwayInformationFile)
					.addGap(18)
					.addComponent(textFieldFile, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(43, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(120, Short.MAX_VALUE)
					.addComponent(btnLoadButton, GroupLayout.PREFERRED_SIZE, 266, GroupLayout.PREFERRED_SIZE)
					.addGap(114))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblType)
					.addGap(48)
					.addComponent(rdbtnMinimumAsphalt)
					.addGap(68)
					.addComponent(rdbtnMinimumCost)
					.addContainerGap(77, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(48)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblHighwayInformationFile)
						.addComponent(textFieldFile, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblType)
						.addComponent(rdbtnMinimumAsphalt)
						.addComponent(rdbtnMinimumCost))
					.addPreferredGap(ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
					.addComponent(btnLoadButton)
					.addGap(61))
		);
		getContentPane().setLayout(groupLayout);
		super.setLocationRelativeTo(null); //sets the position of the window to center by default
	}
	
	/**
	 * Defines the behavior for radio button minimum asphalt.
	 * 
	 * @author John Choi
	 * @version 07232018
	 */
	private class RadioButtonMinAsphaltUI extends AbstractAction {
		
		/**
		 * Generated serial.
		 */
		private static final long serialVersionUID = 8576061986118504262L;
		
		/**
		 * Constructs this button.
		 */
		public RadioButtonMinAsphaltUI() {
			putValue(NAME, "Minimum Asphalt");
			putValue(SHORT_DESCRIPTION, "Selects asphalt to be the weight");
		}
		
		/**
		 * Defines the behavior for this button.
		 * 
		 * @param e action
		 */
		public void actionPerformed(ActionEvent e) {
			rdbtnMinimumAsphalt.setSelected(true);
			rdbtnMinimumCost.setSelected(false);
		}
	}
	
	/**
	 * Defines the behavior for this radio button.
	 * 
	 * @author John Choi
	 * @version 07232018
	 */
	private class RadioButtonMinCostUI extends AbstractAction {
		
		/**
		 * Generated serial.
		 */
		private static final long serialVersionUID = -8896853907635042433L;
		
		/**
		 * Constructs this radio button.
		 */
		public RadioButtonMinCostUI() {
			putValue(NAME, "Minimum Cost");
			putValue(SHORT_DESCRIPTION, "Selects cost to be the weight");
		}
		
		/**
		 * Defines the behavior for this button.
		 * 
		 * @param e action
		 */
		public void actionPerformed(ActionEvent e) {
			rdbtnMinimumCost.setSelected(true);
			rdbtnMinimumAsphalt.setSelected(false);
		}
	}
	
	/**
	 * Defines the behavior for this load button.
	 * 
	 * @author John Choi
	 * @version 07232018
	 */
	private class LoadButtonUI extends AbstractAction {
		
		/**
		 * Generated serial.
		 */
		private static final long serialVersionUID = -8366797687777231571L;
		
		/**
		 * Constructs this button.
		 */
		public LoadButtonUI() {
			putValue(NAME, "Load!");
			putValue(SHORT_DESCRIPTION, "Initializes the program");
		}
		
		/**
		 * Defines the behavior for this button.
		 * 
		 * @param e action
		 */
		@SuppressWarnings("unused")
		public void actionPerformed(ActionEvent e) {
			if (textFieldFile.getText().equals("")) {
				AlertBoxUI a = new AlertBoxUI("Error", "You must specify the file name");
				return;
			}
			if (!rdbtnMinimumAsphalt.isSelected() && !rdbtnMinimumCost.isSelected()) {
				AlertBoxUI a = new AlertBoxUI("Error", "You must select the type of weight");
				return;
			}
			try {
				tm = new TransportationManager(textFieldFile.getText());
			} catch (IllegalArgumentException ex) {
				AlertBoxUI a = new AlertBoxUI("Error", "File cannot be opened");
				return;
			}
			if (rdbtnMinimumAsphalt.isSelected()) {
				ManagerUI start = new ManagerUI(tm, true);
			} else {
				ManagerUI start = new ManagerUI(tm, false);
			}
		}
	}
}
