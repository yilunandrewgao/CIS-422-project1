package AddressBook;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Created by megfredericks on 10/11/16.
 */
public class AddressBookWrapper implements ActionListener {

    private String fileName;
    private JFrame frame;
    private JTable addressBookDisplay;
    private JPanel tablePanel;
    private JButton newContact;


    private AddressBookWrapper(String tsvFileName) {
        Controller c = new Controller();
        this.fileName = tsvFileName;
        c.loadAddressBook(tsvFileName);
        displayWindow();
    }

    private void displayWindow() {
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

        this.frame = new JFrame(this.fileName);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.add(mainPanel);
        this.frame.pack();
        this.frame.setVisible(true);
    }

    private Object[][] getAddressBookDisplay() {
        Object[][] sampleData = {{"Meg", "Fredericks", "5412923031"}, {"Brooke", "Fredericks", "5412920283"}};
        return sampleData;
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
