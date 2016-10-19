package AddressBook;

/**
 * An AddressEntry contains information fields describing a contact.
 */
public class AddressEntry {
	//0=FirstName,1=LastName,2=Delivery,3=Second,4=Email,5=Phone,6=City,7=State,8=Zip;
	private String[] entries;

	/**
	 * Creates a new, empty entry
	 */
	public AddressEntry() {
		this.entries = new String[9];
	}

	/**
	 * Creates an entry with fields specified by an array.
	 *
	 * @param _entries	an array containing the fields to be added to this entry
	 */
	public AddressEntry(String[] _entries) {
		this.entries = _entries;
	}

	/**
	 * Stores a String in the specified field.
	 *
	 * @param index	The index of the field to be changed.
	 * @param entry	The String to be added to the field.
	 */
	public void setEntry(int index, String entry)
	{
		this.entries[index]=entry;
	}

	/**
	 * Sets the first name field of the entry.
	 *
	 * @param firstName	the first name
	 */
	public void setFirstName(String firstName)
	{
		this.entries[0]=firstName;
	}
	public String getFirstName()
	{
		return this.entries[0];
	}

	/**
	 * Returns a String representation of this entry.
	 *
	 * @return A String representing this entry.
	 */
	@Override
	public String toString() {
		String returnString = "";
		for (String field:entries) {
			returnString = returnString + field + "\t";
		}
		returnString = returnString.substring(0, returnString.length() - 1);
		return returnString;
	}

	/**
	 * Sets the last name field of this entry.
	 *
	 * @param lastName	the last name to be set
	 */
	public void setLastName(String lastName)
	{
		this.entries[1]=lastName;
	}

	/**
	 * Returns the last name of this entry.
	 *
	 * @return	the last name
	 */
	public String getLastName()
	{
		return this.entries[1];
	}

	/**
	 * Sets the delivery address of this entry.
	 *
	 * @param delivery	the delivery address to be set
	 */
	public void setDelivery(String delivery)
	{
		this.entries[2]=delivery;
	}

	/**
	 * Returns the delivery address of this entry
	 *
	 * @return	the delivery address
	 */
	public String getDelivery()
	{
		return this.entries[2];
	}

	/**
	 * Sets the second line of the address of this entry
	 *
	 * @param second	the second line of the address to be set
	 */
	public void setSecond(String second)
	{
		this.entries[3]=second;
	}

	/**
	 * Returns the second line of the address of this entry
	 *
	 * @return	the second line of the address
	 */
	public String getSecond()
	{
		return this.entries[3];
	}

	/**
	 * Sets the email of this entry
	 *
	 * @param email	the email to be set
	 */
	public void setEmail(String email)
	{
		this.entries[4]=email;
	}

	/**
	 * Returns the email of this entry
	 *
	 * @return	the email
	 */
	public String getEmail()
	{
		return this.entries[4];
	}

	/**
	 * Sets the phone of this entry
	 *
	 * @param phone	the phone to be set
	 */
	public void setPhone(String phone)
	{
		this.entries[5]=phone;
	}

	/**
	 * Returns the phone number of this entry
	 *
	 * @return	the phone number
	 */
	public String getPhone()
	{
		return this.entries[5];
	}

	/**
	 * Sets the city of this entry
	 *
	 * @param city	the city to be set
	 */
	public void setCity(String city)
	{
		this.entries[6]=city;
	}

	/**
	 * Returns the city of this entry
	 *
	 * @return	the city
	 */
	public String getCity()
	{
		return this.entries[6];
	}

	/**
	 * Sets the state of this entry
	 *
	 * @param state	the state to be set
	 */
	public void setState(String state)
	{
		this.entries[7]=state;
	}

	/**
	 * Returns the state of this entry.
	 *
	 * @return	the state
	 */
	public String getState()
	{
		return this.entries[7];
	}

	/**
	 * Sets the ZIP code of this entry
	 *
	 * @param zip	the ZIP code to be set
	 */
	public void setZip(String zip)
	{
		this.entries[8]=zip;
	}

	/**
	 * Returns the ZIP code of this entry
	 *
	 * @return	the ZIP code
	 */
	public String getZip()
	{
		return this.entries[8];
	}
}
