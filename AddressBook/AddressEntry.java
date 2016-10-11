
public class AddressEntry {
	//0=FirstName,1=LastName,2=Delivery,3=Second,4=Email,5=Phone,6=City,7=State,8=Zip;
	private String[] Entrys=new String[9];
	
	public void getEntry(int index, String entry)
	{
		this.Entrys[index]=entry;
	}
	
	public void setFirstName(String FirstName)
	{
		this.Entrys[0]=FirstName;
	}
	public String getFirstName()
	{
		return this.Entrys[0];
	}
	
	@Override
	public String toString() {
		return "First Name: " + getFirstName() + " Last Name: " + getLastName();
	}
	
	public void setLastName(String LastName)
	{
		this.Entrys[1]=LastName;
	}
	public String getLastName()
	{
		return this.Entrys[1];
	}
	
	public void setDelivery(String Delivery)
	{
		this.Entrys[2]=Delivery;
	}
	public String getDelivery()
	{
		return this.Entrys[2];
	}
	
	public void setSecond(String Second)
	{
		this.Entrys[3]=Second;
	}
	public String getSecond()
	{
		return this.Entrys[3];
	}
	
	public void setEmail(String Email)
	{
		this.Entrys[4]=Email;
	}
	public String getEmail()
	{
		return this.Entrys[4];
	}
	
	public void setPhone(String Phone)
	{
		this.Entrys[5]=Phone;
	}
	public String getPhone()
	{
		return this.Entrys[5];
	}
	
	public void setCity(String City)
	{
		this.Entrys[6]=City;
	}
	public String getCity()
	{
		return this.Entrys[6];
	}
	
	public void setState(String State)
	{
		this.Entrys[7]=State;
	}
	public String getState()
	{
		return this.Entrys[7];
	}
	
	public void setZip(String Zip)
	{
		this.Entrys[8]=Zip;
	}
	public String getZip()
	{
		return this.Entrys[8];
	}
}
