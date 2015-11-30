/************************************
 * File:          proj1.c
 * Author:        Christopher Mai
 * Date:          9/26/07
 * Section:       0203
 * E-mail:        chrmai1@umbc.edu
 *
 * This file recieves a positive 
 * value from the user then allows
 * the user to choose from various
 * mathematical calculations to 
 * choose from and two other options
 * from choose a new number to quit
 *
 * all function prototypes and 
 * functions are located in seperate
 * files.
 ************************************/

#include <stdio.h>
#include <math.h>
#include "exponents.h" /*includes function prototypes*/

/*helps readiblity and used for GetValidInt*/
#define MIN          1
#define MAX          7

/*menu selections*/
#define SQUARE       1
#define CUBE         2
#define APPROX_SQRT  3
#define SQRT         4
#define DIFFERENCE   5
#define NEW_NUM      6
#define QUIT         7

int main()
{
   double value, newValue;
   int    choice;

   /*prints greetng*/
   PrintGreeting();

   /*makes sure value given by the user is positive*/
   value = GetPositiveValue();

   /*do while has the loop perform at least one and repeat the switch
    *until the user chooses to end the program                      */
   do
   {
      /*print menu inside the loop so it comes up each time before you make
       *your selection                                                    */
      PrintMenu();
      choice = GetValidInt(MIN, MAX);
      
      /*switch used to create a menu interface*/
      switch(choice)
      {
	 case SQUARE:
	    newValue = FindSquare(value);
	    printf("%f squared is %f\n\n", value, newValue);
	    break;
	 case CUBE:
	    newValue = FindCube(value);
	    printf("%f cubed is %f\n\n", value, newValue);
	    break;
	 case APPROX_SQRT:
	    newValue = FindApproxSqRt(value);
	    printf("The approximate root of %f is  %f\n\n", value, newValue);
	    break;
	 case SQRT:
	    newValue = sqrt(value);
	    printf("The precise root of %f is %f\n\n", value, newValue);
            break;
         case DIFFERENCE:
            newValue = FindDifference(value);
            printf("The difference between the approximate root\n");
	    printf(" and the precise root is %e\n\n", newValue);
            break;
         case NEW_NUM:
            value = GetPositiveValue();
            break;
         case QUIT:
            break;
         default:
            printf("Dios Mios!!, esta numero no existan!!!");
            break;
      }
   } while(choice != QUIT);

  return 0;
}
	    

