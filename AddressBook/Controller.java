package AddressBook;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

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

	public static bool validateEnoughInputs(String[9] dataFields) {
		if (!(dataFields[0] != "" && dataFields[1] != "" && dataFields[5] != "" && dataFields[8] != "")) {
			return False;
		}
		else {
			return True;
		}
	}



	// this method adds an entry to the currentBook
	public void addEntry(String[9] dataFields) {
		newEntry = new AddressEntry(dataFields);
		currentBook.addEntry(newEntry);
	}

}