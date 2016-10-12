/**
 * This program launches a GUI that allows the user to access the features 
 * of our address book easily. 
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI {
	
	private JFrame frame;
	private JPanel tablePanel;
	private JLabel headerLabel;
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenuItem newButton, openButton, saveButton, saveAsButton;
	private JTable addressBookDisplay;

	public GUI() {
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
		saveAsButton = new JMenuItem("Save As");
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
		Object[][] sampleData = {{"Meg", "Fredericks", "5412923031"}, {"Brooke", "Fredericks", "5412920283"}};
		addressBookDisplay = new JTable(sampleData, columnNames);
		JScrollPane scrollPane = new JScrollPane(addressBookDisplay);
		addressBookDisplay.setPreferredScrollableViewportSize(new Dimension(800, 400));
		scrollPane.setViewportView(addressBookDisplay);
		tablePanel.add(addressBookDisplay);

		// Panel for the add contact button
		JPanel contactButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
		// Adding a button to add a contact
		JButton newContact = new JButton(new ButtonAction("Add New Contact", KeyEvent.VK_A));
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

	private class ButtonAction extends AbstractAction {
		public ButtonAction(String name, Integer mnemonic) {
			super(name);
			putValue(MNEMONIC_KEY, mnemonic);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("button pressed");
		}
	}
	
	public static void main(String[] args) {
		new GUI();
	}
}
