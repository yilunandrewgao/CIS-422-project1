package AddressBook;

import java.io.BufferedReader;
import java.io.*;
import java.util.ArrayList;
import java.util.regex.*;

public class Controller {

	private AddressBook currentBook;
	private String tsvFileName;


	public Controller(String _tsvFileName) {
		currentBook = new AddressBook();
		tsvFileName = _tsvFileName;
	}

	public void loadAddressBook () throws Exception{
		BufferedReader TSVFileReader=new BufferedReader(new FileReader(tsvFileName));
		String dataRow=TSVFileReader.readLine();
		int numEntries =0;
		while (dataRow!=null)
		{
			String[] dataArray=dataRow.split("\t");	
			int t=0;
			AddressEntry entry=new AddressEntry();
			for (String item:dataArray)
			{
				entry.setEntry(t,item);
				t++;
			}
			this.currentBook.addEntry(entry);
			numEntries++;
			dataRow=TSVFileReader.readLine();			
		}
		TSVFileReader.close();
	}

	// this method returns a list of entry objects for the currentBook
	public ArrayList<AddressEntry> returnCurrentBook() {
		return currentBook.returnEntries();
	}

	public static boolean validateEnoughInputs(String[] dataFields) {
		if (!(dataFields[0] != "" && dataFields[1] != "" && dataFields[5] != "" && dataFields[8] != "")) {
			return false;
		}
		else {
			return true;
		}
	}


	public void tryToAddEntry(String[] dataFields) throws TooLittleInputException, InvalidInputException {

		if (dataFields[0].isEmpty() || dataFields[1].isEmpty() || dataFields[5].isEmpty() || dataFields[8].isEmpty()) {
			throw new TooLittleInputException("Not all of First name, last name, zip and phone are filled in");
		}

		else if (!Pattern.matches("^(\\(\\d{3}\\)|\\d{3})[\\s-]?\\d{3}[\\s-]?\\d{4}$", dataFields[5])) {
			throw new InvalidInputException("Phone number is invalid", 5);
		}

		else if (!Pattern.matches("/d{5}", dataFields[8])) {
			throw new InvalidInputException("ZIP code is invalid", 8);
		}

		else if (!Pattern.matches("[A-Z]{2}", dataFields[7])) {
			throw new InvalidInputException("State is invalid", 7);
		}

		else if (!Pattern.matches(".*@.*\\..*", dataFields[4])) {
			throw new InvalidInputException("Email is invalid", 4);
		}



		else addEntry(dataFields);

	}

	// this method adds an entry to the currentBook
	public void addEntry(String[] dataFields) {


		AddressEntry newEntry = new AddressEntry(dataFields);

		currentBook.addEntry(newEntry);
	}

}