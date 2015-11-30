/*****************************************************************************
 ** File:      proj4.c
 ** Author:    Christopher Mai
 ** Date:      11/14/07
 ** Section:   0203
 ** E-mail:    chrmai1@umbc.edu
 **
 **  This is the main file for project 4. This program makes use of different
 ** command line arguments, pointers, arrays, and requires opening files.
 ** In addition to using stdio this program also uses stdlib for outside files
 ****************************************************************************/

#include "palindrome.h"
#include "stringtoll.h"

int main(int argc, char *argv[])
{
   FILE  *ifp;
   int randNum, length, i;
   int numLychrel = 0, timesPtr = 0, times = 0;
   char *fileName = argv[3], string[16];
   LYCHREL *lychrel;
   
   /* checks if it is the correct number of arguments */
   if(argc != 4)
   {
      /* print message to standard error */
      fprintf(stderr, "incorrect number of arguments.\n");
      fprintf(stderr, "enter a.out then seed number, then number of \n");
      fprintf(stderr, "repititons, then input data file\n");
      
      exit(-1);
   }
   
   /* checks to see that seed value is positive */
   if(argv[1] < 0)
   {
      /* prints message to standard error */
      fprintf(stderr, "arugment 2 cannot be negative input positive\n");

      exit(-2);
   }

   /* checks to see that number of repititions is positive */
   if(argv[2] <= 0)
   {
      /* prints a message to standard error */
      fprintf(stderr, "argument 3 cannot be negarive input positive\n");
   }
   
   /* Greets the user */
   PrintGreeting();
   
   /* Explains the program to the user */
   PrintExplanation();

   /* open the lychrels file and count number of lychrels */
   ifp = OpenLychrelFile(&numLychrel, fileName);

   /* Gets the memory required to hold the array of lychrels */
   lychrel = GetMemory(numLychrel, lychrel); 
   
   /* Scans lychrels into array */
   ScanLychrel(ifp, numLychrel, lychrel);   

   /* seed random number generator */
   srand(atoi(argv[1]));

   /* repeats program until reptition equals third cmd line argument */
   while(times < atoi(argv[2]))
   {
      timesPtr = 0;
      randNum = rand() % 2000 + 1;
      
      /* checks to see if the random number is a lychrel */
      if(CheckLychrel(lychrel, randNum, numLychrel) == TRUE)
      {
	 printf("\nnum = %d\n", lychrel[i].num);
         printf("Cannot be solved %d is a lychrel\n", lychrel[i].num);
      } else {
	 printf("\nnum = %d\n", randNum);
	 
	 /* converts random number to string */
	 length = NumToReversedString(randNum, string);
	 
	 /* checks number if it is a palindrome */
	 i = IsPalindrome(string, 0, length);
	 
	 /* reverses and adds if IsPalindrome returns false */
	 if(i == FALSE)
	 {  
	    timesPtr++;
	    
	    /* reverses and adds num recursively until palindrome */
	    ReverseAndAdd(randNum, &timesPtr); 
	 }
	 
	 printf("This problem was solved in %d iterations\n", timesPtr);
	 
      }
      
      /* increment to end while loop */
      times++;
      
   }
   
   /* return allocated memory */
   free(lychrel);

   return 0;
} 
 
