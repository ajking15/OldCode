package proj4;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
* File:    HashTable.java
* Project: CMSC 341 Project 4, Fall 2008
* @author  Chris Mai
* Date:    11/23/08
* Section: 0201
* E-mail:  chrmai1@umbc.edu
* Class Invariant
*	1. HashTable is generic
*/

public class HashTable<K> {
	private int  prime;	
	private int numElements;
    private List<Hash<K>>[] hashTable;     //hash will be a wrapper class
	
	HashTable(int prime){
		//initialize a generic array
		this.prime = prime;
		hashTable = (List<Hash<K>>[]) new ArrayList[this.prime];
		//add a list to each and every array spot to avoid null ptr exception
		for(int x = 0; x < hashTable.length; x++)
		{
			hashTable[x] = new ArrayList<Hash<K>>();
		}
		
	}
	
	/**
	 * 
	 * @param hashList  a list of hashs that need to be added to the table
	 * @param condition wether or not the boards won or lost
	 */
	public void addHashs(List<Hash<K>> hashList, String condition){
		boolean added = false;
        //hashList should be a list of boards to add to the array
		Iterator<Hash<K>> iter = hashList.iterator();
		//iter is traversing hashList
		//iterate over the list of hashs
		while(iter.hasNext())
		{
			Hash<K> temp = iter.next();
			//check position in the hash table
			if(hashTable[temp.hashCode() % prime].isEmpty())
			{
				//if empty update current goodness
				temp.updateGoodness(condition);
				//add node
				hashTable[temp.hashCode() % prime].add(temp);
				added = true;
			} else {
				//i need to travese hashTable and compare to temp
				Iterator<Hash<K>> ator = hashTable[temp.hashCode() % prime].iterator();
				//otherwise iterate over the nodes in that position
				while(ator.hasNext())
				{
					Hash<K> orary = ator.next();
					//compare orary to temp 
					//if(orary.getKey().equals(temp.getKey()))
					//compare their hashCodes
					if(orary.getKey().hashCode() == temp.getKey().hashCode())
					{
						//if equal update goodness
						orary.updateGoodness(condition);
						added = true;
					}
				}
			}
			//otherwise node not found so add it
			if(added == false)
			{
				//update condition
				temp.updateGoodness(condition);
				//add to hash table
				hashTable[temp.hashCode() % prime].add(temp);
			}
		}
	}
	
	/**
	 * 
	 * @param key sends in an onject of type hash with a board
	 *            to retrieve it's goodness rating
	 */
	public void hash(Hash<K> key){
		//which retrieves hashValue
		int hashKey = key.hashCode();
		//find position in the array
		hashKey %= prime;
		//check if empty
		if(hashTable[hashKey].isEmpty())
		{
			//goodness stays at default 1
		} else {
			//check if it's in the list
			Iterator<Hash<K>> iter = hashTable[hashKey].iterator();
			while(iter.hasNext())
			{
				//iterate over the positions list
				Hash<K> temp = iter.next();
				if(key.hashCode() == temp.hashCode())
				{
					//if found update goodness. otherwise left as default
					key.setGoodness(temp.getGoodness());
				}
			}
		}
	}
	
	/**
	 * 
	 * @param printer printwriter object used to print hashtable stats to a file
	 */
	public void stats(PrintWriter printer){
		numElements = 0;
		int percentFull = 0;
		int numCollisions = 0;
		//iterates over the table
		for(int x = 0; x < prime; x++)
		{
			//if data is found
			if(hashTable[x].isEmpty() == false)
			{
				//update percent full
				percentFull++;
				//increase numElements by hashs in that position
				numElements += hashTable[x].size();
				//calculate collisions by num elements - 1
				numCollisions += hashTable[x].size() - 1;
			}
		}
		//num collisions
		//print to file
		printer.println("Num Elements: " + numElements);
		printer.println("Percent Full: " + percentFull + "/" + prime);
		printer.println("Total Collisions: " + numCollisions);
//		System.out.println("Num Elements: " + numElements);
//		System.out.println("Percent Full: " + percentFull + "/" + prime);
//		System.out.println("Total Collisions: " + numCollisions);
	}

}
