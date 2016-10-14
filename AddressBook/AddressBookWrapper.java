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

    private int textFieldDimension = 10;

    private String fileName;
    private Object[][] displayData;
    private String[] columnNames;
    private JFrame frame;
    private JTable addressBookDisplay;
    private JPanel mainPanel, tablePanel, rightPanel, contactFields;
    private JButton newContactSave, newContactCancel;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem saveOption, saveAsOption, newContactOption;
    private JTextField firstName, lastName, phone, address1, address2, city, state, zip, email;
    private Controller controller;

    // reference to parent GUIController
    private DisplayGUI GUIController;

    public AddressBookWrapper(String tsvFileName, DisplayGUI _GUIController) throws Exception {
        GUIController = _GUIController;
        this.controller = new Controller(tsvFileName, GUIController);
        this.controller.loadAddressBook();
        this.fileName = tsvFileName;

        displayWindow();
    }

    // getter for fileName
    public String getFileName() {
        return fileName;
    }

    private void displayWindow() {
        // Panel for the address book display
        this.tablePanel = new JPanel();

        // Build "File" menu with save and save as options
        menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_A);
		saveAsOption = new JMenuItem("Save As");
		saveAsOption.addActionListener(this);
		saveOption = new JMenuItem("Save");
        saveOption.addActionListener(this);
        newContactOption = new JMenuItem("Add New Contact");
        newContactOption.addActionListener(this);
        fileMenu.add(newContactOption);
		fileMenu.add(saveOption);
		fileMenu.add(saveAsOption);
		menuBar.add(fileMenu);

        // Adding multi-column list to display address book
        columnNames = new String[]{"First", "Last"};
        //DefaultTableModel contactTableModel = (DefaultTableModel) jTable
               // .getModel();
        displayData = getAddressBookDisplay();
        addressBookDisplay = new JTable(displayData, columnNames);
        tablePanel.add(addressBookDisplay);

        // Main panel creation
        rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(200, 200));
        mainPanel = new JPanel();
        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
        mainPanel.add(tablePanel);
        mainPanel.add(rightPanel);
        mainPanel.setLayout(new GridLayout(1, 2));

        // Make frame visible
        this.frame = new JFrame(this.fileName);
        this.frame.setJMenuBar(menuBar);
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.frame.add(mainPanel);
        this.frame.pack();
        this.frame.setVisible(true);

        // add the closing listener
        closingListener cl = new closingListener(this);
        this.frame.addWindowListener(cl);
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
        // If you select a contact, info will display on side, and there are save and delete options
        // editable fields
        if (e.getSource() == newContactOption) {
            displayNewContact(); // put this on right side of window
        } else if (e.getSource() == saveOption) {
            // Controller.save, true or false if saved. display "saved successfully" method
            // If save failed, display "error while saving"

            try {
                controller.save();
            } catch (Exception ex) {
                System.out.println("Failed to save Address Book.");
            }

        } else if (e.getSource() == saveAsOption) {
            // open a file chooser dialog box

            String userFileName = null;

            JFileChooser c = new JFileChooser(System.getProperty("user.dir"));
            int rVal = c.showSaveDialog(fileMenu);
            if (rVal == JFileChooser.APPROVE_OPTION) {
                userFileName = c.getCurrentDirectory().toString() + "/" + c.getSelectedFile().getName();
            }

            // if user chose a file, call the save as method
            if (userFileName != null) {
                try {
                    controller.saveAs(userFileName);
                } catch (Exception ex) {
                    System.out.println("Failed to save Address Book as " + userFileName);
                }
            }

        }
        // If the user wants to save a new contact, send info from text fields to controller
        else if (e.getSource() == newContactSave) {
            String[] newContactInfo = new String[9];
            newContactInfo[0] = firstName.getText();
            newContactInfo[1] = lastName.getText();
            newContactInfo[2] = address1.getText();
            newContactInfo[3] = address2.getText();
            newContactInfo[4] = email.getText();
            newContactInfo[5] = phone.getText();
            newContactInfo[6] = city.getText();
            newContactInfo[7] = state.getText();
            newContactInfo[8] = zip.getText();
            try {
                controller.tryToAddEntry(newContactInfo);
            } catch (TooLittleInputException ex1) {
                System.out.println(ex1.getMessage());
            } catch (InvalidInputException ex2) {
                System.out.println(ex2.getMessage());
                System.out.println(ex2.getInvalidFieldNum());
            }
            addressBookDisplay = new JTable(getAddressBookDisplay(), columnNames);

            // Removing "add new contact" screen because contact has been added.
            rightPanel.remove(contactFields);
            this.frame.pack();
            this.frame.setVisible(true);
        } else if (e.getSource() == newContactCancel) {

        }
    }


    private void displayNewContact() {
        firstName = new JTextField(textFieldDimension);
        lastName = new JTextField(textFieldDimension);
        phone = new JTextField(textFieldDimension);
        address1 = new JTextField(textFieldDimension);
        address2 = new JTextField(textFieldDimension);
        city = new JTextField(textFieldDimension);
        state = new JTextField(textFieldDimension);
        zip = new JTextField(textFieldDimension);
        email = new JTextField(textFieldDimension);
        contactFields = new JPanel();
        contactFields.setLayout(new BoxLayout(contactFields, BoxLayout.Y_AXIS));
        contactFields.add(new JLabel("First Name:"));
        contactFields.add(firstName);
        contactFields.add(new JLabel("Last Name:"));
        contactFields.add(lastName);
        contactFields.add(new JLabel("Phone:"));
        contactFields.add(phone);
        contactFields.add(new JLabel("Address 1:"));
        contactFields.add(address1);
        contactFields.add(new JLabel("Address 2:"));
        contactFields.add(address2);
        contactFields.add(new JLabel("City:"));
        contactFields.add(city);
        contactFields.add(new JLabel("State:"));
        contactFields.add(state);
        contactFields.add(new JLabel("ZIP:"));
        contactFields.add(zip);
        contactFields.add(new JLabel("Email:"));
        contactFields.add(email);

        // Adding save and cancel buttons
        newContactSave = new JButton("Save Contact");
        newContactSave.addActionListener(this);
        newContactCancel = new JButton("Cancel");
        newContactCancel.addActionListener(this);
        contactFields.add(newContactSave);
        contactFields.add(newContactCancel);

        rightPanel.add(contactFields);
        this.frame.pack();
        this.frame.setVisible(true);
    }


    // the window listener that handles closing
    // need to pass in reference to this AddressBookWrapper so it can be removed from the Arraylist of wrappers.
    class closingListener extends WindowAdapter {

        private AddressBookWrapper thisAddressBookWrapper;

        public closingListener(AddressBookWrapper _thisAddressBookWrapper) {
            super();
            thisAddressBookWrapper = _thisAddressBookWrapper;
        }

        public void windowClosing(WindowEvent we) {
            GUIController.getOpenBooks().remove(thisAddressBookWrapper);
        }
    }
}
