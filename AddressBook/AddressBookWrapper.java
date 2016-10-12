package AddressBook;

/**
 * Created by megfredericks on 10/11/16.
 */
public class AddressBookWrapper {

    public AddressBookWrapper(String tsvFileName) {
        Controller c = new Controller(tsvFileName);
        c.loadAddressBook();
    }
}
