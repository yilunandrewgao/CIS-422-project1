/**
 * This program launches a GUI that allows the user to access the features 
 * of our address book easily. 
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DisplayGUI implements ActionListener {
	
	private JFrame frame;
	private JPanel tablePanel;
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenuItem newButton, openButton, saveButton, saveAsButton;
	private JButton newContact;
	private JTable addressBookDisplay;
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

		// Panel for the address book display
		this.tablePanel = new JPanel(new BorderLayout());
		tablePanel.setPreferredSize(new Dimension(800, 400));
		tablePanel.setLayout(new FlowLayout());

		// Adding multi-column list to display address book
		String[] columnNames = {"First", "Last", "Phone"};
		// Just sample data created
		Object[][] displayData = getAddressBookDisplay();
		addressBookDisplay = new JTable(displayData, columnNames);
		JScrollPane scrollPane = new JScrollPane(addressBookDisplay);
		addressBookDisplay.setPreferredScrollableViewportSize(new Dimension(800, 400));
		scrollPane.setViewportView(addressBookDisplay);
		tablePanel.add(addressBookDisplay);

		// Panel for the add contact button
		JPanel contactButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
		// Adding a button to add a contact
		this.newContact = new JButton("Add New Contact");
		newContact.addActionListener(this);
		newContact.setMnemonic(KeyEvent.VK_D);
		contactButtonPanel.add(newContact);

		// Main panel creation
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		mainPanel.add(tablePanel);
		mainPanel.add(contactButtonPanel);
		
		// Makes window visible
		this.frame = new JFrame("Address Book");
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setJMenuBar(menuBar);
		frame.add(mainPanel);
		frame.pack();
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == newContact) {
			JTextField firstName = new JTextField(10);
			JTextField lastName = new JTextField(10);
			JTextField phone = new JTextField(10);
			JTextField address1 = new JTextField(20);
			JTextField address2 = new JTextField(20);
			JFrame newContactFrame = new JFrame("Add New Contact");
			JPanel contactFields = new JPanel();
			contactFields.setLayout(new BoxLayout(contactFields, BoxLayout.Y_AXIS));
			contactFields.add(new JLabel("First Name:"));
			contactFields.add(firstName);
			contactFields.add(new JLabel("Last Name:"));
			contactFields.add(lastName);
			contactFields.add(Box.createHorizontalStrut(15));
			contactFields.add(new JLabel("Phone:"));
			contactFields.add(phone);
			contactFields.add(new JLabel("Address 1:"));
			contactFields.add(address1);
			contactFields.add(new JLabel("Address 2:"));
			contactFields.add(address2);
			newContactFrame.add(contactFields);
			newContactFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			newContactFrame.pack();
			newContactFrame.setVisible(true);
		} else if (e.getSource() == openButton) {

		} else if (e.getSource() == newButton) {

		} else if (e.getSource() == saveAsButton) {

		}
	}


	private Object[][] getAddressBookDisplay() {
		Object[][] sampleData = {{"Meg", "Fredericks", "5412923031"}, {"Brooke", "Fredericks", "5412920283"}};
		return sampleData;
	}

	public static void main(String[] args) {
		new DisplayGUI();
	}
}
