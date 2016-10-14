package AddressBook;

import java.io.BufferedReader;
import java.io.*;
import java.util.ArrayList;
import java.util.regex.*;

public class Controller {

	private AddressBook currentBook;
	private DisplayGUI GUIController;


	public Controller(String _tsvFileName, DisplayGUI _GUIController) {
		currentBook = new AddressBook(_tsvFileName);
		GUIController = _GUIController;

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
	public void save() throws Exception {

		createTsvFile(currentBook.getFileName(),currentBook);

	}

	// Creates a new .tsv file with new info
	public void saveAs(String newFileName) throws Exception {

		createTsvFile(newFileName,currentBook);

	}

	// Deletes the current .tsv file and removes it from GUIController's array
	public boolean delete() {
		File file = new File(currentBook.getFileName());

		// remove it from GUIController's array
		GUIController.getOpenBooks().remove(currentBook);

		return file.delete();
	}

	// this method returns a list of entry objects for the currentBook
	public ArrayList<AddressEntry> returnCurrentBook() {
		return currentBook.returnEntries();
	}
	


	public void validateEntry(String[] dataFields) throws TooLittleInputException, InvalidInputException {

		if (dataFields[0].isEmpty() || dataFields[1].isEmpty() || dataFields[5].isEmpty() || dataFields[8].isEmpty()) {
			throw new TooLittleInputException("Not all of First name, last name, zip and phone are filled in.");
		}

		else if (!Pattern.matches("^(\\(\\d{3}\\)|\\d{3})[\\s-]?\\d{3}[\\s-]?\\d{4}$", dataFields[5])) {
			throw new InvalidInputException("Phone number is invalid.", 5);
		}

		else if (!Pattern.matches("^\\d{5}$", dataFields[8])) {
			throw new InvalidInputException("ZIP code is invalid.", 8);
		}

		else if (!Pattern.matches("^[A-Z]{2}$", dataFields[7])) {
			throw new InvalidInputException("State is invalid.", 7);
		}

		else if (!Pattern.matches("^.*@.*\\..*$", dataFields[4])) {
			throw new InvalidInputException("Email is invalid.", 4);
		}



	}

	// this method adds an entry to the currentBook
	public void addEntry(String[] dataFields) {


		AddressEntry newEntry = new AddressEntry(dataFields);

		currentBook.addEntry(newEntry);
	}

	public void editEntry(String[] dataFields, AddressEntry oldEntry) {

		// create the edited entry
		AddressEntry editedEntry = new AddressEntry(dataFields);

		currentBook.editEntry(editedEntry, oldEntry);

	}

}