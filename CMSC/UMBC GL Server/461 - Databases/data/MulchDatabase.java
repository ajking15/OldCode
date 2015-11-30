package data;

public class MulchDatabase extends Database {
	/*
	 * translates between result sets returned by 
	 * JDBC into mulch objects & report objects
	 * Specific connect commands?
	 * ie, methods tailored for specific requests that then call connect?
	 * makes sense I suppose.
	 * new constructors?
	 * yes, with call to super
	 */
	public MulchDatabase() {
		super();
	}
	
	public MulchDatabase(boolean oracle, String user, String pass) {
		super(oracle,user,pass);
	}
	
}
