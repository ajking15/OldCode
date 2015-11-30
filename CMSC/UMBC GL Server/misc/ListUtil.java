package lab13;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class ListUtil {
  
  public static void populateList(List<Integer> list, String fileName)throws FileNotFoundException{
    System.out.println("Populate list from " + fileName);
    //Create a FileInputStream using file name provided and create a scanner object using the 
    //fileinputstream object created. Use scanner methods hasNextInt and nextInt to read integers
    //from file and add them into the list
    //start
    
    //end
  }
  
  public static void print(List<Integer> list){
    //print the list of integers using Iterators
    System.out.println("Print List");
    //start
    
    //end
    System.out.println("");
  }
  
  public static void remove(List<Integer> list, int value){
    System.out.println("Remove element " + value + " from list");
    //remove elements that match 'value' when iterating through the list
    //using iterator.remove method described below:
    // "Removes from the underlying collection the last element returned by the iterator (optional operation). This method 
    // can be called only once per call to next. The behavior of an iterator is unspecified if the underlying collection is  
    // modified while the iteration is in progress in any way other than by calling this method." (From JavaDocs)
    //start
    
    //end
  }
  
  public static void sort(List<Integer> list){
    System.out.println("Sort List");
    //sort the list using collections. No changes required here. This method is for 
    //your information
    
    Collections.sort(list);
    
  }
  
}
