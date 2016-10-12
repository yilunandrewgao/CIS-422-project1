/**
 * Created by megfredericks on 10/11/16.
 */

package AddressBook;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 * The AddressBookWrapper class displays a specified open address book to the user
 * in a new window with the same title as the address book. The user can add new
 * contacts to the address book from this page.
 */
public class AddressBookWrapper implements ActionListener {

    private String fileName;
    private JFrame frame;
    private JTable addressBookDisplay;
    private JPanel tablePanel;
    private JButton newContact;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem saveOption, saveAsOption;
    private Controller controller;


    public AddressBookWrapper(String tsvFileName) throws Exception {
        this.controller = new Controller(tsvFileName);
        this.controller.loadAddressBook();
        this.fileName = tsvFileName;
        displayWindow();
    }

    private void displayWindow() {
        // Panel for the address book display
        this.tablePanel = new JPanel(new BorderLayout());
        tablePanel.setPreferredSize(new Dimension(800, 400));
        tablePanel.setLayout(new FlowLayout());

        // Build "File" menu with save and save as options
        menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_A);
		saveAsOption = new JMenuItem("Save As");
		saveAsOption.addActionListener(this);
		saveOption = new JMenuItem("Save");
		fileMenu.add(saveOption);
		fileMenu.add(saveAsOption);
		menuBar.add(fileMenu);

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

        // Make frame visible
        this.frame = new JFrame(this.fileName);
        this.frame.setJMenuBar(menuBar);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.add(mainPanel);
        this.frame.pack();
        this.frame.setVisible(true);
    }

    private Object[][] getAddressBookDisplay() {

        int line_num = 0;
        ArrayList<AddressEntry> book = this.controller.returnCurrentBook();
        System.out.println(book);
        Object[][] returnArray = new Object[book.size()][9];
        for(AddressEntry entry:book) {
            returnArray[line_num][0] = entry.getFirstName();
            returnArray[line_num][1] = entry.getLastName();
            returnArray[line_num][2] = entry.getDelivery();
            returnArray[line_num][3] = entry.getSecond();
            returnArray[line_num][4] = entry.getEmail();
            returnArray[line_num][5] = entry.getPhone();
            returnArray[line_num][6] = entry.getCity();
            returnArray[line_num][7] = entry.getState();
            returnArray[line_num][8] = entry.getZip();

            line_num += 1;
        }

        return returnArray;
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
        }

    }
}
