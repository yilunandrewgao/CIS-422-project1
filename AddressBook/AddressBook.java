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

	public String getFileName(){ return this.fileName; }


}
