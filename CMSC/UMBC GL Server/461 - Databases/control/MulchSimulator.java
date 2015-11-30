package control;

import view.Reports;
import data.MulchDatabase;

public class MulchSimulator {
	private static MulchDatabase mulchDB;
	/**
	 * @param args
	 * SELECT seller, SUM(shredded), SUM(nugget), SUM(black), SUM(red)
     * FROM big_bird_forest
	 * GROUP BY seller
	 * ORDER BY seller
	 * Create a view with all the data inserted into it?
	 * sounds fantastic.
	 * then the annoying part will be formatting the report
	 */
	/*
	 * REPORT - SUMMARY OF ORDERS BY SELLER
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//takes in user/pass(gl|local)
		
		if(args.length != 3){
			System.out.println("Invalid cmd line args: user/pass/(gl|local)");
			System.exit(-1);
		}
		//scripts run outside of app
		if(args[0].equalsIgnoreCase("local")){
			System.out.println("local");
			
			runLocal(args[1], args[2]);
		} else {
			System.out.println("gl");
			
			runGL(args[1], args[2]);
		}
		
	}

	private static void runLocal(String user, String pass){
		//run on oracle
		mulchDB = new MulchDatabase(true, user, pass);
		//connect
		System.out.println(user);
		System.out.println(pass);
		mulchDB.connect();
		//build report using view package
		String sql = "SELECT seller, NVL(SUM(num_shredded),0) as shredded, NVL(SUM(num_nugget),0) as nugget, NVL(SUM(num_black),0) as black, NVL(SUM(num_red),0) as red, NVL(SUM(num_shredded),0) + NVL(SUM(num_nugget),0) + NVL(SUM(num_black),0) + NVL(SUM(num_red),0) AS total_sold, SUM(amount_due) AS amount_due FROM all_orders GROUP BY CUBE(seller) ORDER BY seller";
		mulchDB.execSQL(sql);
		//mulchDB.printResults();
		//basically take print results except with formatted strings.
		Reports.SummaryBySeller(mulchDB.getResults());
		//disconnect
		mulchDB.disconnect();
	}
	
	private static void runGL(String user, String pass) {
		//run on oracle
		mulchDB = new MulchDatabase(false, user, pass);
		//connect
		System.out.println(user);
		System.out.println(pass);
		mulchDB.connect();
		//build report using view package
		// = "SELECT seller, NVL(SUM(num_shredded),0) as shredded, NVL(SUM(num_nugget),0) as nugget, NVL(SUM(num_black),0) as black, NVL(SUM(num_red),0) as red, NVL(SUM(num_shredded),0) + NVL(SUM(num_nugget),0) + NVL(SUM(num_black),0) + NVL(SUM(num_red),0) AS total_sold, SUM(amount_due) AS amount_due FROM all_orders GROUP BY CUBE(seller) ORDER BY seller";
		String sql = "SELECT * FROM summary_of_sellers";
		mulchDB.execSQL(sql);
		//mulchDB.printResults();
		//disconnect
		Reports.SummaryBySeller(mulchDB.getResults());
		mulchDB.disconnect();
	}
}
