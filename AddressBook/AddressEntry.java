package AddressBook;


public class AddressEntry {
	//0=FirstName,1=LastName,2=Delivery,3=Second,4=Email,5=Phone,6=City,7=State,8=Zip;
	private String[] entries;

	public AddressEntry() {
		this.entries = new String[9];
	}

	public AddressEntry(String[] _entries) {
		this.entries = _entries;
	}
	
	public void setEntry(int index, String entry)
	{
		this.entries[index]=entry;
	}
	
	public void setFirstName(String firstName)
	{
		this.entries[0]=firstName;
	}
	public String getFirstName()
	{
		return this.entries[0];
	}
	
	@Override
	public String toString() {
		String returnString = "";
		for (String field:entries) {
			returnString = returnString + field + "\t";
		}
		returnString = returnString.substring(0, returnString.length() - 1);
		return returnString;
	}
	
	public void setLastName(String lastName)
	{
		this.entries[1]=lastName;
	}
	public String getLastName()
	{
		return this.entries[1];
	}
	
	public void setDelivery(String delivery)
	{
		this.entries[2]=delivery;
	}
	public String getDelivery()
	{
		return this.entries[2];
	}
	
	public void setSecond(String second)
	{
		this.entries[3]=second;
	}
	public String getSecond()
	{
		return this.entries[3];
	}
	
	public void setEmail(String email)
	{
		this.entries[4]=email;
	}
	public String getEmail()
	{
		return this.entries[4];
	}
	
	public void setPhone(String phone)
	{
		this.entries[5]=phone;
	}
	public String getPhone()
	{
		return this.entries[5];
	}
	
	public void setCity(String city)
	{
		this.entries[6]=city;
	}
	public String getCity()
	{
		return this.entries[6];
	}
	
	public void setState(String state)
	{
		this.entries[7]=state;
	}
	public String getState()
	{
		return this.entries[7];
	}
	
	public void setZip(String zip)
	{
		this.entries[8]=zip;
	}
	public String getZip()
	{
		return this.entries[8];
	}
}
