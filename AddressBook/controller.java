public class Controller {

	private AddressBook currentBook;


	public Controller() {
		
	}

	public loadAddressBook (String tsvFileName) throws Exception{
		BufferedReader TSVFileReader=new BufferedReader(new FileReader(tsvFileName));
		String dataRow=TSVFileReader.readLine();
		numEntries =0;
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
	public returnCurrentBook() {
		return currentBook.returnEntries();
	}



}