/* 
 * Filename: errors.c 
 * Author: Sue Bogar 
 * Date written: 9/18/99 
 * Section #: 101 
 * Email: bogar@cs.umbc.edu 
 * 
 * This file contains buggy code and is to be 
 * used as an exercise in the CMSC 201 extra 
 * lab session # 1. Errors are to be found and 
 * fixed one at a time, just as you would fix 
 * your errors in a real project. 
 */

#include <stdio.h>  

int main ( ) 
{ 
   int num1, num2; 
   float average; 
   
   /* Print the greeting and prompt the user for 2 ints */ 
   printf ("This program finds the average of two integers.\n"); 
   printf ("Please enter the first integer: "); 
   scanf ("%d", &num1); 
   printf ("Please enter the second integer: "); 
   scanf ("%d", &num2); 

   /* Calculate the average */ 
   average = (num1 + num2)/2.0; 

   /* Print the average to 3 decimal places */ 
   printf ("The average of %d and %d is %.3f\n", num1, num2, average); 

   return 0;

}
