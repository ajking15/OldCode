/**
 * 
 */
package project1;
import java.util.Scanner;

/**
 * @author chrmai1
 *
 */
public class Project1 {
	public static final String ENCODE = "encode";
	public static final String DECODE = "decode";
	public static final int ONE = 1;
	public static final int ZERO = 0;
	public static final int FOUR = 4;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in);
		String[][] array = new String[5][5];
		String keyword;
		String encoded = "";
		String decoded = "";
		String option = "";
		String status = "TRUE";
		String alpha = "ABCDEFGHIJKLMNOPQRSTUVWYZ";
		
		System.out.println("Please enter the cipher keyword: "); //obtain the keyword
		keyword = keyboard.nextLine();
		
		keyword = removeWhitespace(keyword); //removes all whitespace
		
		keyword = keyword.toUpperCase();
		
		keyword = removeDuplicate(keyword); //removes duplicate letters
		
		alpha = prepAlpha(alpha, keyword); //removes letters in keyword from alphabet for array
		
		initializeArray(keyword, array, alpha);	 //initialize the array	
		
		while(status == "TRUE")// ensures the user chooses to either encode or decode
		{
			System.out.println("Encode or Decode? ");
			option = keyboard.nextLine();
			option = removeWhitespace(option);
			
			if(option.equalsIgnoreCase(ENCODE))
			{
				status = "FALSE";//ends loop
			} else
				if(option.equalsIgnoreCase(DECODE)) 
				{
					status = "FALSE";//ends loop
				} else {
					status = "TRUE";//continues loop because encode/decode not selected
				}
		}
		
		System.out.println("Enter the message: ");//obtains and preps message for encrypt/decrypt
		String message = keyboard.nextLine();
		message = removeWhitespace(message);
		message = message.toUpperCase();
		
		if(option.equals(ENCODE))
		{
			encoded = encode(array, message);//encodes array prints out the message
			System.out.println(encoded);
		} else {
			decoded = decode(array, message);//decodes the array and prints out the message
			System.out.println(decoded);
		}
	}
	
	/*
	 * Name: removeWhitespace
	 * @parameter keyword - keyword/message entered by the user
	 * @return - keyword/message with whitespace removed
	 */
	public static String removeWhitespace(String keyword)
	{
		keyword = keyword.replaceAll(" ", "");//replaces all whitespace
		
		return keyword;
	}
	
	/*
	 * Name: removeDuplicate
	 * @parameter keyword - keyword/message entered by the user
	 * @return - keyword/message with all duplicate letters removed
	 */
	public static String removeDuplicate(String keyword)
	{
		int m;
		
		for(m = 0; m < keyword.length(); m++)
		{
				if(keyword.indexOf(keyword.charAt(m)) != keyword.lastIndexOf(keyword.charAt(m)))
				{					
					String first = keyword.substring(0,keyword.indexOf(keyword.charAt(m))+1);					
					String second = keyword.substring(m+1);					
					second = second.replaceAll(keyword.charAt(m)+ "", "");					
					keyword = first + second;	//splits string and removes duplicates then concats into one string				
				}
		}
		return keyword;
	}
	
	/*
	 * Name: prepAlpha
	 * @parameter alpha - alphabet excluding X
	 * @parameter keyword - keyword entered by the user
	 * @return - alpha with letters from keyword removed
	 */
	public static String prepAlpha(String alpha, String keyword)
	{
		int m;
		
		for(m = 0; m < keyword.length(); m++)
		{
			alpha = alpha.replaceAll(keyword.charAt(m)+"", "");//removes letter in keyword from alphabet
		}			
		return alpha;
	}
	
	/*
	 * Name: initializeArray
	 * @parameter keyword - keyword entered by the user
	 * @parameter array - 5x5 array to hold all letters
	 * @parameter alpha - alphabet with letters from keyword removed
	 */
	public static void initializeArray(String keyword, String[][] array, String alpha){
		int i, j, k, l;
		k = 0;
		l = 0;	
	
		for(i = 0; i < array[0].length; i++)
		{
			for(j = 0; j < array[0].length; j++)
			{
				if(k < keyword.length())
				{
					array[i][j] = keyword.substring(k, k + 1); //inputs the letters of the word into the array	
					k++;
				} else {
					array[i][j] = alpha.substring(l, l +1);//inputs the rest of the letters of the alphabet
					l++;
				}
			}	
		}
	}
	
	/*
	 * Name: encode
	 * @parameter array - 5x5 initialized array
	 * @parameter message - message input by user for encryption
	 * @return encrypted message
	 */
	public static String encode(String[][] array, String message)
	{
		int k = 0;
		int i = 0, j = 0, di1 = 0, di2 = 0, di3 = 0, di4 = 0, x;
		String encoded = "";
		String digraph = "";
		String copy = message;
		
		if(message.length() % 2 != 0)
		{
			message = message.concat("X");//concats an X when message is odd in length
		}
		
		for(x = 0; x < copy.length(); x += 2)
		{
			digraph = message.substring(k,k+2);
			message = message.substring(k+2);
		
			if((digraph.charAt(0) == 'X') || (digraph.charAt(1) == 'X'))//rule x. rule 1
			{
				encoded += digraph + " ";
			} else
				if(digraph.charAt(0) == digraph.charAt(1))//rule same letter. rule 1
				{
					//checks if letters are the same.
					encoded += digraph + " ";
				} else {
					// this finds the indices of the letters in the array
					for(i = 0; i < array[0].length; i++)
					{
						for(j = 0; j < array[0].length; j++)
						{
							if(array[i][j].equals(digraph.charAt(ZERO) + ""))
							{
								di1 = i;//row
								di2 = j;//column
							}
						
							if(array[i][j].equals(digraph.charAt(ONE) + ""))
							{
								di3 = i; //row
								di4 = j; //column
							}
						}
					}	
					//splits the digraph to simplify replacing the letters
					String first = digraph.substring(ZERO, ONE);
					String second = digraph.substring(ONE);
				
				if(di1 == di3)//rule for same row. rule 2
				{
					first = array[di1][(di2 + 1) % 5]; 
					second = array[di3][(di4 + 1) % 5];		
				} else
					if(di2 == di4)//rule for same column. rule 3
					{
						first = array[(di1 + 1) % 5][di2];
						second = array[(di3 + 1) % 5][di4];
					} else {
						first = array[di1][di4];
						second = array[di3][di2];
					}
				encoded += first + second + " ";
			}		
		}
		return encoded;
	}
	
	/*
	 * Name: decode
	 * @parameter array - 5x5 initialized array
	 * @parameter message - message input by user for decryption
	 * @return - decrypted message 
	 */
	public static String decode(String[][] array, String message)
	{
		int k = 0;
		int i = 0, j = 0, di1 = 0, di2 = 0, di3 = 0, di4 = 0, x;
		String decoded = "";
		String digraph = "";
		String copy = message;
		
		if(message.length() % 2 != 0)
		{
			message = message.concat("X"); //adds an X to uneven messages
		}
		
		for(x = 0; x < copy.length(); x += 2)
		{	
			digraph = message.substring(k,k+2); //separates two letters from the message to be a digraph
			message = message.substring(k+2);

			if((digraph.charAt(ZERO) == 'X') || (digraph.charAt(ONE) == 'X'))//rule x. rule 1
			{
				decoded += digraph + " ";
			} else
				if(digraph.charAt(ZERO) == digraph.charAt(ONE))//rule same letter. rule 1
				{
					//checks if letters are the same. don't do anything
					decoded += digraph + " ";
				} else {				
				// this finds the indices of the letters in the array
				for(i = 0; i < array[ZERO].length; i++)
				{
					for(j = 0; j < array[ZERO].length; j++)
					{
						if(array[i][j].equals(digraph.charAt(ZERO) + ""))
						{
							di1 = i;//row
							di2 = j;//column
						}
						
						if(array[i][j].equals(digraph.charAt(ONE) + ""))
						{
							di3 = i; //row
							di4 = j; //column
						}
					}
				}
				String first = digraph.substring(ZERO, ONE);
				String second = digraph.substring(ONE);
				
				if(di1 == di3)//rule for same row. rule 2
				{
					if(di2 == ZERO)
					{
						first = array[di1][FOUR];
					} else {
						first = array[di1][di2 - 1];
					}
					
					if(di4 == ZERO)
					{
						second = array[di3][FOUR];
					} else {
						second = array[di3][di4 - 1];
					}					                  
					
				} else
					if(di2 == di4)//rule for same column. rule 3
					{
						if(di1 == ZERO)
						{
							first = array[FOUR][di2];
						} else {
							first = array[di1 - 1][di2];
						}
						
						if(di3 == ZERO)
						{
							second = array[FOUR][di4];
						} else {
							second = array[di3 - 1][di4];
						}	
					} else {
						first = array[di1][di4];
						second = array[di3][di2];
					}
				decoded += first + second + " ";	
			}
			
		}
		return decoded;
	}
}