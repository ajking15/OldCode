package lab11;

public class Patron implements Comparable{
	private Name name;
	private int age;
	Patron(String firstName, String lastName, int age)
	{
		name = new Name(firstName, lastName);
		this.age = age;
	}

	public Name getName()
	{
		return name;
	}
	
	public int getAge()
	{
		return age;
	}
	
	public int compareTo(Object o) {
		Patron pat = (Patron)o;
		
		return name.compareTo(pat.getName());
	}
}
