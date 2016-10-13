package AddressBook;

import java.io.BufferedReader;
import java.io.*;
import java.util.ArrayList;
import java.util.regex.*;

public class Controller {

	private AddressBook currentBook;



	public Controller(String _tsvFileName) {
		currentBook = new AddressBook(_tsvFileName);

	}

	public void loadAddressBook () throws Exception{
		BufferedReader TSVFileReader=new BufferedReader(new FileReader(currentBook.getFileName()));
		String dataRow=TSVFileReader.readLine();

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

			dataRow=TSVFileReader.readLine();			
		}
		TSVFileReader.close();
	}

	// Helper function for saveAddressBook
	public static void createTsvFile(String FileName, AddressBook book) throws Exception {
		BufferedWriter bw = new BufferedWriter(new FileWriter(FileName));

		ArrayList<AddressEntry> listOfEntries = book.returnEntries();

		for (int i = 0; i < listOfEntries.size(); i++) {
			bw.write(listOfEntries.get(i).toString());
			if (i < listOfEntries.size() - 1){
				bw.write("\n");
			}

		}

		bw.close();

	}

	// overwrites the current .tsv file with new info
	public void save(){

		try {
			createTsvFile(currentBook.getFileName(),currentBook);
		} catch (Exception e) {
			System.out.println("Failed to save Address Book");
		}

	}

	// Creates a new .tsv file with new info
	public void saveAs(String newFileName){

		try {
			createTsvFile(newFileName,currentBook);
		} catch (Exception e) {
			System.out.println("Failed to save Address Book as " + newFileName);
		}

	}

	// this method returns a list of entry objects for the currentBook
	public ArrayList<AddressEntry> returnCurrentBook() {
		return currentBook.returnEntries();
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