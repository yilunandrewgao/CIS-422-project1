package AddressBook;

import java.util.ArrayList;

/**
 * An AddressBook keeps track of a number of AddressEntry objects. It can manipulate these entries,
 * with operations including deleting contacts, adding new contacts, and editing contacts.
 */
public class AddressBook {
	private ArrayList<AddressEntry> book;
	private String fileName;

	/**
	 * Creates a new AddressBook based on a file, passed in as a String.
	 *
	 * @param fileName	a String representation of the path to the file to be accessed
	 */
	public AddressBook(String fileName)
	{
		this.book = new ArrayList<AddressEntry>();
		this.fileName=fileName;
	}

	/**
	 * Returns the entries in this book.
	 *
	 * @return	an ArrayList of AddressEntry objects that are in this AddressBook
	 */
	public ArrayList<AddressEntry> returnEntries() {
		return this.book;
	}

	/**
	 * Adds a new AddressEntry to this AddressBook.
	 *
	 * @param newEntry	an AddressEntry to be added to this book.
	 */
	public void addEntry(AddressEntry newEntry) {
		book.add(newEntry);
	}

	/**
	 * Applies changes to an existing AddressEntry.
	 *
	 * @param editedEntry	an AddressEntry containing the information that was edited.
	 * @param oldEntry		the AddressEntry that should be edited, containing the information
	 *                      that was previously in this Entry
	 */
	public void editEntry(AddressEntry editedEntry, AddressEntry oldEntry) {
		// get index of oldEntry
		int entryIndex = book.indexOf(oldEntry);
		book.set(entryIndex, editedEntry);
	}

	/**
	 * Deletes an AddressEntry from this AddressBook.
	 *
	 * @param entryToDelete	the AddressEntry that should be deleted
	 */
	public void deleteEntry(AddressEntry entryToDelete) {
		book.remove(entryToDelete);
	}

	/**
	 * Changes the contents of this AddressBook to a new set of AddressEntry objects
	 *
	 * @param entryList	an ArrayList of AddressEntry objects.
	 */
	public void setEntries(ArrayList<AddressEntry> entryList) {
		book = entryList;
	}

	public String getFileName(){ return this.fileName; }

	/**
	 * Returns a copy of this AddressBook.
	 *
	 * @return	a copy of this AddressBook.
	 */
	public AddressBook copyAddressBook() {
		AddressBook newBook = new AddressBook(this.getFileName());
		newBook.setEntries(this.returnEntries());
		return newBook;
	}

	/**
	 * Returns a String representation of this AddressBook.
	 *
	 * @return	A String representing this AddressBook.
	 */
	public String toString() {
		return book.toString();
	}


}
