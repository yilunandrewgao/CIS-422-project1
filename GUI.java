/**
 * This program launches a GUI that allows the user to access the features 
 * of our address book easily. 
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI {
	
	private JFrame frame;
	private JLabel headerLabel;
	private JMenuBar menuBar;
	private JMenu menu, submenu;
	private JMenuItem menuItem;

	public GUI() {
		createPanel();
	}

	/**
	 * Creates a JFrame that will host the buttons for the address book
	 * @return	the panel created by this method
	 */
	private void createPanel() {
		// Creating the window for the GUI
		this.frame = new JFrame("MainWindow");
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setSize(400, 400);
		
		// Add component
		JLabel blank = new JLabel();
		blank.setOpaque(true);
		blank.setPreferredSize(new Dimension(400, 400));

		// Creating the menu bar
		/*menuBar = new JMenuBar();

		// Build "File" menu
		menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_A);
		*/
		// Create "File" drop-down menu
		/**
		String[] fileOptions = {"File", "New", "Open", "Save", "Save As"};
		final DefaultComboBoxModel defaultOptions = new DefaultComboBoxModel(fileOptions);
		final JComboBox fileMenu = new JComboBox(defaultOptions);
		fileMenu.setSelectedIndex(0);
		
		JScrollPane fileMenuScrollPane = new JScrollPane(defaultOptions);

		JButton showButton = new JButton("Show");
	
		ActionListener e = new ActionListener();
		showButton.addActionListener(e);
		frame.add(fileMenuScrollPane);
		frame.add(showButton);*/
		
		// Makes the window visible
		frame.pack();
		frame.setVisible(true);
	}	
	
	public static void main(String[] args) {
		new GUI();
	}

}
