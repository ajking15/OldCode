/*****************************************************
 * File:        lab4.c
 * Created by:  Anubhav Kale
 * Modified by: Corey Weidenhammer
 * Created on:  Saturday, September 24, 2005
 * Modified on: Sunday, February 26, 2006
 * Section:     All
 * Email:       corey1@cs.umbc.edu
 *
 * Description: This program will compute the perimeter
 *              and area of the number of rectangles 
 *              defined by NUMRECTANGLES. It also prints 
 *              a Welcome message to user.
 *****************************************************/


#include <stdio.h>
#include "geometry.h"

/****************** STEP 3  ***********************
 * Include geoemtry.h so that we can use its      *
 * geometry related functions.                    *
 *************************************************/



/****************** STEP 6 ************************
 *   Change the value of NUMRECTANGLES            *
 *************************************************/
#define NUMRECTANGLES 1000

#define LENGTH        0
#define WIDTH         1
#define PERIMETER     2
#define AREA          3
#define NUMATTRIBUTES 4



/************************************
 * PrintGreeting -
 * Displays a greeting to the user
 *
 * Output: none
 * Input:  none
 ************************************/ 
void PrintGreeting(void);


/************************************
 * PrintTableHeader -
 * Displays the heading for a table that
 * will show the properties of each
 * rectangle.
 *
 * Output: none
 * Input:  none
 ************************************/ 
void PrintTableHeader(void);


/************************************
 * PrintRectangleTable -
 * Prints each line of a table that 
 * describes the length, width, 
 * perimeter and area of each 
 * rectangle in the array passed in.
 *
 * Output: none
 * Input:  a 2-dimensional array of integers,
 *         the size of the first dimension of 
 *         the array (i.e. the number of rectangles)
 ************************************/ 
void PrintRectangleTable( int rectangles[][NUMATTRIBUTES], int numRectangles );



int main (void)
{
   
   int length = 0;
   int width  = 0;     
   int i      = 0;     
   int rectangleList[NUMRECTANGLES][NUMATTRIBUTES];
   

   PrintGreeting();

   /* Get input from user */

   printf("Please enter the length and width of ");
   printf("%d", NUMRECTANGLES);
   printf(" rectangles\n");
   printf("in the form of length, width (number comma number)\n");
   printf("- one pair on each line\n\n");
   
   /* Read in the length and width values for each rectangle */
   /* Store each value in an element of a 2-dimensional array */
   
   for (i = 0; i < NUMRECTANGLES; i++)
   {
      /* Read length and width and store in array  */
      scanf ("%d,%d", &length, &width);

      rectangleList[i][LENGTH] = length;
      rectangleList[i][WIDTH] = width;


      /******************** STEP 3  ***********************
       * Write a couple lines of code to calculate the    *
       * perimeter and area of the current rectangle,     *
       * and store the perimeter and area in elements 2   *
       * and 3 of the second dimension of the array.      *
       *                                                  *
       * Use the CalculatePerimeter and CalculateArea     *
       * functions from geometry.h                        *
       ***************************************************/
      rectangleList[i][PERIMETER] = CalculatePerimeter(length, width);
      rectangleList[i][AREA] = CalculateArea(length, width);

   }
    
   printf("\nThe properties of the rectangle(s) are:\n\n");

   /* The header for a results table */
   PrintTableHeader();

   /* Print out the length, width, perimeter and area of each rectangle */
   /* Print one per line */

   /******************* STEP 2 ***************************
    * Uncomment the following statement to call the      *
    * function which actually print out a table based on *
    * the values in our array.                           *
    *                                                    *
    * Note that we are PASSING the array here!           *
    *****************************************************/
   
   
     PrintRectangleTable( rectangleList, NUMRECTANGLES );
   
   
   printf("\n");

   return 0;
}


/************************************
 * PrintGreeting -
 * Displays a greeting to the user
 *
 * Output: none
 * Input:  none
 ************************************/

void PrintGreeting(void)
{
   printf("\n");
   printf("          Welcome to the Rectangle Wizard           \n");
   printf("  This program will compute the area and perimeter  \n");
   printf("       of rectangles from the length and width      \n");
   printf("            of each, as per your input.             \n\n");
   
}


/************************************
 * PrintTableHeader -
 * Displays the heading for a table that
 * will show the properties of each
 * rectangle.
 *
 * Output: none
 * Input:  none
 ************************************/ 
void PrintTableHeader( void )
{
   int numDashes = 60;
   int i = 0;

   printf("%11s %11s %11s %11s %11s\n",
	  "Rectangle #",
	  "Length",
	  "Width",
	  "Perimeter",
	  "Area");

   for( i = 0; i < numDashes; i++ )
   {
      printf("-");
   }
   
   printf("\n");

}


/************************************
 * PrintRectangleTable -
 * Prints each line of a table that 
 * describes the length, width, 
 * perimeter and area of each 
 * rectangle in the array passed in.
 *
 * Output: none
 * Input:  a 2-dimensional array of integers,
 *         the size of the first dimension of 
 *         the array (i.e. the number of rectangles)
 ************************************/ 
void PrintRectangleTable( int rectangles[][4], int numRectangles )
{
   int i = 0;
   
   for(i = 0; i < numRectangles; i++)
   {
      printf("%11d %11d %11d %11d %11d\n", i+1, 
	     rectangles[i][LENGTH], 
	     rectangles[i][WIDTH], 
	     rectangles[i][PERIMETER],
	     rectangles[i][AREA]);
   }
}
