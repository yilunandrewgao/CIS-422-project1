package AddressBook; /**
 * This program launches a GUI that allows the user to access the features 
 * of our address book easily. 
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class DisplayGUI implements ActionListener {
	
	private JFrame frame;
	private JPanel tablePanel;
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenuItem newButton, openButton, saveButton, saveAsButton;
	private JButton newContact;
	private JTable addressBookDisplay;
	private ArrayList<AddressBookWrapper> booksOpen;
	//private Controller controller;

	public DisplayGUI() {
		//this.controller = c;
		createPanel();
	}

	/**
	 * Creates a JFrame that will host the buttons for the address book.
	 */
	private void createPanel() {

		// Creating the menu bar
		menuBar = new JMenuBar();

		// Build "File" menu with open, save, save as, and new options
		fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_A);
		openButton = new JMenuItem("Open");
		openButton.addActionListener(this);
		saveAsButton = new JMenuItem("Save As");
		saveAsButton.addActionListener(this);
		newButton = new JMenuItem("New");
		saveButton = new JMenuItem("Save");
		fileMenu.add(newButton);
		fileMenu.add(openButton);
		fileMenu.add(saveButton);
		fileMenu.add(saveAsButton);
		menuBar.add(fileMenu);


		frame.add(mainPanel);
		frame.pack();
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {

	}


	public static void main(String[] args) {
		new DisplayGUI();
	}
}
