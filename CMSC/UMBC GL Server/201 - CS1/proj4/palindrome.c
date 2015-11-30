/******************************************************************************
 ** File:         palindrome.c
 ** Author:       Christopher Mai
 ** Date:         11/14/07
 ** Section:      0203
 ** E-mail:       chrmai1@umbc.edu
 **
 **  This file holds all function definitions necessary to run proj4.c
 ** this file needs to be run with palindrome.h
 *****************************************************************************/

#include "palindrome.h"
#include "stringtoll.h"

/*****************************************************************************
 ** PrintGreeting --
 **  Prints out a message to greet the user
 **   Input:  void
 **   Output: void
 *****************************************************************************/

void PrintGreeting()
{
   printf("   Hello, this is a program written for computer science 201.\n");
   printf("This will test several new topics learnt in class.\n");
   printf("Namely recursion\n");
}

/*****************************************************************************
 ** PrintExplanation --
 **  Prints out the explanation of what this program does
 **   Input:  void
 **   Output: void
 *****************************************************************************/

void PrintExplanation()
{
   printf("\n   Using recursion this program will determine numerical\n");
   printf("palindromes which are numbers that are the same when written\n");
   printf("both backward and forward. However, if they are not palindromes\n");
   printf("the numbers will be reveresed and added until they are.\n");
   printf("Lychrels will be ignored.\n");
}

/****************************************************************************
 ** OpenLychrelFile --
 **  opens the lychrel.dat file to read information
 **   Input:  number of lychrel pointer and a pointer to the filename
 **   Output: incoming file pointer
 *****************************************************************************/

FILE* OpenLychrelFile(int *numLychrel, char *fileName)
{
   FILE *ifp;
   int x;
 
   /* Open the document to the program */
   ifp = fopen(fileName, "r");

   /* Check to see if file opened correctly */
   if(ifp == NULL)
   {
      /* print message to standard error */
      fprintf(stderr, "Error: file %s failed to open.\n", fileName);

      exit(-2);
   }
   
   /* scans until end of file */
   while(fscanf(ifp, "%d", &x) != EOF)
   {
      (*numLychrel)++;
   }

   return ifp;
}

/******************************************************************************
 ** GetMemory --
 **  Gets the memory to hold the array of lychrels
 **   Input:  number of lychrels and the array
 **   Output: the array
 *****************************************************************************/

LYCHREL* GetMemory(int numLychrel, LYCHREL *lychrel)
{
   /* Get a block of memory big enough to hold all the lychrels */
   lychrel = (LYCHREL *) calloc(numLychrel, sizeof(LYCHREL));

   /* Check to make sure memory is available */
   if(lychrel == NULL)
   {
      /* print message to standard error */
      fprintf(stderr, "Error not enough memory\n");

      exit(-3);
   }

   return lychrel;

}

/*****************************************************************************
 ** ScanLychrel --
 **  Scans all lychrels into an array
 **   Input:  pointer of ifp number of lychrels and lychrel arrray
 **   Output: void
 ****************************************************************************/

void ScanLychrel(FILE* ifp,int numLychrel, LYCHREL *lychrel)
{
   int i = 0;
   /* rewinds the file so lychrels can be scanned */
   rewind(ifp);

   /* loop to absorb each lychrel into the array */
   for(i = 0; i < numLychrel; i++)
   {
      fscanf(ifp, "%d", &lychrel[i].num);
   }

   /* Closes the file after all information has been absorbed */
   fclose(ifp);

}

/*****************************************************************************
 ** CheckLychrel --
 **  Compares random long long with array of lychrels
 **   Input:  array of lychrels, the random number, and the number of lychrels
 **   Output: true or false
 *****************************************************************************/

int CheckLychrel(LYCHREL *lychrel, int randNum, int numLychrel)
{
   int i;

   for(i = 0; i < numLychrel; i++)
   {
      if(randNum == lychrel[i].num)
      {
	 return TRUE;
      }
   }

   return FALSE;

}

/*****************************************************************************
 ** NumToReversedString --
 **  takes in a number and converts it into a string reversed
 **   Input:  the random number and a string to convert
 **   Output: the reversed string
 ****************************************************************************/

int NumToReversedString(long long randNum, char string[])
{  
   int strippedInt, counter = 0;

   while(randNum != 0)
   {
      /* picks out the int in the ones place and shortens the int */
      strippedInt = randNum % 10;
      randNum = randNum / 10;
      
      /* make into type char */
      strippedInt = (char)strippedInt;
      
      /* modifies ascii value back into a number */
      strippedInt += MODIFIER;

      /* adds stripped int on to end of string */
      string[counter] = strippedInt;

      counter++;
   }

   /* adds null terminator to end of string */
   string[counter] = '\0';

   return counter;

}

/*****************************************************************************
 ** IsPalindrome --
 **  Checks random number if it is a palindrome
 **   Input:  random number in a string and indices of beginning and end of 
 **           string
 **   Output: true or false
 *****************************************************************************/

int IsPalindrome(char string[], int left, int right)
{
   /* checks for end of string */
   if(right < left)
   {
      /* if it meets the above condition it will have been a palindrome */
      return TRUE;
   } else
      if(string[right - 1] == string[left]) /* checks for equivalty */
      {
	 /* calls is palindrome again with more interior coordinates */
	 return IsPalindrome(string, left + 1, right - 1);
      } else { 
	 /* otherwise function is false */
	 return FALSE;
      }
   
}

/*****************************************************************************
 ** ReverseAndAdd --
 **  Reverses nummber and adds it to original 
 **   Input:  random number, and pointer to keep track of iterations
 **   Output: void
 *****************************************************************************/

void ReverseAndAdd(long long num, int *timesPtr)
{
   long long numReversed = 0, numTemp = num, added;
   int temp, length;
   char string[16];

   /* Reverses the number */
   while(numTemp != 0)
   {
   temp = numTemp % 10;
   numTemp = numTemp / 10;

   numReversed = numReversed * 10 + temp;
   } 

   added = num + numReversed; 
   
   printf("%lld + %lld = %lld\n", num, numReversed, added);

   /* converts number back to string so it can be sent to IsPalindrome */
   length = NumToReversedString(added,  string);
 
   /* checks for palindrome */
   if(IsPalindrome(string, LEFT, length) == FALSE)
   {
      /* record number of iterations of Reverse and add */ 
      (*timesPtr)++;
      ReverseAndAdd(added, timesPtr);
   }
   
}
