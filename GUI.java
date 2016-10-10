/**
 * This program launches a GUI that allows the user to access the features 
 * of our address book easily. 
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI {
	
	private JFrame frame;
	private JPanel panel;
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
		// Creating the window for the GUI
		this.frame = new JFrame("Address Book");
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setSize(400, 400);

		this.panel = new JPanel();
		panel.setPreferredSize(new Dimension(400,400));
		frame.add(panel);

		// Add component
		JLabel blank = new JLabel();
		blank.setOpaque(true);
		blank.setPreferredSize(new Dimension(400, 400));

		// Creating the menu bar
		menuBar = new JMenuBar();

		// Build "File" menu
		fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_A);
		newButton = new JMenuItem("New");
		fileMenu.add(newButton);
		openButton = new JMenuItem("Open");
		fileMenu.add(openButton);
		saveButton = new JMenuItem("Save");
		fileMenu.add(saveButton);
		saveAsButton = new JMenuItem("Save As");
		fileMenu.add(saveAsButton);
		fileMenu.add(newButton);
		menuBar.add(fileMenu);
		frame.setJMenuBar(menuBar);

		// Adding multi-column list to display address book
		String[] columnNames = {"First", "Last", "Phone"};
		// Just sample data created
		Object[][] sampleData = {{"Meg", "Fredericks", "5412923031"}, {"Brooke", "Fredericks", "5412920283"}};
		addressBookDisplay = new JTable(sampleData, columnNames);
		JScrollPane scrollPane = new JScrollPane();
		addressBookDisplay.setFillsViewportHeight(true);
		frame.add(addressBookDisplay);
		
		// Makes the window visible
		frame.pack();
		frame.setVisible(true);
	}	
	
	public static void main(String[] args) {
		new GUI();
	}

}
