package lab13;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Lab13 {
  
  public static final String FILE_NAME = "input.txt";
  
  public static void main(String [] args){
    
    List<Integer> list;
    
    //First assign ArrayList Object to list and run the program
    list = new ArrayList<Integer>();
    
    //Second assign LinkedList Object to list and run the program
        
    try {
      ListUtil.populateList(list, FILE_NAME);
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return;
    }
    ListUtil.print(list);
    ListUtil.remove(list, 10);
    ListUtil.print(list);
    ListUtil.sort(list);
    ListUtil.print(list);
    
    
    
  }
   

}
