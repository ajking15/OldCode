/******************************
** Filename: lab7.c
** Name: Robert Tand
** Section: TA
** Created on: March 10, 2004
** email: rtand1@cs.umbc.edu
*******************************/

#include <stdio.h>
#include "pointers.h"

int main()
{
   int x1, x2, y1, y2;
   double totalInches = 0;
   int distanceInFeet = 0;
   double distanceInInches = 0;
   
   
   /*************************************
    *               Step 2              *
    * Add two calls to ScanCoordinates, *
    * one for each pair of coordinates. *
    * Also, uncomment the two lines of  *
    * code below before compiling again *
    *************************************/
   ScanCoordinates( &x1, &y1);
   ScanCoordinates( &x2, &y2);
   
   totalInches = CalculateDistance(x1, y1, x2, y2);
   printf("The distance is: %lf\n", totalInches);
   
   
   
   /************************************
    *          Step 4                  *
    * Call the ConvertInchesToFeet     *
    * function, making sure to pay     *
    * attention to what is passed in   *
    * by reference, and what is passed *
    * in by value. Also uncomment the  *
    * call to printf below.            *
    ************************************/
   ConvertInchesToFeet(&distanceInFeet, &distanceInInches, totalInches);
  
   
   printf("Feet: %d, Inches: %lf\n", distanceInFeet, 
	  distanceInInches);
   
   
   return 0;
}
