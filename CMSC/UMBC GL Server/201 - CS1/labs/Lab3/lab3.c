/*****************************************************
 * File:        lab3.c
 * Created by:  Tim Murray
 * Created on:  Tuesday, February 15, 2005
 * Section:     0105, 0106, 0302, 0304
 * Email:       mtimoth1@umbc.edu
 *
 * Description: This program will compute the area
 *              of two triangles and display the
 *              output to the screen.  A greeting 
 *              message will also be displayed to 
 *              the user.
 *****************************************************/


#include <stdio.h>
#include "geometry.h"


/*-----------------Step 6---------------------*
 *      DON'T FORGET TO INCLUDE geometry.h         
 *--------------------------------------------*/

/*-----------------Step 1---------------------*
 * Type out the prototype for the PrintGreeting
 * function below
 *--------------------------------------------*/
void PrintGreeting();


int main (void)
{
   double area1 = 0.0, 
      area2 = 0.0;
   double height1 = 3.0,
      base1 = 2.0, 
      height2 = 5.0, 
      base2 = 3.0;
      
   /*-----------------Step 3---------------------*
    * Move the following 5 lines into your 
    * PrintGreeting function defintion below
    * and replace them with a call to PrintGreeting
    *--------------------------------------------*/
   PrintGreeting();
      
   /*-----------------Step 6---------------------*
    * Fill in the function definition for 
    * TriangleArea and replace the following
    * 2 lines with 2 calls to TriangleArea
    *--------------------------------------------*/
   

   area1 = TriangleArea(base1, height1); 
   area2 = TriangleArea(base2, height2);
   
   /* Display results */
   printf("The area of the first triangle with\n");
   printf("Height: %.1f and Base: %.1f is:\n    %.1f\n\n", 
	  height1, base1, area1);
   
   printf("The area of the second triangle with\n");
   printf("Height: %.1f and Base: %.1f is:\n    %.1f\n\n", 
	  height2, base2, area2);
   
   return 0;
}


/******************************************
 * PrintGreeting -
 * Displays a greeting to the user
 *
 * Output: none
 * Input:  none
 ******************************************/

/*-----------------Step 2---------------------*
 * Type out the definition for the PrintGreeting
 * function below
 *--------------------------------------------*/

void PrintGreeting()
{
   printf("Welcome to The Triangle Area Calculator!\n\n");
   printf("This program will compute the area of\n");
   printf("two different triangles based\n");
   printf("on the hard-coded values of base and height.\n");
   printf("Please look below for the results...\n\n");
}
