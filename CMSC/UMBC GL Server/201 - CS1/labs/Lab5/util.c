/***************************************************** 
 * File: util.c
 * Created By: Dalibor Zeleny
 * Created on: March 4, 2007
 * Email: zelenyd1@umbc.edu
 * Section: All
 * 
 * Description: This file contains some functions suitable for general use
 ******************************************************/

#include <stdio.h>
#include <stdlib.h>

#include "util.h"


/*************************************************
 * GetRandomInt -
 * Returns a random integer from the given range
 *
 * Output: int - random number between min and max
 * Input:  int min - the lower bound for the number that should be returned
 *         int max - the upper bound for the number that should be returned
 *************************************************/
int GetRandomInt(int min, int max) 
{
   /*********************
   ** STEP 1
   ** Implement this function. See the section on random numbers in the notes
   **   for this lab if you need a hint. But try yourself first.
   **********************/
  
   return rand() % (max - min + 1);
}



/*************************************************
 * FillArray -
 * Fills an array with random numbers
 *
 * Output: no output
 * Input:  int array[] - the array to fill with random numbers
 *         int length - size of the array being sorted
 *         int min - smallest number that can be put in the array
 *         int max - largest number that can be put in the array
 * Side effects: The array is filled with random numbers between
 *                 min and max
 *************************************************/
void FillArray(int intArray[], int length, int min, int max) 
{
   /*********************
   ** STEP 2a
   ** Implement this function. It should fill the array 'intArray' (that has
   **   length 'length' with random integers between 'min' and 'max'.
   **********************/
   int i;

   for(i = 0; i < length; i++)
   {
      intArray[i] = GetRandomInt(min, max);
   }
}



/*************************************************
 * PrintArray -
 * Prints out an array of a given length
 *
 * Output: no output
 * Input:  int array[] - the array to sort
 *         int length - size of the array being sorted
 *************************************************/
void PrintArray(int intArray[], int length) 
{
   int i;

   printf("Printing array...\n");
   printf("-----------------\n");
   for (i = 0; i < length; i++) 
   {
      printf("%7d", intArray[i]);
      if (i % 10 == 9) 
      {
	 printf("\n");
      }
   }
   printf("\n\n");
}



/*************************************************
 * InsertionSort -
 * Sorts an array if a given length
 *
 * Output: no output
 * Input:  int array[] - the array to sort
 *         int length - size of the array being sorted
 * Side effects: The array is sorted in ascending order
 *************************************************/
void InsertionSort(int intArray[], int length)
{
   /*********************
   ** STEP 3a
   ** Implement this function. It should sort the elements of the array 
   **   'intArray' (that has length 'length') in ascending order. Refer to
   **   the slides for pseudocode for InsertionSort and look at the graphic
   **   if you need additional help.
   **********************/
   int i, j;
   int temp;

   /* Following the Pseudocode given in the "Insertion Sort" page */
   /* 1. We are already in InsertionSort()  */
   /* 2. i starts at 1(not 0), which sets the length of the sorted part to 1 */
   /* 3. for loop :  Takes care of the "do while (the sorted part ... )" */
   /* 8. i++ : Increase the length of the sorted part by 1 */
   for (i = 1; i < length; i++)
   {
     /*****************************************************
     * 4. WRITE YOUR CODE HERE: 
     * Take the first element, E, from the unsorted part and
     * store it as temp 
     ****************************************************/
      temp = intArray[i];

      /* 5. Take elements in the sorted part that are bigger than E(temp) */
      /* 6a. Move them "one slot to the right" in the array */
      j = i;
      while (j > 0 && intArray[j - 1] > temp) 
      { 
	 intArray[j] = intArray[j - 1];
	 /************************************************************
	 * 6b. WRITE YOUR CODE HERE:  Make sure the loop terminates 
	 ************************************************************/
	 j--;
      }

      /*******************************************************************
      * 7. WRITE YOUR CODE HERE: Put E in the space that has been freed 
      *******************************************************************/
      intArray[j] = temp;
   }
}
