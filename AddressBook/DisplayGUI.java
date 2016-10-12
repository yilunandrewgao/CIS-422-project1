package AddressBook;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.io.File;
import java.net.URL;

/**
 * This program launches a simple GUI that allows the user to access
 * features of our address book. It displays our team logo ("Semi-Professional"),
 * as well as buttons to either create a new address book or open an existing one.
 */
public class DisplayGUI implements ActionListener {
	
	private JFrame frame;
	private JPanel mainPanel;
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenuItem saveButton, saveAsButton;
	private JLabel iconLabel;
	private JButton newButton, openButton;
	private JTable addressBookDisplay;
	//private ArrayList<AddressBookWrapper> booksOpen;
	//private Controller controller;

	/**
	 * Constructor that takes no parameters-- always displays the main menu, with options
	 * to open an existing address book or create a new one.
	 */
	public DisplayGUI() {
		//this.controller = c;
		createPanel();
	}

	/**
	 * Creates and shows the GUI. Initializes logo and buttons, and displays them.
	 */
	private void createPanel() {

		// Get team logo from "Logo" folder and resize it
		ImageIcon logo = new ImageIcon(this.getClass().getResource("/Logo/initial_logo.png"));
		Image image = logo.getImage();
		Image resizedLogo = image.getScaledInstance(400, 200, Image.SCALE_SMOOTH);
		logo = new ImageIcon(resizedLogo);

		// Create "new" and "open buttons
		newButton = new JButton("Create New Address Book");
		openButton = new JButton("Open Existing Address Book");
		openButton.addActionListener(this);
		newButton.addActionListener(this);

		// Make panel, change its layout, add components, and display frame.
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		iconLabel = new JLabel(logo);
		mainPanel.add(iconLabel);
		mainPanel.add(newButton);
		mainPanel.add(openButton);
		frame = new JFrame();
		frame.add(mainPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * This method keeps track of how all ActionEvents are handled on this GUI.
	 * @param e	the ActionEvent that is triggered by the user.
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == openButton) {


			File file = new File("AddressBook/example_tsv.tsv");
			String absolutePath = file.getAbsolutePath();
			System.out.println(absolutePath);
			try {
				AddressBookWrapper test = new AddressBookWrapper(absolutePath);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}


	public static void main(String[] args) {
		new DisplayGUI();
	}
}
