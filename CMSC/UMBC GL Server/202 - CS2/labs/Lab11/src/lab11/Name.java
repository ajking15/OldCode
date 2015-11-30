package lab11;

public class Name implements Comparable{
	private String firstName;
	private String lastName;
	
	Name(String first, String last)
	{
		firstName = first;
		lastName = last;
	}
	
	public String getFirstName()
	{
		return firstName;
	}
	
	public String getLastName()
	{
		return lastName;
	}
	
	public int compareTo(Object object)
	{
		Name n = (Name)object;
		
		if(lastName.compareTo(n.getLastName()) == 0)
		{
			return firstName.compareTo(n.getFirstName());
		} else {
			return lastName.compareTo(n.getLastName());
		}
	}
}
