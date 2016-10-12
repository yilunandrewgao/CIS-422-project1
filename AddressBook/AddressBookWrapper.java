package AddressBook;

/**
 * Created by megfredericks on 10/11/16.
 */
public class AddressBookWrapper {

    private AddressBookWrapper(String tsvFileName) {
        Controller c = new Controller();
        c.loadAddressBook(tsvFileName);
    }
}
