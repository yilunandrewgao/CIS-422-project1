import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class AddressBook {
	private ArrayList<AddressEntry> book=new ArrayList<AddressEntry>();
	private int numEntries;
	
	public AddressBook(String filename) throws Exception
	{
		BufferedReader TSVFile=new BufferedReader(new FileReader("TSVFile.tsv"));
		String dataRow=TSVFile.readLine();
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
			book.add(entry);
			numEntries++;
			dataRow=TSVFile.readLine();			
		}
		TSVFile.close();
	}

	// return entries in this book
	public returnEntries() {
		return this.book;
	}

	// adds a new entry
	public void addEntry(Entry newEntry) {
		book.add(newEntry)
	}
		
			
	}

}
