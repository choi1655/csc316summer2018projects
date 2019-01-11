package edu.ncsu.csc316.transportation_manager.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;

/**
 * GUI for custom alert box.
 * 
 * @author John Choi
 * @version 07112018
 */
public class AlertBoxUI extends JFrame {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -7232511468321799637L;
	private JPanel contentPane;
	private final Action action = new SwingActionOKUI();

	/**
	 * Create the frame.
	 * 
	 * @param title of the alertbox
	 * @param message of the alertbox
	 */
	public AlertBoxUI(String title, String message) {
		showAlert(title, message);
	}
	
	/**
	 * Displays the alert message with the title and message passed in.
	 * 
	 * @param title to display
	 * @param message to display
	 */
	public void showAlert(String title, String message) {
		setTitle(title);
		setType(Type.NORMAL);
		setAlwaysOnTop(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 300, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel(message);
		contentPane.add(lblNewLabel, BorderLayout.CENTER);
		
		JButton btnOk = new JButton("OK");
		btnOk.setAction(action);
		contentPane.add(btnOk, BorderLayout.SOUTH);
		super.setLocationRelativeTo(null); //sets the position of the window to center by default
		super.setVisible(true);
	}

	/**
	 * Private class that defines the OK button's behavior.
	 * 
	 * @author John Choi
	 * @version 07112018
	 */
	private class SwingActionOKUI extends AbstractAction {
		/**
		 * Generated serial ID.
		 */
		private static final long serialVersionUID = -2473912243448972607L;
		
		/**
		 * Constructs the OK button.
		 */
		public SwingActionOKUI() {
			putValue(NAME, "OK");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		
		/**
		 * Tells the button to close the alert box.
		 * 
		 * @param e action received
		 */
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}
}
