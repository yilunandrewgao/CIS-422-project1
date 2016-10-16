/**
 * Created by megfredericks on 10/11/16.
 */

package AddressBook;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.*;

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
    private JPanel mainPanel, tablePanel, contactFieldsDisplayPanel, contactFields, buttonPanel;
    private JButton ContactSave, ContactCancel, ContactDelete, NewContact;
    private JLabel searchBarLabel;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem saveOption, saveAsOption, deleteOption;
    private JTextField firstName, lastName, phone, address1, address2, city, state, zip, email, searchBar;
    private JScrollPane scrollPane;
    private GridBagConstraints c;
    private Controller controller;

    private ArrayList<AddressEntry> lastContactList;
    private AddressEntry lastClickedContact;

    // keeps track of which entry is selected in the table, null if new entry is being added
    private AddressEntry currentSelectedEntry;

    // reference to parent GUIController
    private DisplayGUI GUIController;

    public AddressBookWrapper(String tsvFileName, DisplayGUI _GUIController) throws Exception {
        GUIController = _GUIController;
        this.controller = new Controller(tsvFileName, GUIController);
        this.controller.loadAddressBook();
        this.fileName = tsvFileName;

        this.lastContactList = new ArrayList<AddressEntry>();
        this.currentSelectedEntry = null; //FIXME what should it default to? Check if = null when accessing?
        GUIController = _GUIController;

        displayWindow();
        initializeContactFieldsComponents();

        //code for mouse listener
        //contacts are indexed starting at 1, since the column headers are in row 0
        this.addressBookDisplay.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int indexClicked = addressBookDisplay.rowAtPoint(e.getPoint());
                if (indexClicked >= 0){
                    // if the index is 0 then it's the header row, if it's -1 then click was in the frame but not a table row
                    currentSelectedEntry = lastContactList.get(indexClicked);
                    //display the detailed contact info on side
                    displayContact(currentSelectedEntry);
                }
                else return; //The click wasn't on the table so we are done now

                //TODO remove, this is just for testing
                System.out.println(currentSelectedEntry.toString());

            }
        });
    }

    // getter for fileName
    public String getFileName() {
        return fileName;
    }

    private void displayWindow() {
        // Panel for the address book display
        this.tablePanel = new JPanel();
        this.scrollPane = new JScrollPane();


        // Build "File" menu with save and save as options
        menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_A);
		saveAsOption = new JMenuItem("Save As");
		saveAsOption.addActionListener(this);
		saveOption = new JMenuItem("Save");
        saveOption.addActionListener(this);
        deleteOption = new JMenuItem("Delete Address Book");
        deleteOption.addActionListener(this);
		fileMenu.add(saveOption);
		fileMenu.add(saveAsOption);
        fileMenu.add(deleteOption);
		menuBar.add(fileMenu);

        // Adding multi-column list to display address book
        columnNames = new String[]{"First", "Last", "Address 1", "Address 2", "Email", "Phone", "City", "State", "ZIP"};
        displayData = getAddressBookDisplay();
        //FIXME test to see if column names work now
        DefaultTableModel initialModel = new DefaultTableModel(displayData, columnNames);
        addressBookDisplay = new JTable(initialModel);
        tablePanel.add(scrollPane);
        scrollPane.setViewportView(addressBookDisplay);

        // Create button panel for search bar and new contact button
        buttonPanel = new JPanel(new FlowLayout());
        NewContact = new JButton("Add New Contact");
        NewContact.addActionListener(this);
        searchBarLabel = new JLabel("Search: ");
        searchBar = new JTextField(textFieldDimension);
        buttonPanel.add(NewContact);
        buttonPanel.add(searchBarLabel);
        buttonPanel.add(searchBar);

        mainPanel = new JPanel(new GridBagLayout());
        c = new GridBagConstraints();

        // Set Button Panel constraints, and add to panel
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.0;
        //c.gridwidth = 4;
        c.gridx = 0;
        c.gridy = 0;
        mainPanel.add(buttonPanel, c);

        // Set Address Table constraints, and add to panel
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 4;
        c.gridheight = 4;
        c.ipady = 40;
        c.gridx = 0;
        c.gridy = 1;
        //mainPanel.add(addressBookDisplay, c);
        //mainPanel.add(scrollPane, c);
        mainPanel.add(tablePanel, c);

        // Set contact field display constraints and add to panel
        contactFieldsDisplayPanel = new JPanel();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 4;
        c.gridheight = 4;
        c.gridx = 0;
        c.gridy = 10;
        mainPanel.add(contactFieldsDisplayPanel, c);

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

    // helper method to initialize all widgets in contactFields panel
    private void initializeContactFieldsComponents() {
        firstName = new JTextField(textFieldDimension);
        lastName = new JTextField(textFieldDimension);
        phone = new JTextField(textFieldDimension);
        address1 = new JTextField(textFieldDimension);
        address2 = new JTextField(textFieldDimension);
        city = new JTextField(textFieldDimension);
        state = new JTextField(textFieldDimension);
        zip = new JTextField(textFieldDimension);
        email = new JTextField(textFieldDimension);
        contactFields = new JPanel(new GridBagLayout());
        GridBagConstraints c2 = new GridBagConstraints();
        //c.fill = GridBagConstraints.HORIZONTAL;

        c2.gridx = 0;
        c2.gridy = 0;
        contactFields.add(new JLabel("First Name:"), c2);
        //c.fill = GridBagConstraints.HORIZONTAL;
        c2.gridx = 1;
        c2.gridy = 0;
        contactFields.add(firstName, c2);

        //c.fill = GridBagConstraints.HORIZONTAL;
        c2.gridx = 2;
        c2.gridy = 0;
        contactFields.add(new JLabel("Last Name:"), c2);
        c2.gridx = 3;
        c2.gridy = 0;
        contactFields.add(lastName, c2);

        c2.gridx = 0;
        c2.gridy = 1;
        contactFields.add(new JLabel("Phone:"), c2);
        c2.gridx = 1;
        c2.gridy = 1;
        contactFields.add(phone, c2);

        c2.gridx = 2;
        c2.gridy = 1;
        contactFields.add(new JLabel("Address 1:"), c2);
        c2.gridx = 3;
        c2.gridy = 1;
        contactFields.add(address1, c2);
        c2.gridx = 0;
        c2.gridy = 3;
        contactFields.add(new JLabel("Address 2:"), c2);
        c2.gridx = 1;
        c2.gridy = 3;
        contactFields.add(address2, c2);
        c2.gridx = 2;
        c2.gridy = 2;
        contactFields.add(new JLabel("City:"), c2);
        c2.gridx = 3;
        c2.gridy = 2;
        contactFields.add(city, c2);
        c2.gridx = 0;
        c2.gridy = 2;
        contactFields.add(new JLabel("State:"), c2);
        c2.gridx = 1;
        c2.gridy = 2;
        contactFields.add(state, c2);
        c2.gridx = 2;
        c2.gridy = 3;
        contactFields.add(new JLabel("ZIP:"), c2);
        c2.gridx = 3;
        c2.gridy = 3;
        contactFields.add(zip, c2);
        c2.gridx = 0;
        c2.gridy = 4;
        contactFields.add(new JLabel("Email:"), c2);
        c2.gridx = 1;
        c2.gridy = 4;
        contactFields.add(email, c2);

        // Adding save and cancel buttons
        ContactSave = new JButton("Save Contact");
        ContactSave.addActionListener(this);
        ContactCancel = new JButton("Cancel");
        ContactCancel.addActionListener(this);
        ContactDelete = new JButton("Delete");
        ContactDelete.addActionListener(this);
        c2.gridx = 4;
        c2.gridy = 0;
        contactFields.add(ContactSave, c2);
        c2.gridx = 4;
        c2.gridy = 1;
        contactFields.add(ContactCancel, c2);
        c2.gridx = 4;
        c2.gridy = 2;
        contactFields.add(ContactDelete, c2);

        contactFieldsDisplayPanel.add(contactFields);
        this.frame.pack();

    }

    private Object[][] getAddressBookDisplay() {

        int line_num = 0;
        ArrayList<AddressEntry> book = this.controller.returnCurrentBook();
        lastContactList = book;
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

    // helper function to encapsulate add or edit entry behavior. Used in actionPerformed method
    private void addOrEditEntry(String[] newContactInfo) {
        // if there is no current selected entry, add the entry
        if (currentSelectedEntry == null) {
            controller.addEntry(newContactInfo);
        }
        else {
            controller.editEntry(newContactInfo, currentSelectedEntry);
        }

        // update the JTable, addressBookDisplay
        DefaultTableModel addressBookModel = new DefaultTableModel(getAddressBookDisplay(), columnNames);
        addressBookDisplay.setModel(addressBookModel);

        // Removing "add new contact" screen because contact has been added.
        contactFieldsDisplayPanel.remove(contactFields);
        this.frame.pack();
        this.frame.setVisible(true);

        //Combined the old update stuff
        //refreshTable();
    }

    // helper function to encapsulate delete entry behavior
    private void deleteEntry() {
        // check if there is an entry selected
        if (currentSelectedEntry != null) {
            controller.deleteEntry(currentSelectedEntry);


            // update the JTable, addressBookDisplay
            /*DefaultTableModel addressBookModel = new DefaultTableModel(getAddressBookDisplay(), columnNames);
            addressBookDisplay.setModel(addressBookModel);*/

            // Removing "add new contact" screen because contact has been added.
            contactFieldsDisplayPanel.remove(contactFields);
            /*this.frame.pack();
            this.frame.setVisible(true);*/
            refreshTable();
        }
    }


    public void actionPerformed(ActionEvent e) {
        // If you select a contact, info will display on side, and there are save and delete options
        // editable fields
        if (e.getSource() == NewContact) {
            displayNewContact(); // put this on right side of window

            // set the selected entry to null
            currentSelectedEntry = null;
        } else if (e.getSource() == saveOption) {
            // Controller.save, true or false if saved. display "saved successfully" method
            // If save failed, display "error while saving"

            try {
                controller.save();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error", "Failed to save Address Book.", JOptionPane.ERROR_MESSAGE);
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
                    JOptionPane.showMessageDialog(frame, "Error", "Failed to save Address Book as " + userFileName, JOptionPane.ERROR_MESSAGE);
                }
            }

        }

        else if (e.getSource() == deleteOption) {
            boolean deleted;
            int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this phone book?", "Warning",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                deleted = controller.delete();

                if (deleted == false) {
                    JOptionPane.showMessageDialog(frame, "Unable to delete address book.");
                }
                else {
                    frame.dispose();
                }
            }
        }
        // If the user wants to save a new contact, send info from text fields to controller
        else if (e.getSource() == ContactSave) {
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
                controller.validateEntry(newContactInfo);

                addOrEditEntry(newContactInfo);
            } catch (TooLittleInputException ex1) {

                // if user does not put in the required fields
                JOptionPane.showMessageDialog(frame, ex1.getMessage());
            } catch (InvalidInputException ex2) {
                int response = JOptionPane.showConfirmDialog(null,
                        ex2.getMessage() + "Are you sure you want to continue anyways?", "Warning",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (response == JOptionPane.YES_OPTION) {
                    addOrEditEntry(newContactInfo);
                }

            }
            
        }

        else if (e.getSource() == ContactDelete) {
            int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this contact?", "Warning",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {

                deleteEntry();
            }
        }

        else if (e.getSource() == ContactCancel) {
            int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel without saving?", "Warning",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                contactFieldsDisplayPanel.remove(contactFields);
                frame.pack();
                frame.setVisible(true);
            }
        }


    }

    private void displayNewContact() {

        // empty the default text in each JTextField
        firstName.setText("");
        lastName.setText("");
        phone.setText("");
        address1.setText("");
        address2.setText("");
        city.setText("");
        state.setText("");
        zip.setText("");
        email.setText("");

        contactFields.repaint();

        contactFieldsDisplayPanel.add(contactFields);
        this.frame.pack();
        this.frame.setVisible(true);
    }

    // variant of displayNewContact for case where an AddressEntry is provided
    private void displayContact(AddressEntry entry) {
        firstName.setText(entry.getFirstName());
        lastName.setText(entry.getLastName());
        phone.setText(entry.getPhone());
        address1.setText(entry.getDelivery());
        address2.setText(entry.getSecond());
        city.setText(entry.getCity());
        state.setText(entry.getState());
        zip.setText(entry.getZip());
        email.setText(entry.getEmail());

        contactFields.repaint();

        contactFieldsDisplayPanel.add(contactFields);
        this.frame.pack();
        this.frame.setVisible(true);
    }

    private void refreshTable(){
        DefaultTableModel addressBookModel = new DefaultTableModel(getAddressBookDisplay(), columnNames);
        addressBookDisplay.setModel(addressBookModel);
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

//Comment for git2