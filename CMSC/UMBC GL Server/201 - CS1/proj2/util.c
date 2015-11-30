/******************************************************************************
 ** File:               util.c
 ** Author:             Christopher Mai
 ** Date:               10/07/07
 ** Section:            0203
 ** E-mail:             chrmai1@umbc.edu
 **
 **   This File contains general purpose functions that are not program 
 ** specific and will be used again and again as it gets bigger
 **
 *****************************************************************************/

#include <stdio.h>
#include "battle.h"

/******************************************************************************
 ** GetValidInt --
 **  This function ensures that a value taken in by the user falls within a 
 ** specific range.
 **   Input:  min and max for the range
 **   Output: int value withing the range
 *****************************************************************************/

int GetValidInt(int min, int max)
{
   /* is set greater than max so the loop will be entered */
   int input = max + 1;
 
   /* Loop assures a valid entry */
   while( input < min || input > max)
   {
      printf("Please enter an integer between");
      printf(" %d and %d : ", min, max);
      scanf("%d", &input);
   }
   
   return input;
}
