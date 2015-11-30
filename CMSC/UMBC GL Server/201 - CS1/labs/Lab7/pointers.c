/******************************
** Filename: pointers.c
** Name: Robert Tand
** Section: TA
** Created on: March 10, 2004
** email: rtand1@cs.umbc.edu
*******************************/

#include "pointers.h"


/***************************************************************************
 * Function: ScanCoordinates
 * input(s): a pointer to an int "x", and another pointer to an int "y"
 * output(s): None
 *
 * The purpose of this function is to scan in a coordinate (both and x and y
 * parts) and to change their values in main via the use of pointers.
 ***************************************************************************/
void ScanCoordinates(int* xPtr, int* yPtr)
{

   /**************************************
    *             Step 1                 * 
    * Write the code to prompt the user  *
    * for a pair of x and y coordinates. *
    * Use scanf, but be careful about    *
    * using (or not using) the ampersand *
    **************************************/
   printf("enter a x coordinate: ");
   scanf("%d", xPtr);
   printf("enter a y coordinate: ");
   scanf("%d", yPtr);
}


/***************************************************************************
 * Function: ConvertInchesToFeet
 * input(s): a pointer to an int "feet", a pointer to double "inches" and a
 *           double which contains the total number of inches. 
 * output(s): None
 *
 * The purpose of this function is to take the double variable "totalInches"
 * and modify the pointers "feet" and "inches" so that they are effectively
 * returned to main
 ***************************************************************************/
void ConvertInchesToFeet(int* feetPtr, double* inchesPtr, double totalInches)
{

   /****************************************
    *             Step 3                   *
    * Write the code to convert total      *
    * inches into feet and inches. Hint:   *
    * there are 12 inches in a foot. :)    *
    * Be careful about how you reference   *
    * the values of the pointer variables! *
    ****************************************/
   *feetPtr = totalInches / 12;
   *inchesPtr = totalInches - *feetPtr * 12;
}


/***************************************************************************
 * Function: CalculateDistance
 * input(s): two sets of (x,y) coordinates
 * output(s): a double containing the distance between those two pointers
 *   It uses this formula:
 *     distance = SquareRoot( ((x2 - x1)^2) + ((y2 - y1)^2))
 ***************************************************************************/
double CalculateDistance(int x1, int y1, int x2, int y2 )
{
   return (sqrt( ((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1)) ));
}
