/***************************************************** 
 * File: lab5.c
 * Created By: Dalibor Zeleny
 * Created on: March 4, 2007
 * Email: zelenyd1@umbc.edu
 * Section: All
 * 
 * Description: This file contains the driver for lab 5
 ******************************************************/

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#include "util.h"

#define ARRAY_LENGTH    10
#define MIN              0
#define MAX             99


int main() 
{
   int intArray[ARRAY_LENGTH];

   /* Seed the random number generator */
   srand(time(NULL));

   /*********************
   ** STEP 2b
   ** Uncomment the block of code below. Make sure you compile and run
   **   after you uncomment the code.
   **********************/

   /* Fills the array with random numbers and prints it out */
   
   FillArray(intArray, ARRAY_LENGTH, MIN, MAX);
   printf("Unsorted array\n");
   PrintArray(intArray, ARRAY_LENGTH);
   
   
   /*********************
   ** STEP 3b
   ** Uncomment the block of code below. Make sure you compile and run
   **   after you uncomment the code.
   **********************/

   /* Sorts the array and prints it out */
   
   InsertionSort(intArray, ARRAY_LENGTH);
   printf("Sorted array\n");
   PrintArray(intArray, ARRAY_LENGTH);
   

   return 0;
}

