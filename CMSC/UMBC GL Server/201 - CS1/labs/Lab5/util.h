/***************************************************** 
 * File: util.h
 * Created By: Dalibor Zeleny
 * Created on: March 4, 2007
 * Email: zelenyd1@umbc.edu
 * Section: All
 * 
 * Description: This file contains some functions suitable for general use
 ******************************************************/


/*************************************************
 * GetRandomInt -
 * Returns a random integer from the given range
 *
 * Output: int - random number between min and max
 * Input:  int min - the lower bound for the number that should be returned
 *         int max - the upper bound for the number that should be returned
 *************************************************/
int GetRandomInt(int min, int max);


/*************************************************
 * FillArray -
 * Fills an array of given length with random numbers within
 *   given bounds
 *
 * Output: no output
 * Input:  int array[] - the array to fill with random numbers
 *         int length - size of the array being sorted
 *         int min - smallest number that can be put in the array
 *         int max - largest number that can be put in the array
 * Side effects: The array is filled with random numbers between
 *                 min and max
 *************************************************/
void FillArray(int intArray[], int length, int min, int max);


/*************************************************
 * PrintArray -
 * Prints out an array of a given length
 *
 * Output: no output
 * Input:  int array[] - the array to sort
 *         int length - size of the array being sorted
 *************************************************/
void PrintArray(int intArray[], int length);


/*************************************************
 * InsertionSort -
 * Sorts an array if a given length
 *
 * Output: no output
 * Input:  int array[] - the array to sort
 *         int length - size of the array being sorted
 * Side effects: The array is sorted in ascending order
 *************************************************/
void InsertionSort(int intArray[], int length);
