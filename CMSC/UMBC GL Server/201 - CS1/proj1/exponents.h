/*********************************************
 * File:     exponents.h
 * Author:   Christopher Mai
 * Date:     9/26/07
 * Section:  0203
 * E-mail:   chrmai1@umbc.edu
 *
 * This file contains the function prototypes
 * for the functions found in proj1.c
 *********************************************/

/*********************************************
 * Function: PrintGreeting
 *
 * This function prints a greeting to be 
 * issued during program startup
 *
 * Input:  void
 * Output: 0
 *********************************************/

void PrintGreeting();

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

void PrintMenu();

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

int GetValidInt(int min, int max);

/*****************************************************
 * Function: GetPositiveValue
 * 
 * reads input from the user and determines if it is 
 * a postive value. if not requests a new value
 *
 * Input:  void
 * Output: positive value for the program
 *****************************************************/

double GetPositiveValue(void);

/*******************************************************
 * Function:  FindSquare
 *
 * This function squares the value given to it and then
 * returns said value to main
 *
 * Input:  one double to be squared
 * Output: returns the squared double
 *******************************************************/

double FindSquare(double n);

/*******************************************************
 * Function: FindCube
 *
 * This function cubes the given value and returns the 
 * value to main
 * 
 * Input:  one double to be cubed
 * Output: one cubed double
 *******************************************************/

double FindCube(double n);

/*******************************************************
 * Function: FindApproxSqRt
 *
 * This function approximates the square root of the 
 * given value using an equation
 *
 * Input:  one double to have the approximate root taken
 * Output: approximate root
 ********************************************************/

double FindApproxSqRt(double n);

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

double FindDifference(double n);
