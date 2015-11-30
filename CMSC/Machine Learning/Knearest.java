package kNearest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
//import java.util.Comparator;
//import java.util.PriorityQueue;
import java.util.HashMap;


public class Knearest {
	private static ArrayList<Double> petalL = new ArrayList<Double>();
	private static ArrayList<Double> petalW = new ArrayList<Double>();
	private static ArrayList<String> classLabel = new ArrayList<String>();
	private static ArrayList<String> results = new ArrayList<String>();
	//private static Comparator<HashMap<String, String>> comp = new hashMapComparator();
	private static ArrayList<HashMap<String, String>> kList = new ArrayList<HashMap<String, String>>();
	private static int testCount = 0;
	private static int correctGuess = 0;
	private static final int k = 1;
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Read in Data Values
		readTraining("training.txt");
		
		//Test. Modular for various values of K
		testData("test.txt");
		//Use a priority queue based off of distance. and count the votes
		// of the top K? crap. how do I remember the label then
		//Distance
		//Label
		
		//
	}
	
	private static void readTraining(String f) {
		//Read In Data and Store it
		//ArrayList for each column
		
		try{
		    
		    FileInputStream fstream = new FileInputStream(f);
		    // Get the object of DataInputStream
		    DataInputStream in = new DataInputStream(fstream);
		    BufferedReader br = new BufferedReader(new InputStreamReader(in));
		    String strLine;
		    //Read File Line By Line
		    while ((strLine = br.readLine()) != null)   {
		      // Print the content on the console
		    	//line by line. so don't need a for loop.
		    	String[] s = strLine.split(",");
		    	
		    	petalL.add(Double.parseDouble(s[2]));
		    	petalW.add(Double.parseDouble(s[3]));
		    	classLabel.add(s[4]);
		    	
		    	//System.out.println (s[3]);
		      //System.out.println (s[4]);
		    }
		    //Close the input stream
		    in.close();
		    }catch (Exception e){//Catch exception if any
		      System.err.println("Error: " + e.getMessage());
		    }
	}
	
	private static void testData(String f) {
try{
		    
		    FileInputStream fstream = new FileInputStream(f);
		    FileOutputStream outstream = new FileOutputStream("k" + k + "NearestResultTraining.txt");
		    // Get the object of DataInputStream
		    DataInputStream in = new DataInputStream(fstream);
		    DataOutputStream out = new DataOutputStream(outstream);
		    BufferedReader br = new BufferedReader(new InputStreamReader(in));
		    BufferedWriter output = new BufferedWriter(new OutputStreamWriter(out));
		    String strLine;
		    //Read File Line By Line
		    while ((strLine = br.readLine()) != null)   {
		      // Print the content on the console
		    	//line by line. so don't need a for loop.
		    	String[] s = strLine.split(",");
		    	//read in values. calculate distance make determination
		    	results.add(calculateKNearest(Double.parseDouble(s[2]),Double.parseDouble(s[3]), s[4]));
		    	
		    }
		    //output.write("Christopher Mai\n");
		    output.write("Iris Data Set\n");
		    output.write("K: " + k + "\n");
		    output.write("Source: " + f + "\n");
		    output.write("Accuracy: " + correctGuess + "/" + testCount + "\n");
		    //output.write("Accuracy: " + ((correctGuess / testCount) * 100) + "%\n");
		    //System.out.println(correctGuess);
		    //System.out.println(testCount);
		    for(int x = 0; x < results.size(); x++) {
		    	//output.write(results.get(x) + "\n");
		    }
		    //Close the input stream
		    output.close();
		    in.close();
		    }catch (Exception e){//Catch exception if any
		      System.err.println("Error: " + e.getMessage());
		    }
	}
	
	private static String calculateKNearest(double length, double width, String label){
		String verdict = "None";
		//for each point go through the training set
		for(int x = 0; x < petalL.size(); x++){
			//calculate distance for each point
			HashMap<String, String> dist = new HashMap<String, String>();
			Double dis = Math.sqrt(((petalL.get(x) - length) * (petalL.get(x) - length)) + ((petalW.get(x) - width) * (petalW.get(x) - width)));
			
			if(dis != 0) {
				dist.put("Distance", dis.toString());
				dist.put("labelActual", label);
				dist.put("labelGuess", classLabel.get(x));
				kList.add(dist);
			}
			//change kQueue to an ArrayList?
			//
		}
		
		//find smallest k instances
		Collections.sort(kList, new Comparator<HashMap<String, String>>() {

			@Override
			public int compare(HashMap<String, String> arg0,
					HashMap<String, String> arg1) {
				double x = Double.parseDouble(arg0.get("Distance"));
				double y = Double.parseDouble(arg1.get("Distance"));
				
				if(x > y ) {
					return 1;
				} else if (x < y ) {
					return -1;
				} else {
					return 0;
				}
				
				// TODO Auto-generated method stub
				
			}
		});
		
//		for(int x = 0; x < kList.size(); x++) {
//			System.out.println(kList.get(x).get("Distance"));
//		}
//		System.out.println(kList.size());
		HashMap<String, String> guess = new HashMap<String, String>();
		double setv = 0;
		double verv = 0;
		double virv = 0;
		for(int x = 0; x < k; x++) {
			guess = kList.get(x);
			//System.out.println(guess.get("labelGuess"));
			//add weighting based off of distance?
			//closer points add a larger vote
			if(guess.get("labelGuess").equalsIgnoreCase("Iris-setosa")) {
				//vote for setosa
				setv++;
				//setv += (1 / Double.parseDouble(guess.get("Distance")));
			} else if(guess.get("labelGuess").equalsIgnoreCase("Iris-versicolor")) {
				//vote for versicolor
				verv++;
				//verv += (1 / Double.parseDouble(guess.get("Distance")));
			} else {
				//vote for virginica
				virv++;
				//virv += (1 / Double.parseDouble(guess.get("Distance")));
			}
		}
		
		if(setv > verv && setv > virv) {
			//Setosa!
			verdict = "Guess: Setosa. Actual: " + label;
			if(label.equalsIgnoreCase("Iris-setosa")) {
				correctGuess++;
			}
		} else
			if(verv > setv && verv > virv) {
				//Versicolor!
				verdict = "Guess: versicolor. Actual: " + label;
				if(label.equalsIgnoreCase("Iris-versicolor")) {
					correctGuess++;
				}
			} else {
				//Virginica!
				verdict = "Guess: Virginica. Actual: " + label;
				if(label.equalsIgnoreCase("Iris-virginica")) {
					correctGuess++;
				}
			}
//		System.out.println("setv: " + setv);
//		System.out.println("verv: " + verv);
//		System.out.println("virv: " + virv);
		
		testCount++;
		//wipe out kQueue for the next point
		//kQueue = new PriorityQueue<HashMap<String, String>>();
		 kList = new ArrayList<HashMap<String, String>>();
		 
		return verdict;
	}
}


