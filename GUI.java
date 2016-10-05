/**
 * This program launches a GUI that allows the user to access the features 
 * of our address book easily. 
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI {
	
	private JFrame frame;

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
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}	

}
