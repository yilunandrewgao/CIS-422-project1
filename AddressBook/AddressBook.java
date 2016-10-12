package AddressBook;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class AddressBook {
	private ArrayList<AddressEntry> book;
	private int numEntries;
	
	public AddressBook() 
	{
		book = new ArrayList<AddressEntry>();
		numEntries = 0;
	}

	// return entries in this book
	public ArrayList<AddressEntry> returnEntries() {
		return this.book;
	}

	// adds a new entry
	public void addEntry(AddressEntry newEntry) {
		book.add(newEntry);
	}


}
