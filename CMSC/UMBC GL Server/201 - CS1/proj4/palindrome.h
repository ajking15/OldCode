/******************************************************************************
 ** File:         palindrome.h
 ** Author:       Christopher Mai
 ** Date:         11/14/07
 ** Section:      0203
 ** E-mail:       chrmai1@umbc.edu
 **
 **  This file holds all #defines, function prototypes, and also #includes
 *****************************************************************************/
#include <stdio.h>
#include <stdlib.h>

#define TRUE  1
#define FALSE 0
#define LEFT  0

typedef struct lychrel
{
      int num;
}LYCHREL;

/*****************************************************************************
 ** PrintGreeting --
 **  Prints out a message to greet the user
 **   Input:  void
 **   Output: void
 *****************************************************************************/

void PrintGreeting();

/*****************************************************************************
 ** PrintExplanation --
 **  Prints out the explanation of what this program does
 **   Input:  void
 **   Output: void
 *****************************************************************************/

void PrintExplanation();

/****************************************************************************
 ** OpenLychrelFile --
 **  opens the lychrel.dat file to read information
 **   Input:  number of lychrels pointer and a pointer to the filename
 **   Output: incoming file pointer
 *****************************************************************************/

FILE* OpenLychrelFile(int *numLychrelPtr, char *fileName);

/******************************************************************************
 ** GetMemory --
 **  Gets the memory to hold the array of lychrels
 **   Input:  number of lychrels and the array
 **   Output: the array
 *****************************************************************************/

LYCHREL* GetMemory(int numLychrel, LYCHREL *lychrel);

/*****************************************************************************
 ** ScanLychrel --
 **  Scans all lychrels into an array
 **   Input:  ifp pointer, number of lychrels and lychrel array
 **   Output: array of lychrels
 ****************************************************************************/

void ScanLychrel(FILE* ifp, int numLychrel, LYCHREL *lychrel);

/*****************************************************************************
 ** CheckLychrel --
 **  Compares random long long with array of lychrels
 **   Input:  array of lychrels, the random number, and the number of lychrels
 **   Output: true or false
 *****************************************************************************/

int CheckLychrel(LYCHREL *lychrel, int randNum, int numLychrel);

/*****************************************************************************
 ** NumToReversedString --
 **  takes in a number and converts it into a string reversed
 **   Input:  the random number and a string to convert
 **   Output: the reversed string
 ****************************************************************************/

int NumToReversedString(long long randNum, char string[]);

/*****************************************************************************
 ** IsPalindrome --
 **  Checks random number if it is a palindrome
 **   Input:  random number in a string and indices of beginning and end of 
 **           string
 **   Output: true or false
 *****************************************************************************/

int IsPalindrome(char string[], int left, int right);

/*****************************************************************************
 ** ReverseAndAdd --
 **  Reverses nummber and adds it to original 
 **   Input:  random number, and pointer to keep track of iterations
 **   Output: void
 *****************************************************************************/

void ReverseAndAdd(long long num, int *timesPtr);
