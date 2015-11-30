package naiveBayes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Hashtable;

public class bayes {
	private static ArrayList<Double> sepalL = new ArrayList<Double>();
	private static ArrayList<Double> sepalW = new ArrayList<Double>();
	private static ArrayList<Double> petalL = new ArrayList<Double>();
	private static ArrayList<Double> petalW = new ArrayList<Double>();
	private static ArrayList<String> classLabel = new ArrayList<String>();
	private static Hashtable<String, Double> setosa = new Hashtable<String, Double>();
	private static Hashtable<String, Double> versicolour = new Hashtable<String, Double>();
	private static Hashtable<String, Double> virginica = new Hashtable<String, Double>();
	private static ArrayList<String> results = new ArrayList<String>();
	private static int testCount = 0;
	private static int correctGuess = 0;
	/**
	 * @param args
	 */
	
	/**
	 * Attribute Information:
   1. sepal length in cm
   2. sepal width in cm
   3. petal length in cm
   4. petal width in cm
   5. class: 
      -- Iris Setosa
      -- Iris Versicolour
      -- Iris Virginica

	 */
	public static void main(String[] args) {
		
		//Naive Bayes
		
		// Read in/ Store Data From Training Set. 
		readTraining("training.txt");
		
		//calculate mean/standard dev values
		calculateStats();
		//P(x) = p(k|x)*p(y|x)...
		//P(z) = p(k|z)*P(y|Z)... choose bigger number? how do you do it for non-binary data?
		
		// Test Set: Read in and do calculation based off of values. Write Out
		testData("test.txt");
		// Z-scores?
		//Calculations based only off of petal length & width as 
		// data description claims those are the only two with 
		// high class correlation
		
		//Create an Equation? //evidence can be ignored
		//System.out.println(correctGuess);
	}

	private static void testData(String f) {
		try{
		    
		    FileInputStream fstream = new FileInputStream(f);
		    FileOutputStream outstream = new FileOutputStream("bayesResult.txt");
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
		    	results.add(bayesTest(Double.parseDouble(s[2]),Double.parseDouble(s[3]), s[4]));
		    	
		    }
		    output.write("Iris Data Set\n");
		    output.write("Accuracy: " + ((correctGuess / testCount) * 100) + "%\n");
		    output.write("Setosa\n");
		    output.write("Petal Length Mean: " + setosa.get("plMean") + "\n");
		    output.write("Petal Length Variance: " + setosa.get("plStd") + "\n");
		    output.write("Petal Width Mean: " + setosa.get("pwMean") + "\n");
		    output.write("Petal Width Variance: " + setosa.get("pwStd") + "\n");
		    output.write("Versicolor\n");
		    output.write("Petal Length Mean: " + versicolour.get("plMean") + "\n");
		    output.write("Petal Length Variance: " + versicolour.get("plStd") + "\n");
		    output.write("Petal Width Mean: " + versicolour.get("pwMean") + "\n");
		    output.write("Petal Width Variance: " + versicolour.get("pwStd") + "\n");
		    output.write("Virginica\n");
		    output.write("Petal Length Mean: " + virginica.get("plMean") + "\n");
		    output.write("Petal Length Variance: " + virginica.get("plStd") + "\n");
		    output.write("Petal Width Mean: " + virginica.get("pwMean") + "\n");
		    output.write("Petal Width Variance: " + virginica.get("pwStd") + "\n");
		    for(int x = 0; x < results.size(); x++) {
		    	output.write(results.get(x) + "\n");
		    }
		    //Close the input stream
		    output.close();
		    in.close();
		    }catch (Exception e){//Catch exception if any
		      System.err.println("Error: " + e.getMessage());
		    }
	}
	
	private static String bayesTest(double petalL, double petalW, String classLabel){
		String verdict = "None";
		testCount++;
		//use z-score in probability
		double setlz = Math.abs((petalL - setosa.get("plMean")) / setosa.get("plStd"));
		double setwz = Math.abs((petalW - setosa.get("pwMean")) / setosa.get("pwStd"));
		
		double verlz = Math.abs((petalL - versicolour.get("plMean")) / versicolour.get("plStd"));
		double verwz = Math.abs((petalW - versicolour.get("pwMean")) / versicolour.get("pwStd"));
		
		double virlz = Math.abs((petalL - virginica.get("plMean")) / virginica.get("plStd"));
		double virwz = Math.abs((petalW - virginica.get("pwMean")) / virginica.get("pwStd"));
		
		double setp = (1 - (setosa.get("count") / sepalL.size()));
		double verp = (1 - (versicolour.get("count") / sepalL.size()));
		double virp = (1 - (virginica.get("count") / sepalL.size()));
		
		double setosap = setp * setlz * setwz;
		double versicolourp = verp * verlz * verwz;
		double virginicap = virp * virlz * virwz;
		
		if(setosap < versicolourp && setosap < virginicap) {
			//predict setosa
			verdict = "Guess: Setosa. Actual: " + classLabel;
			if(classLabel.equalsIgnoreCase("Iris-setosa")) {
				correctGuess++;
			}
			
		} else if (versicolourp < setosap && versicolourp < virginicap ) {
			//predict versicolour
			verdict = "Guess: Versicolour. Actual: " + classLabel;
			if(classLabel.equalsIgnoreCase("Iris-versicolor")) {
				correctGuess++;
			}
		} else {
			//predict virginica
			verdict = "Guess: Virginica. Actual: " + classLabel;
			if(classLabel.equalsIgnoreCase("Iris-virginica")) {
				correctGuess++;
			}
		}
		//choose smallest as guess
		return verdict;
		
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
		    	//s[0].toString().
		    	
		    	sepalL.add(Double.parseDouble(s[0]));//doesn't matter
		    	sepalW.add(Double.parseDouble(s[1]));//doesn't matter
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
	
	private static void calculateStats() {
		plStats();
		pwStats();
	}
	
	private static void plStats() {
		//calculate petal length mean & std for the 3 flower classes
		double pset = 0;
		int setx = 0;
		double pver = 0;
		int verx = 0;
		double pvir = 0;
		int virx = 0;
		//calculate petal length Mean
		for(int x = 0; x < petalL.size(); x++){
			if(classLabel.get(x).equalsIgnoreCase("Iris-setosa")) {
				pset += petalL.get(x);
				setx++;
			} else
				if(classLabel.get(x).equalsIgnoreCase("Iris-versicolor")) {
					pver += petalL.get(x);
					verx++;
				} else {
					pvir += petalL.get(x);
					virx++;
				}
		}
		
		double plsetMean = pset / setx;
		double plverMean = pver / verx;
		double plvirMean = pvir /virx;
		
		setosa.put("plMean", plsetMean);
		versicolour.put("plMean", plverMean);
		virginica.put("plMean", plvirMean);
		//calculate petal length
		pset = 0;
		pver = 0;
		pvir = 0;
		for(int x = 0; x < petalL.size(); x++){
			if(classLabel.get(x).equalsIgnoreCase("Iris-setosa")) {
				pset += ((petalL.get(x) - plsetMean) * (petalL.get(x) - plsetMean));
			} else
				if(classLabel.get(x).equalsIgnoreCase("Iris-versicolor")) {
					pver += ((petalL.get(x) - plverMean) * (petalL.get(x) - plverMean));
				} else {
					pvir += ((petalL.get(x) - plvirMean) * (petalL.get(x) - plvirMean));
				}
		}
		
		double plsetStd = Math.sqrt(pset);
		double plverStd = Math.sqrt(pver);
		double plvirStd = Math.sqrt(pvir);
		
		setosa.put("plStd", plsetStd);
		versicolour.put("plStd", plverStd);
		virginica.put("plStd", plvirStd);
		//record counts
		setosa.put("count", (double) setx);
		versicolour.put("count", (double) verx);
		virginica.put("count", (double) virx);
	}
	
	private static void pwStats() {
		//calculate petal width mean & std for the 3 flower classes
		double pset = 0;
		int setx = 0;
		double pver = 0;
		int verx = 0;
		double pvir = 0;
		int virx = 0;
		//calculate petal width Mean
		for(int x = 0; x < petalW.size(); x++){
			if(classLabel.get(x).equalsIgnoreCase("Iris-setosa")) {
				pset += petalW.get(x);
				setx++;
			} else
				if(classLabel.get(x).equalsIgnoreCase("Iris-versicolor")) {
					pver += petalW.get(x);
					verx++;
				} else {
					pvir += petalW.get(x);
					virx++;
				}
		}
		
		double pwsetMean = pset / setx;
		double pwverMean = pver / verx;
		double pwvirMean = pvir /virx;
		
		setosa.put("pwMean", pwsetMean);
		versicolour.put("pwMean", pwverMean);
		virginica.put("pwMean", pwvirMean);
		//calculate petal width
		pset = 0;
		pver = 0;
		pvir = 0;
		for(int x = 0; x < petalW.size(); x++){
			if(classLabel.get(x).equalsIgnoreCase("Iris-setosa")) {
				pset += ((petalW.get(x) - pwsetMean) * (petalW.get(x) - pwsetMean));
			} else
				if(classLabel.get(x).equalsIgnoreCase("Iris-versicolor")) {
					pver += ((petalW.get(x) - pwverMean) * (petalW.get(x) - pwverMean));
				} else {
					pvir += ((petalW.get(x) - pwvirMean) * (petalW.get(x) - pwvirMean));
				}
		}
		
		double pwsetStd = Math.sqrt(pset);
		double pwverStd = Math.sqrt(pver);
		double pwvirStd = Math.sqrt(pvir);
		
		setosa.put("pwStd", pwsetStd);
		versicolour.put("pwStd", pwverStd);
		virginica.put("pwStd", pwvirStd);
	
	}
}
