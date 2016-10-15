package AddressBook;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class AddressBook {
	private ArrayList<AddressEntry> book;
	private String fileName;
	
	public AddressBook(String fileName)
	{
		this.book = new ArrayList<AddressEntry>();
		this.fileName=fileName;
	}

	// return entries in this book
	public ArrayList<AddressEntry> returnEntries() {
		return this.book;
	}

	// adds a new entry
	public void addEntry(AddressEntry newEntry) {
		book.add(newEntry);
	}

	// edit an entry
	public void editEntry(AddressEntry editedEntry, AddressEntry oldEntry) {
		// get index of oldEntry
		int entryIndex = book.indexOf(oldEntry);
		book.set(entryIndex, editedEntry);
	}

	// delete an entry
	public void deleteEntry(AddressEntry entryToDelete) {
		book.remove(entryToDelete);
	}

	public String getFileName(){ return this.fileName; }


}
