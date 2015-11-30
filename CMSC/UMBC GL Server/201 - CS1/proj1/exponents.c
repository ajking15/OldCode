



/*********************************************
 * File:     exponents.c
 * Author:   Christopher Mai
 * Date:     9/26/07
 * Section:  0203
 * E-mail:   chrmai1@umbc.edu
 *
 * This file contains the function prototypes
 * for the functions found in proj1.c
 *********************************************/

#include <stdio.h>
#include <math.h>
#include "exponents.h"

/*********************************************
 * Function: PrintGreeting
 *
 * This function prints a greeting to be 
 * issued during program startup
 *
 * Input:  void
 * Output: 0
 *********************************************/

void PrintGreeting()
{
   /*Greeting*/
   printf("\n\nHello, this is a program which allows you to input\n");
   printf("a positive value then perform various mathematical\n");
   printf("calculations on said value until you choose to quit\n\n");
}

/********************************************
 * Function: PrintMenu
 *
 * This function will be used to print the
 * menu of selections after a positive value
 * has been determined
 *
 * Input:  void
 * Ouput:  0
 ********************************************/

void PrintMenu()
{
   /*menu*/
   printf("1 - Square the number\n\n");
   printf("2 - Cube the number\n\n");
   printf("3 - Approximate the Square Root\n\n");
   printf("4 - Find the actual Square Root\n\n");
   printf("5 - Compare approximate and actual roots\n\n");
   printf("6 - Choose a new number\n\n");
   printf("7 - Quit\n\n");
}

/*********************************************
 * Function: GetValidInt
 *
 * This function will be used to determine if 
 * the number given by the user is valid for 
 * this program
 *
 * Input:  minimum and maximum possible values
 * Output: a value between min and max
 *********************************************/

int GetValidInt(int min, int max)
{
   /*is set greater than max so the loop will be entered*/
   double input = max + 1;

   /*Loop assures a valid entry*/
   while(input < min || input > max)
   {

      printf("Please enter an integer between");
      printf(" %d and %d : ", min, max);
      scanf("%lf", &input);
      input = input/1;
   }

   return input;

}

/*****************************************************
 * Function: GetPositiveValue
 * 
 * reads input from the user and determines if it is 
 * a postive value. if not requests a new value
 *
 * Input:  void
 * Output: positive value for the program
 *****************************************************/

double GetPositiveValue(void)
{
   /*is set to negative so loop will be entered*/
   double input = -1.0;

   /*Loop assures a positive value*/
   while(input <= 0)
   {
      printf("Please, enter a positive value: ");
      scanf("%lf", &input);
   }

   return input;
}
  
/*******************************************************
 * Function:  FindSquare
 *
 * This function squares the value given to it and then
 * returns said value to main
 *
 * Input:  one double to be squared
 * Output: returns the squared double
 *******************************************************/

double FindSquare(double n)
{
   double square;

   square = n * n;

   return square;
}

/*******************************************************
 * Function: FindCube
 *
 * This function cubes the given value and returns the 
 * value to main
 * 
 * Input:  one double to be cubed
 * Output: one cubed double
 *******************************************************/

double FindCube(double n)
{
   double cube;
  
   cube = n * n * n;

   return cube;
}

/*******************************************************
 * Function: FindApproxSqRt
 *
 * This function approximates the square root of the 
 * given value using an equation
 *
 * Input:  one double to have the approximate root taken
 * Output: approximate root
 ********************************************************/

double FindApproxSqRt(double n)
{
   /*ensures that loop will be entered*/
   double lastGuess = 1.0;
   double nextGuess = 1.0;
   float difference = 1.0;

   /*will continue until the difference is less than 0.005 so it will be close
    *to acutual value*/
   do
   {
      lastGuess = nextGuess;
      nextGuess = 0.5 * (lastGuess + (n / lastGuess));
      difference = nextGuess - lastGuess;
      if(difference < 0)
      {
	 difference = difference * -1;
      }
   } while(difference > 0.005);

   return nextGuess;
      
}

/********************************************************
 * Function: FindDifference
 * 
 * This function finds the differance between the 
 * approximate square root and the actual square
 * root
 *
 * Input:  one double which is sent to FindApproxSqRt and
 *         SqRT from the math library 
 * Output: the difference between the approximate square
 *         root and the actualy square root
 *********************************************************/

double FindDifference(double n)
{
   double approxSqRt, root, difference;

   /*makes call to function FindApproxSqRt*/
   approxSqRt = FindApproxSqRt(n);
   
   root = sqrt(n);

   difference = approxSqRt - root;

   /*makes sure difference is positive*/
   if(difference < 0)
   {
      difference = difference * -1;
   }

   return difference;
}
   
   
