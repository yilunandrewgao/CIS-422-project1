package AddressBook;

import java.io.BufferedReader;
import java.io.*;
import java.util.ArrayList;
import java.util.regex.*;

/**
 * The Controller is a connection between an AddressBook and the GUI that allows the user to manipulate it.
 * It does not interact directly with the user, but with the AddressBookWrapper.
 */
public class Controller {

	private AddressBook currentBook;
	private AddressBook bookToDisplay;
	private DisplayGUI GUIController;


	/**
	 * Creates a new Controller using a file name. The controller then
	 * creates a new AddressBook from the TSV file.
	 *
	 * @param _tsvFileName		the path of the TSV file to be accessed
	 * @param _GUIController	The GUI
	 */
	public Controller(String _tsvFileName, DisplayGUI _GUIController) {
		currentBook = new AddressBook(_tsvFileName);
		GUIController = _GUIController;
		bookToDisplay = currentBook.copyAddressBook();

	}

	/**
	 * Loads an address book from a TSV file.
	 *
	 * @throws Exception
	 */
	public void loadAddressBook () throws Exception{
		BufferedReader TSVFileReader=new BufferedReader(new FileReader(currentBook.getFileName()));
		String dataRow=TSVFileReader.readLine();
		dataRow=TSVFileReader.readLine();

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

	/**
	 * Creates a TSV file so that the AddressBook can be saved into it, including any changes
	 * that may have been made to its contents.
	 *
	 * @param FileName		the name of the file to be saved to
	 * @param book			The AddressBook that is being saved
	 * @throws Exception
	 */
	public static void createTsvFile(String FileName, AddressBook book) throws Exception {
		BufferedWriter bw = new BufferedWriter(new FileWriter(FileName));
		bw.write("FirstName\tLastName\tDelivery\tSecond\tEmail\tPhone\tCity\tState\tZip\n");

		ArrayList<AddressEntry> listOfEntries = book.returnEntries();

		for (int i = 0; i < listOfEntries.size(); i++) {
			bw.write(listOfEntries.get(i).toString());
			if (i < listOfEntries.size() - 1){
				bw.write("\n");
			}

		}

		bw.close();

	}

	/**
	 * Overwrites the current .tsv file with new info.
	 *
	 * @throws Exception
	 */
	public void save() throws Exception {

		createTsvFile(currentBook.getFileName(),currentBook);

	}

	/**
	 * Creates a new .tsv file with new info
	 *
	 * @param newFileName	The name of the TSV file to be saved to
	 * @throws Exception
	 */
	public void saveAs(String newFileName) throws Exception {

		createTsvFile(newFileName,currentBook);

	}

	/**
	 * Deletes the current .tsv file and removes it from GUIController's array
	 */

	public boolean delete() {
		File file = new File(currentBook.getFileName());

		// remove it from GUIController's array
		GUIController.getOpenBooks().remove(currentBook);

		return file.delete();
	}

	/**
	 * Returns a list of the current AddressEntry objects in the current AddressBook
	 *
	 * @return	an ArrayList of AddressEntry objects in the current AddressBook.
	 */
	public ArrayList<AddressEntry> returnCurrentBook() {
		return currentBook.returnEntries();
	}

	/**
	 * Returns the AddressEntry objects in this book.
	 * @return
	 */
	public ArrayList<AddressEntry> returnBookToDisplay() {
		return bookToDisplay.returnEntries();
	}

	/**
	 * Checks the data that the user wants to add to an AddressEntry for unwanted input.
	 * This includes too few fields being filled in, and invalid input.
	 *
	 * @param dataFields				an array of Strings containing the fields for the Entry
	 * @throws TooLittleInputException
	 * @throws InvalidInputException
	 */
	public void validateEntry(String[] dataFields) throws TooLittleInputException, InvalidInputException {

		int other_nonempty_fields = 0;
		for (int i = 0; i <dataFields.length; i++) {
			if (!(dataFields[i].isEmpty()) && i != 0 && i != 1) {
				other_nonempty_fields++;
			}
		}

		if( (dataFields[0].isEmpty() && dataFields[1].isEmpty()) || (other_nonempty_fields < 1)) {

			throw new TooLittleInputException("Please enter a first name or last name and another field.");

		}

		else {

			ArrayList<InputError> errorList = new ArrayList<InputError>();

			if (!Pattern.matches("^(\\(\\d{3}\\)|\\d{3})[\\s-]?\\d{3}[\\s-]?\\d{4}$|^$", dataFields[5])) {
				errorList.add(new InputError("Phone number is invalid.", 5));
			}

			if (!Pattern.matches("^\\d{5}$|^\\d{5}-\\d{4}$|^$", dataFields[8])) {
				errorList.add(new InputError("ZIP code is invalid.", 8));
			}

//			if (!Pattern.matches("^[A-Z]{2}$", dataFields[7])) {
//				errorList.add(new InputError("State is invalid.", 7));
//			}

			if (!Pattern.matches("^.*@.*\\..*$|^$", dataFields[4])) {
				errorList.add(new InputError("Email is invalid.", 4));
			}


			if (errorList.size() > 0) {
				throw new InvalidInputException(errorList);
			}

		}



	}

	/**
	 * Adds an entry to the current AddressBook
	 *
	 * @param dataFields	an array of Strings containing the fields that the new Entry should contain
	 */
	public void addEntry(String[] dataFields) {


		AddressEntry newEntry = new AddressEntry(dataFields);

		currentBook.addEntry(newEntry);

		bookToDisplay = currentBook.copyAddressBook();
	}

	/**
	 * Edits the contents of an AddressEntry
	 *
	 * @param dataFields	the new information for this Entry
	 * @param oldEntry		the AddressEntry being edited
	 */
	public void editEntry(String[] dataFields, AddressEntry oldEntry) {

		// create the edited entry
		AddressEntry editedEntry = new AddressEntry(dataFields);

		currentBook.editEntry(editedEntry, oldEntry);

		bookToDisplay = currentBook.copyAddressBook();

	}

	/**
	 * Deletes an AddressEntry from the current AddressBook
	 *
	 * @param entryToDelete		the AddressEntry to be deleted
	 */
	public void deleteEntry(AddressEntry entryToDelete) {

		currentBook.deleteEntry(entryToDelete);
		bookToDisplay = currentBook.copyAddressBook();
	}

	/**
	 * Searches for a keyword in any of the fields of any AddressEntry in the current
	 * AddressBook.
	 *
	 * @param keyword	a String representing a keyword to be searched for
	 */
	public void searchAddressBook(String keyword) {
		ArrayList<AddressEntry> newList = new ArrayList<AddressEntry>();

		// populate newList with entries containing the keyword
		for (AddressEntry entry : currentBook.returnEntries()) {
			if (entry.toString().toLowerCase().contains(keyword.toLowerCase())){
				newList.add(entry);
			}
		}

		// change contents of bookToDisplay
		bookToDisplay.setEntries(newList);

	}

	/**
	 * Sets the AddressBook to be displayed to the current AddressBook.
	 */
	public void cancelSearch() {
		bookToDisplay = currentBook.copyAddressBook();

	}


}