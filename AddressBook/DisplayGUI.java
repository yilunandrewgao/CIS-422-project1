package AddressBook;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.util.ArrayList;
import java.io.File;
import java.io.*;

/**
 * This program launches a simple GUI that allows the user to access
 * features of our address book. It displays our team logo ("Semi-Professional"),
 * as well as buttons to either create a new address book or open an existing one.
 */
public class DisplayGUI implements ActionListener {
	
	private JFrame frame;
	private JPanel mainPanel, buttonPanel;
	private JLabel iconLabel;
	private JButton newButton, openButton;
	private ArrayList<AddressBookWrapper> booksOpen;

	/**
	 * Constructor that takes no parameters-- always displays the main menu, with options
	 * to open an existing address book or create a new one.
	 */
	public DisplayGUI() {
		createPanel();
	}

	// returns the array of open books
    public ArrayList<AddressBookWrapper> getOpenBooks() {
        return booksOpen;
    }

	/**
	 * Creates and shows the GUI. Initializes logo and buttons, and displays them.
	 */
	private void createPanel() {

		// Get team logo from "Logo" folder and resize it
		ImageIcon logo = new ImageIcon(this.getClass().getResource("/Logo/initial_logo.png"));
		Image image = logo.getImage();
		Image resizedLogo = image.getScaledInstance(500, 120, Image.SCALE_SMOOTH);
		logo = new ImageIcon(resizedLogo);

		// Create "new" and "open" buttons
		newButton = new JButton("Create New Address Book");
		openButton = new JButton("Open Existing Address Book");
		openButton.addActionListener(this);
		newButton.addActionListener(this);

		// Make panel, change its layout, add components, and display frame.
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		iconLabel = new JLabel(logo);
		iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainPanel.add(iconLabel);

		buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.add(newButton);
		buttonPanel.add(openButton);
		mainPanel.add(buttonPanel);
		frame = new JFrame();
		frame.add(mainPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

        // initialize array of open books
        booksOpen = new ArrayList<AddressBookWrapper>();
	}

	/**
	 * This method keeps track of how all ActionEvents are handled on this GUI.
	 *
	 * @param e	the ActionEvent that is triggered by the user.
	 */
	public void actionPerformed(ActionEvent e) {
		// open menu, list all existing address books and let user choose
		if (e.getSource() == openButton) {

            // open a file chooser component that shows the working directory
			String absolutePath = "";
			JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
			FileNameExtensionFilter filter = new FileNameExtensionFilter("TSV Files", "tsv");
			chooser.setFileFilter(filter);
			int returnVal = chooser.showOpenDialog(mainPanel);
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				absolutePath = chooser.getSelectedFile().getAbsolutePath();

			}

			try {
				boolean alreadyOpen = false;
				for (AddressBookWrapper current: booksOpen) {
					if (current.getFileName().equals(chooser.getSelectedFile().getAbsolutePath())) {
						alreadyOpen = true;
					}
				}
				if (alreadyOpen) {
					JOptionPane.showMessageDialog(frame, "This address book is already open.");
				} else {
					AddressBookWrapper newBook = new AddressBookWrapper(absolutePath, this);
					booksOpen.add(newBook);
				}

			} catch (Exception e1) {
				System.out.println("Invalid File Chosen");
                JOptionPane.showMessageDialog(frame, "Invalid File Chosen", "Error",JOptionPane.ERROR_MESSAGE);
			}
		} else if (e.getSource() == newButton) {
			try {
				File tempFile = File.createTempFile("New File", ".tsv");
				String label = "FirstName\tLastName\tDelivery\tSecond\tEmail\tPhone\tCity\tState\tZIP";
				FileWriter fw = new FileWriter(tempFile.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(label);
				AddressBookWrapper createNewBook = new AddressBookWrapper(tempFile.getAbsolutePath(), this);
				booksOpen.add(createNewBook);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}


	/**
	 * Creates a new DisplayGUI
	 * @param args
	 */
	public static void main(String[] args) {
		new DisplayGUI();
	}
}
